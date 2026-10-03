package com.CuteNekoDragon.Core.utils.tooltips.value;

import com.CuteNekoDragon.Core.utils.tooltips.util.TextUtil;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class DataValues {
    private static final Logger LOGGER = LogUtils.getLogger();

    public record Table(Map<ResourceLocation, TooltipValue> items, List<Map.Entry<TagKey<Item>, TooltipValue>> tags) {}

    private static volatile Map<String, Table> tables = Map.of();

    private DataValues() {}

    @Nullable
    public static TooltipValue lookup(String type, ItemStack stack) {
        Table table = tables.get(type);
        if (table == null) return null;
        ResourceLocation id = ForgeRegistries.ITEMS.getKey(stack.getItem());
        TooltipValue v = id == null ? null : table.items().get(id);
        if (v != null) return v;
        for (Map.Entry<TagKey<Item>, TooltipValue> e : table.tags()) {
            if (stack.is(e.getKey())) return e.getValue();
        }
        return null;
    }

    public static void set(Map<String, Table> loaded) {
        tables = Map.copyOf(loaded);
    }

    public static Map<String, Table> load(ResourceManager manager) {
        Map<String, Map<ResourceLocation, TooltipValue>> items = new HashMap<>();
        Map<String, Map<TagKey<Item>, TooltipValue>> tags = new HashMap<>();
        for (Map.Entry<ResourceLocation, Resource> e : manager.listResources("tooltip/values", p -> p.getPath().endsWith(".json")).entrySet()) {
            String path = e.getKey().getPath();
            String type = path.substring(path.lastIndexOf('/') + 1, path.length() - ".json".length());
            try (Reader reader = e.getValue().openAsReader()) {
                JsonObject json = GsonHelper.parse(reader);
                for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
                    TooltipValue value = parseEntry(entry.getValue());
                    String key = entry.getKey();
                    if (key.startsWith("#")) {
                        TagKey<Item> tag = TagKey.create(Registries.ITEM, new ResourceLocation(key.substring(1)));
                        tags.computeIfAbsent(type, k -> new LinkedHashMap<>()).put(tag, value);
                    } else {
                        items.computeIfAbsent(type, k -> new HashMap<>()).put(new ResourceLocation(key), value);
                    }
                }
            } catch (Exception ex) {
                LOGGER.error("Failed to load tooltip values {}", e.getKey(), ex);
            }
        }
        Map<String, Table> out = new HashMap<>();
        for (String type : union(items.keySet(), tags.keySet())) {
            out.put(type, new Table(
                    Map.copyOf(items.getOrDefault(type, Map.of())),
                    tags.getOrDefault(type, Map.of()).entrySet().stream()
                            .map(en -> Map.entry(en.getKey(), en.getValue())).toList()));
        }
        return out;
    }

    private static TooltipValue parseEntry(JsonElement el) {
        if (el.isJsonPrimitive()) return TooltipValue.of(el.getAsDouble());
        JsonObject o = el.getAsJsonObject();
        TooltipValue.Builder b = TooltipValue.builder(require(o, "value").getAsDouble());
        if (o.has("breakdown")) {
            for (JsonElement line : GsonHelper.getAsJsonArray(o, "breakdown")) {
                JsonObject l = line.getAsJsonObject();
                b.line(TextUtil.keyOrLiteral(GsonHelper.getAsString(l, "label")), require(l, "value").getAsDouble());
            }
        }
        if (o.has("notes")) {
            for (JsonElement note : GsonHelper.getAsJsonArray(o, "notes")) b.note(note.getAsString());
        }
        return b.build();
    }

    private static JsonElement require(JsonObject o, String key) {
        if (!o.has(key)) throw new com.google.gson.JsonParseException("Missing '" + key + "'");
        return o.get(key);
    }

    private static List<String> union(java.util.Set<String> a, java.util.Set<String> b) {
        List<String> out = new ArrayList<>(a);
        for (String s : b) if (!a.contains(s)) out.add(s);
        return out;
    }
}
