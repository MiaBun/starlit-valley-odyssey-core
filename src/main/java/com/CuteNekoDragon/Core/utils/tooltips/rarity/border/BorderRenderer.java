package com.CuteNekoDragon.Core.utils.tooltips.rarity.border;

import com.CuteNekoDragon.Core.utils.tooltips.render.TooltipBounds;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;

import java.util.List;

@FunctionalInterface
public interface BorderRenderer {

    void render(GuiGraphics graphics, TooltipBounds bounds, ItemStack stack, float time);

    default BorderRenderer then(BorderRenderer next) {
        return new LayeredBorder(List.of(this, next));
    }
}
