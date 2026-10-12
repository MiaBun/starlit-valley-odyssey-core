package com.CuteNekoDragon.Core.utils.price;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.tags.TagKey;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.Item;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

public final class PriceManager extends SimpleJsonResourceReloadListener {

    private static final Logger LOGGER = LogUtils.getLogger();

    private final Map<Item, PriceEntry> itemEntries = new HashMap<>();
    private final Map<TagKey<Item>, PriceEntry> tagEntries = new HashMap<>();
    private final Map<ResourceLocation, PriceCategory> categories = new HashMap<>();

    public PriceManager(Gson gson, String directory) {
        super(gson, directory);
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> resourceLocationJsonElementMap, ResourceManager resourceManager, ProfilerFiller profilerFiller) {

    }
}
