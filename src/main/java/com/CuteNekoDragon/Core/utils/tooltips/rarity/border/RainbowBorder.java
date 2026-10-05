package com.CuteNekoDragon.Core.utils.tooltips.rarity.border;

import com.CuteNekoDragon.Core.utils.tooltips.render.TooltipBounds;
import com.CuteNekoDragon.Core.utils.tooltips.render.TooltipDraw;
import com.CuteNekoDragon.Core.utils.tooltips.util.Colors;
import com.google.gson.JsonObject;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;

public record RainbowBorder(int bgTop, int bgBottom, float speed, float saturation, float brightness, int alpha)
        implements BorderRenderer {

    public static RainbowBorder fromJson(JsonObject json) {
        int bg = Colors.argb(json, "background", TooltipDraw.VANILLA_BACKGROUND);
        return new RainbowBorder(bg, Colors.argb(json, "background_bottom", bg),
                GsonHelper.getAsFloat(json, "speed", 0.25f),
                GsonHelper.getAsFloat(json, "saturation", 0.8f),
                GsonHelper.getAsFloat(json, "brightness", 1.0f),
                GsonHelper.getAsInt(json, "alpha", 255));
    }

    @Override
    public void render(GuiGraphics g, TooltipBounds b, ItemStack stack, float time) {
        float hue = time * speed;
        int top = Colors.withAlpha(Mth.hsvToRgb(hue - Mth.floor(hue), saturation, brightness), alpha);
        float hue2 = hue + 0.33f;
        int bottom = Colors.withAlpha(Mth.hsvToRgb(hue2 - Mth.floor(hue2), saturation, brightness), alpha);
        TooltipDraw.vanillaFrame(g, b, bgTop, bgBottom, top, bottom);
    }
}
