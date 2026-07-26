package com.CuteNekoDragon.Core.common.data;

import com.CuteNekoDragon.Core.utils.TooltipBuilder;

public class SVOItemTooltips {

    public static void ProvideTooltips() {
        // Foraged Minerals
        TooltipBuilder.addTooltip(SVOItems.QUARTZ).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo("tooltip.svo_core.quartz");
        TooltipBuilder.addTooltip(SVOItems.EARTH_CRYSTAL).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo("tooltip.svo_core.earth_crystal");
        TooltipBuilder.addTooltip(SVOItems.FROZEN_TEAR).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo("tooltip.svo_core.frozen_tear");
        TooltipBuilder.addTooltip(SVOItems.FIRE_QUARTZ).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo("tooltip.svo_core.fire_quartz");

        // Gemstones
        TooltipBuilder.addTooltip(SVOItems.EMERALD).addCoins().addGlyph(TooltipBuilder.SVOTypes.GEMSTONE)
                .addInfo("tooltip.svo_core.emerald");
        TooltipBuilder.addTooltip(SVOItems.AQUAMARINE).addCoins().addGlyph(TooltipBuilder.SVOTypes.GEMSTONE)
                .addInfo("tooltip.svo_core.aquamarine");
        TooltipBuilder.addTooltip(SVOItems.RUBY).addCoins().addGlyph(TooltipBuilder.SVOTypes.GEMSTONE)
                .addInfo("tooltip.svo_core.ruby");
    }
}
