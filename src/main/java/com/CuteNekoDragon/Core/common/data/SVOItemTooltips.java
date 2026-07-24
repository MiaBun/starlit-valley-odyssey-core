package com.CuteNekoDragon.Core.common.data;

import com.CuteNekoDragon.Core.utils.TooltipBuilder;

public class SVOItemTooltips {

    public static void ProvideTooltips() {
        TooltipBuilder.addTooltip(SVOItems.QUARTZ).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo("tooltip.svo_core.quartz");
    }
}
