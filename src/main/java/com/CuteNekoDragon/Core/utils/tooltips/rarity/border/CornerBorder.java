package com.CuteNekoDragon.Core.utils.tooltips.rarity.border;

import com.CuteNekoDragon.Core.utils.tooltips.render.TooltipBounds;
import com.google.gson.JsonObject;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;

public record CornerBorder(ResourceLocation texture, int size, int offset) implements BorderRenderer {

    @SuppressWarnings("removal")
    public static CornerBorder fromJson(JsonObject json) {
        return new CornerBorder(new ResourceLocation(GsonHelper.getAsString(json, "texture")),
                GsonHelper.getAsInt(json, "size", 8),
                GsonHelper.getAsInt(json, "offset", 2));
    }

    @Override
    public void render(GuiGraphics g, TooltipBounds b, ItemStack stack, float time) {
        int tex = size * 2;
        int l = b.left() - offset;
        int t = b.top() - offset;
        int r = b.right() + offset - size;
        int bo = b.bottom() + offset - size;
        RenderSystem.enableBlend();
        g.blit(texture, l, t, b.z(), 0, 0, size, size, tex, tex);
        g.blit(texture, r, t, b.z(), size, 0, size, size, tex, tex);
        g.blit(texture, l, bo, b.z(), 0, size, size, size, tex, tex);
        g.blit(texture, r, bo, b.z(), size, size, size, size, tex, tex);
    }
}
