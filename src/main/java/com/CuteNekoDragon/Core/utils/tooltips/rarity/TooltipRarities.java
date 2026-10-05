package com.CuteNekoDragon.Core.utils.tooltips.rarity;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.utils.tooltips.rarity.border.BorderRenderer;
import com.CuteNekoDragon.Core.utils.tooltips.rarity.border.BorderTypes;
import com.CuteNekoDragon.Core.utils.tooltips.util.Colors;
import com.CuteNekoDragon.Core.utils.tooltips.util.TextUtil;
import com.google.gson.JsonObject;
import com.mojang.logging.LogUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.util.Lazy;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;

public final class TooltipRarities {
    private static final Logger LOGGER = LogUtils.getLogger();

    private static final Map<ResourceLocation, TooltipRarity> CODE = new ConcurrentHashMap<>();
    private static volatile Map<ResourceLocation, TooltipRarity> data = Map.of();
    private static volatile Map<ResourceLocation, TooltipRarity> merged = Map.of();
    private static volatile List<TooltipRarity> byPriority = List.of();

    private static final Map<Item, ResourceLocation> ITEM_ASSIGNMENTS = new ConcurrentHashMap<>();
    private static final List<PredicateAssignment> PREDICATE_ASSIGNMENTS = new CopyOnWriteArrayList<>();

    private record PredicateAssignment(Predicate<ItemStack> test, ResourceLocation rarity) {}

    private TooltipRarities() {}

    public static TooltipRarity register(TooltipRarity rarity) {
        CODE.put(rarity.id(), rarity);
        rebuild();
        return rarity;
    }

    public static void assign(ItemLike item, ResourceLocation rarity) {
        ITEM_ASSIGNMENTS.put(item.asItem(), rarity);
    }

    public static void assign(ItemLike item, TooltipRarity rarity) {
        assign(item, rarity.id());
    }

    public static void assign(Predicate<ItemStack> test, ResourceLocation rarity) {
        PREDICATE_ASSIGNMENTS.add(new PredicateAssignment(test, rarity));
    }

    @Nullable
    public static TooltipRarity byId(ResourceLocation id) {
        return merged.get(id);
    }

    public static List<TooltipRarity> all() {
        return byPriority;
    }

    @Nullable
    public static TooltipRarity get(ItemStack stack) {
        if (stack.isEmpty()) return null;

        CompoundTag tag = stack.getTag();
        if (tag != null && tag.contains(SVOCore.nbtKey(), Tag.TAG_COMPOUND)) {
            CompoundTag ours = tag.getCompound(SVOCore.nbtKey());
            if (ours.contains("rarity", Tag.TAG_STRING)) {
                ResourceLocation id = ResourceLocation.tryParse(ours.getString("rarity"));
                TooltipRarity r = id == null ? null : byId(id);
                if (r != null) return r;
            }
        }

        ResourceLocation assigned = ITEM_ASSIGNMENTS.get(stack.getItem());
        if (assigned != null && byId(assigned) != null) return byId(assigned);

        for (PredicateAssignment a : PREDICATE_ASSIGNMENTS) {
            if (a.test().test(stack)) {
                TooltipRarity r = byId(a.rarity());
                if (r != null) return r;
            }
        }

        for (TooltipRarity r : byPriority) {
            if (stack.is(r.tag())) return r;
        }
        return null;
    }

    public static void setData(Map<ResourceLocation, TooltipRarity> rarities) {
        data = Map.copyOf(rarities);
        rebuild();
    }

    private static synchronized void rebuild() {
        Map<ResourceLocation, TooltipRarity> m = new HashMap<>(CODE);
        m.putAll(data);
        List<TooltipRarity> sorted = new ArrayList<>(m.values());
        sorted.sort(Comparator.comparingInt(TooltipRarity::priority).reversed()
                .thenComparing(r -> r.id().toString()));
        merged = Map.copyOf(m);
        byPriority = List.copyOf(sorted);
    }

    public static Map<ResourceLocation, TooltipRarity> load(ResourceManager manager) {
        Map<ResourceLocation, TooltipRarity> out = new HashMap<>();
        String dir = "tooltip/rarities";
        for (Map.Entry<ResourceLocation, Resource> e : manager.listResources(dir, p -> p.getPath().endsWith(".json")).entrySet()) {
            ResourceLocation file = e.getKey();
            String path = file.getPath().substring(dir.length() + 1, file.getPath().length() - ".json".length());
            ResourceLocation id = new ResourceLocation(file.getNamespace(), path);
            try (Reader reader = e.getValue().openAsReader()) {
                out.put(id, parse(id, GsonHelper.parse(reader)));
            } catch (Exception ex) {
                LOGGER.error("Failed to load tooltip rarity {}", file, ex);
            }
        }
        return out;
    }

    public static TooltipRarity parse(ResourceLocation id, JsonObject json) {
        TooltipRarity.Builder b = TooltipRarity.builder(id)
                .color(Colors.text(json, "color"))
                .recolorName(GsonHelper.getAsBoolean(json, "recolor_name", true))
                .showLabel(GsonHelper.getAsBoolean(json, "show_label", true))
                .priority(GsonHelper.getAsInt(json, "priority", 0));
        if (json.has("name")) b.name(TextUtil.keyOrLiteral(GsonHelper.getAsString(json, "name")));
        if (json.has("border")) {
            JsonObject borderJson = GsonHelper.getAsJsonObject(json, "border");
            // Parsed on first use so border types registered during client setup are available.
            b.lazyBorder(Lazy.of(() -> {
                try {
                    return BorderTypes.parse(borderJson);
                } catch (Exception ex) {
                    LOGGER.error("Invalid border for tooltip rarity {}", id, ex);
                    return (BorderRenderer) null;
                }
            }));
        }
        return b.build();
    }


}
