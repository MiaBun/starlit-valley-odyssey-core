package com.CuteNekoDragon.Core.utils.tooltips.render;

import net.minecraft.client.gui.GuiGraphics;

public final class TooltipDraw {
    public static final int VANILLA_BACKGROUND = 0xF0100010;
    public static final int VANILLA_BORDER_TOP = 0x505000FF;
    public static final int VANILLA_BORDER_BOTTOM = 0x5028007F;

    private TooltipDraw() {}

    public static void vanillaFrame(GuiGraphics g, TooltipBounds b, int bgTop, int bgBottom, int borderTop, int borderBottom) {
        int x = b.x() - 3, y = b.y() - 3, w = b.width() + 6, h = b.height() + 6, z = b.z();
        // background
        g.fill(x, y - 1, x + w, y, z, bgTop);
        g.fill(x, y + h, x + w, y + h + 1, z, bgBottom);
        g.fillGradient(x, y, x + w, y + h, z, bgTop, bgBottom);
        g.fillGradient(x - 1, y, x, y + h, z, bgTop, bgBottom);
        g.fillGradient(x + w, y, x + w + 1, y + h, z, bgTop, bgBottom);
        // frame
        frame(g, x, y + 1, w, h, z, borderTop, borderBottom);
    }

    public static void background(GuiGraphics g, TooltipBounds b, int bgTop, int bgBottom) {
        int x = b.x() - 3, y = b.y() - 3, w = b.width() + 6, h = b.height() + 6, z = b.z();
        g.fill(x, y - 1, x + w, y, z, bgTop);
        g.fill(x, y + h, x + w, y + h + 1, z, bgBottom);
        g.fillGradient(x, y, x + w, y + h, z, bgTop, bgBottom);
        g.fillGradient(x - 1, y, x, y + h, z, bgTop, bgBottom);
        g.fillGradient(x + w, y, x + w + 1, y + h, z, bgTop, bgBottom);
    }

    public static void frame(GuiGraphics g, int x, int y, int w, int h, int z, int top, int bottom) {
        g.fillGradient(x, y, x + 1, y + h - 2, z, top, bottom);
        g.fillGradient(x + w - 1, y, x + w, y + h - 2, z, top, bottom);
        g.fill(x, y - 1, x + w, y, z, top);
        g.fill(x, y + h - 2, x + w, y + h - 1, z, bottom);
    }

    public static void ring(GuiGraphics g, int x0, int y0, int x1, int y1, int z, int color) {
        g.fill(x0, y0, x1, y0 + 1, z, color);
        g.fill(x0, y1 - 1, x1, y1, z, color);
        g.fill(x0, y0 + 1, x0 + 1, y1 - 1, z, color);
        g.fill(x1 - 1, y0 + 1, x1, y1 - 1, z, color);
    }
}
