package com.CuteNekoDragon.Core.utils.tooltips.rarity.border;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;

import com.CuteNekoDragon.Core.utils.tooltips.render.TooltipBounds;
import com.CuteNekoDragon.Core.utils.tooltips.render.TooltipDraw;
import com.CuteNekoDragon.Core.utils.tooltips.util.Colors;
import com.google.gson.JsonObject;

public record GlowBorder(int color, int radius, int alpha, float pulseSpeed) implements BorderRenderer {

    public static GlowBorder fromJson(JsonObject json) {
        return new GlowBorder(Colors.argb(json, "color", 0xFFFFFFFF),
                GsonHelper.getAsInt(json, "radius", 4),
                GsonHelper.getAsInt(json, "alpha", 110),
                GsonHelper.getAsFloat(json, "pulse_speed", 0f));
    }

    @Override
    public void render(GuiGraphics g, TooltipBounds b, ItemStack stack, float time) {
        float pulse = pulseSpeed > 0 ? 0.65f + 0.35f * Mth.sin(time * pulseSpeed * Mth.TWO_PI) : 1f;
        for (int i = 1; i <= radius; i++) {
            float falloff = 1f - (float) (i - 1) / radius;
            int a = (int) (alpha * falloff * falloff * pulse);
            if (a <= 0) continue;
            TooltipDraw.ring(g, b.left() - i, b.top() - i, b.right() + i, b.bottom() + i, b.z(),
                    Colors.withAlpha(color, a));
        }
    }
}
