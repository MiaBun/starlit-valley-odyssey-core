package com.CuteNekoDragon.Core.utils.tooltips;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.utils.tooltips.glyph.Glyph;
import com.CuteNekoDragon.Core.utils.tooltips.glyph.Glyphs;
import com.CuteNekoDragon.Core.utils.tooltips.rarity.TooltipRarities;
import com.CuteNekoDragon.Core.utils.tooltips.rarity.TooltipRarity;
import com.CuteNekoDragon.Core.utils.tooltips.value.DataValues;
import com.google.gson.JsonObject;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import org.slf4j.Logger;
import snownee.jade.impl.Tooltip;

import javax.xml.crypto.Data;
import java.io.Reader;
import java.util.Map;
import java.util.Optional;

final class TooltipResourceLoader extends SimplePreparableReloadListener<TooltipResourceLoader.Loaded> {

    private static final Logger LOGGER = LogUtils.getLogger();

    @Override
    protected Loaded prepare(ResourceManager manager, ProfilerFiller profilerFiller) {
        return new Loaded(loadStyle(manager), DataValues.load(manager), TooltipRarities.load(manager), Glyphs.load(manager));
    }

    @Override
    protected void apply(Loaded loaded, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        TooltipStyle.set(loaded.style());
        DataValues.set(loaded.values());
        TooltipRarities.setData(loaded.rarities());
        Glyphs.setData(loaded.glyphs());
    }

    record Loaded(TooltipStyle style, Map<String, DataValues.Table> values, Map<ResourceLocation, TooltipRarity> rarities,
                  Map<ResourceLocation, Glyph> glyphs) {}

    private static TooltipStyle loadStyle(ResourceManager manager) {
        ResourceLocation location = SVOCore.id("tooltip/style.json");
        Optional<Resource> resource = manager.getResource(location);
        if(resource.isEmpty()) return TooltipStyle.parse(new JsonObject());
        try (Reader reader = resource.get().openAsReader()) {
            return TooltipStyle.parse(GsonHelper.parse(reader));
        } catch (Exception e) {
            LOGGER.error("Failed to load {}, using the default tooltip style", location, e);
            return TooltipStyle.parse(new JsonObject());
        }
    }
}
