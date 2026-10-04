package com.CuteNekoDragon.Core.utils.tooltips;

import com.CuteNekoDragon.Core.utils.tooltips.glyph.Glyph;
import com.CuteNekoDragon.Core.utils.tooltips.rarity.TooltipRarity;
import com.CuteNekoDragon.Core.utils.tooltips.value.DataValues;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.slf4j.Logger;
import snownee.jade.impl.Tooltip;

import java.util.Map;

final class TooltipResourceLoader extends SimplePreparableReloadListener<TooltipResourceLoader.Loaded> {

    private static final Logger LOGGER = LogUtils.getLogger();

    @Override
    protected Loaded prepare(ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        return null;
    }

    @Override
    protected void apply(Loaded loaded, ResourceManager resourceManager, ProfilerFiller profilerFiller) {

    }

    record Loaded(TooltipStyle style, Map<String, DataValues.Table> values, Map<ResourceLocation, TooltipRarity> rarities,
                  Map<ResourceLocation, Glyph> glyphs) {}
}
