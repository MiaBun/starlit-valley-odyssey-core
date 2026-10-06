package com.CuteNekoDragon.Core.utils.tooltips.element;

import com.CuteNekoDragon.Core.utils.tooltips.TooltipContext;
import com.CuteNekoDragon.Core.utils.tooltips.rarity.TooltipRarities;
import com.CuteNekoDragon.Core.utils.tooltips.rarity.TooltipRarity;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.List;

public final class RarityElement implements TooltipElement {
    @Override
    public boolean isVisible(TooltipContext ctx) {
        TooltipRarity r = TooltipRarities.get(ctx.stack());
        return r != null && r.showLabel();
    }

    @Override
    public void appendLines(TooltipContext ctx, List<Component> out) {
        TooltipRarity r = TooltipRarities.get(ctx.stack());
        if (r == null) return;
        MutableComponent label = r.name().copy();
        if (r.color() != null) label = label.withStyle(s -> s.withColor(r.color()));
        out.add(label);
    }
}
