package com.CuteNekoDragon.Core.utils.tooltips.render;

public record TooltipBounds(int x, int y, int width, int height, int z) {
    public int left() { return x - 4; }
    public int top() { return y - 4; }
    public int right() { return x + width + 4; }
    public int bottom() { return y + height + 4; }
}
