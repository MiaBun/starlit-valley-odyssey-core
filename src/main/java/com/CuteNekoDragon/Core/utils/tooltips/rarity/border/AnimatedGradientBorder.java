package com.CuteNekoDragon.Core.utils.tooltips.rarity.border;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;

import com.CuteNekoDragon.Core.utils.tooltips.render.TooltipBounds;
import com.CuteNekoDragon.Core.utils.tooltips.render.TooltipDraw;
import com.CuteNekoDragon.Core.utils.tooltips.util.Colors;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public record AnimatedGradientBorder(int bgTop, int bgBottom, int[] palette, float speed) implements BorderRenderer {

    public static AnimatedGradientBorder fromJson(JsonObject json) {
        JsonArray arr = GsonHelper.getAsJsonArray(json, "colors");
        if (arr.isEmpty()) throw new JsonParseException("'colors' must not be empty");
        int[] palette = new int[arr.size()];
        for (int i = 0; i < palette.length; i++) palette[i] = Colors.argb(arr.get(i));
        int bg = Colors.argb(json, "background", TooltipDraw.VANILLA_BACKGROUND);
        return new AnimatedGradientBorder(bg, Colors.argb(json, "background_bottom", bg), palette,
                GsonHelper.getAsFloat(json, "speed", 0.5f));
    }

    @Override
    public void render(GuiGraphics graphics, TooltipBounds bounds, ItemStack stack, float time) {
        float t = time * speed;
        TooltipDraw.vanillaFrame(graphics, bounds, bgTop, bgBottom, Colors.sample(palette, t),
                Colors.sample(palette, t + 0.5F));
    }
}
