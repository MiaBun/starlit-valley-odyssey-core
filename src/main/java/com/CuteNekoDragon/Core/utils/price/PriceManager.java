package com.CuteNekoDragon.Core.utils.price;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public final class PriceManager extends SimpleJsonResourceReloadListener {

    private static final Logger LOGGER = LogUtils.getLogger();

    private final Map<Item, PriceEntry> itemEntries = new HashMap<>();
    private final Map<TagKey<Item>, PriceEntry> tagEntries = new HashMap<>();
    private final Map<ResourceLocation, PriceCategory> categories = new HashMap<>();
    private final Map<Item, ResolvedPrice> cache = new ConcurrentHashMap<>();

    public PriceManager() {
        super(new Gson(), "prices");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> files, ResourceManager rm, ProfilerFiller profiler) {
        itemEntries.clear();
        tagEntries.clear();
        categories.clear();
        cache.clear();

        files.forEach((fileId, json) -> {
            try {
                parseFile(json.getAsJsonObject());
            } catch (Exception ex) {
                LOGGER.error("Failed to load price file {}", fileId, ex);
            }
        });
    }

    private void parseFile(JsonObject root) {
        if (root.has("categories")) {
            for (var e : GsonHelper.getAsJsonObject(root, "categories").entrySet()) {
                ResourceLocation id = new ResourceLocation(e.getKey());
                JsonObject o = e.getValue().getAsJsonObject();
                OptionalDouble base = o.has("base_price")
                        ? OptionalDouble.of(o.get("base_price").getAsDouble()) : OptionalDouble.empty();
                categories.put(id, new PriceCategory(id, base));
            }
        }
        if (root.has("items")) {
            for (var e : GsonHelper.getAsJsonObject(root, "items").entrySet()) {
                JsonElement v = e.getValue();
                OptionalDouble price = OptionalDouble.empty();
                List<ResourceLocation> cats = new ArrayList<>();

                if(v.isJsonPrimitive()) {
                    price = OptionalDouble.of(v.getAsDouble());
                } else {
                    JsonObject o = v.getAsJsonObject();
                    if (o.has("price")) price = OptionalDouble.of(o.get("price").getAsDouble());
                    if (o.has("categories")) {
                        for (JsonElement c : o.getAsJsonArray("categories")) cats.add(new ResourceLocation(c.getAsString()));
                    }
                }

                PriceEntry entry = new PriceEntry(price, cats);
                String key = e.getKey();
                if(key.startsWith("#")) {
                    TagKey<Item> tag = TagKey.create(Registries.ITEM, new ResourceLocation(key.substring(1)));
                    tagEntries.merge(tag, entry, PriceEntry::merge);
                } else {
                    ResourceLocation id = new ResourceLocation(key);
                    if(ForgeRegistries.ITEMS.containsKey(id)) {
                        itemEntries.merge(ForgeRegistries.ITEMS.getValue(id), entry, PriceEntry::merge);
                    } else {
                        LOGGER.warn("Price entry for unknown item {}", id);
                    }
                }
            }
        }
    }

    public ResolvedPrice resolve(Item item) {
        return cache.computeIfAbsent(item, this::resolve);
    }

    private ResolvedPrice compute(Item item) {
        OptionalDouble price = OptionalDouble.empty();
        Set<ResourceLocation> cats = new LinkedHashSet<>();

        PriceEntry direct = itemEntries.get(item);
        if (direct != null) {
            price = direct.price();
            cats.addAll(direct.categories());
        }

        ItemStack probe = new ItemStack(item);
        for (var e : tagEntries.entrySet()) {
            if (probe.is(e.getKey())) {
                if (price.isEmpty() && e.getValue().price().isPresent()) price = e.getValue().price();
                cats.addAll(e.getValue().categories());
            }
        }
        if (price.isEmpty()) {
            price = cats.stream()
                    .map(categories::get)
                    .filter(Objects::nonNull)
                    .filter(c -> c.basePrice().isPresent())
                    .mapToDouble(c -> c.basePrice().getAsDouble())
                    .max();
        }
        return new ResolvedPrice(price, Collections.unmodifiableSet(cats));
    }
}
