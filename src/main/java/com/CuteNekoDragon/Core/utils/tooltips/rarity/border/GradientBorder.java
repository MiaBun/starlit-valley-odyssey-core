package com.CuteNekoDragon.Core.utils.tooltips.rarity.border;

import com.CuteNekoDragon.Core.utils.tooltips.render.TooltipBounds;
import com.CuteNekoDragon.Core.utils.tooltips.render.TooltipDraw;
import com.CuteNekoDragon.Core.utils.tooltips.util.Colors;
import com.google.gson.JsonObject;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;

public record GradientBorder(int bgTop, int bgBottom, int borderTop, int borderBottom) implements BorderRenderer {

    public static GradientBorder of(int borderTop, int borderBottom) {
        return new GradientBorder(TooltipDraw.VANILLA_BACKGROUND, TooltipDraw.VANILLA_BACKGROUND, borderTop, borderBottom);
    }

    public static GradientBorder fromJson(JsonObject json) {
        int bg = Colors.argb(json, "background", TooltipDraw.VANILLA_BACKGROUND);
        int top = Colors.argb(json, "border_top", TooltipDraw.VANILLA_BORDER_TOP);
        return new GradientBorder(bg, Colors.argb(json, "background_bottom", bg),
                top, Colors.argb(json, "border_bottom", json.has("border_top") ? top : TooltipDraw.VANILLA_BORDER_BOTTOM));
    }

    @Override
    public void render(GuiGraphics g, TooltipBounds b, ItemStack stack, float time) {
        TooltipDraw.vanillaFrame(g, b, bgTop, bgBottom, borderTop, borderBottom);
    }
}
