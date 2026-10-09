package com.CuteNekoDragon.Core.utils.tooltips.render;

import net.minecraft.Util;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.world.item.ItemStack;

import com.CuteNekoDragon.Core.utils.tooltips.rarity.border.BorderRenderer;
import com.mojang.blaze3d.systems.RenderSystem;

import java.util.List;

public final class TooltipRenderer {

    public static final int Z = 400;

    private TooltipRenderer() {}

    public static void render(GuiGraphics g, Font font, List<ClientTooltipComponent> components, int mouseX, int mouseY,
                              int screenWidth, int screenHeight, ItemStack stack, BorderRenderer border) {
        if (components.isEmpty()) return;

        int width = 0;
        int height = components.size() == 1 ? -2 : 0;
        for (ClientTooltipComponent c : components) {
            width = Math.max(width, c.getWidth(font));
            height += c.getHeight();
        }

        int x = mouseX + 12;
        int y = mouseY - 12;
        if (x + width > screenWidth) x = Math.max(x - 24 - width, 4);
        if (y + height + 3 > screenHeight) y = screenHeight - height - 3;
        y = Math.max(y, 4);

        float time = (Util.getMillis() % 3_600_000L) / 1000f;

        g.pose().pushPose();
        RenderSystem.enableBlend();
        border.render(g, new TooltipBounds(x, y, width, height, Z), stack, time);
        g.flush();

        g.pose().translate(0.0F, 0.0F, Z);
        int lineY = y;
        for (int i = 0; i < components.size(); i++) {
            ClientTooltipComponent c = components.get(i);
            c.renderText(font, x, lineY, g.pose().last().pose(), g.bufferSource());
            lineY += c.getHeight() + (i == 0 ? 2 : 0);
        }
        g.flush();

        lineY = y;
        for (int i = 0; i < components.size(); i++) {
            ClientTooltipComponent c = components.get(i);
            c.renderImage(font, x, lineY, g);
            lineY += c.getHeight() + (i == 0 ? 2 : 0);
        }
        g.pose().popPose();
    }
}
