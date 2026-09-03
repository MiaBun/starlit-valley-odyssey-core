package com.CuteNekoDragon.Core.utils.ui;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;

public class CenteredEditBox extends EditBox {

    private final Font font;

    public CenteredEditBox(Font font, int x, int y, int width, int height, Component message) {
        super(font, x, y, width, height, message);
        this.font = font;
        this.setBordered(false); // we draw our own border/fill
        this.setTextColor(0xFFFFFF);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        int x = getX();
        int y = getY();
        int w = getWidth();
        int h = getHeight();

        // border + fill, same style as the box in your screenshot
        guiGraphics.fill(x, y, x + w, y + h, 0xFFAAAAAA);
        guiGraphics.fill(x + 1, y + 1, x + w - 1, y + h - 1, 0xFF000000);

        String text = this.getValue();
        int textWidth = this.font.width(text);
        int textX = x + (w - textWidth) / 2;
        int textY = y + (h - 8) / 2; // 8 = font line height

        guiGraphics.drawString(this.font, text, textX, textY, 0xFFFFFF, false);

        // blinking cursor, centered after the text, only while focused
        if (this.isFocused() && (System.currentTimeMillis() / 300) % 2 == 0) {
            int cursorX = textX + textWidth + 1;
            guiGraphics.fill(cursorX, textY - 1, cursorX + 1, textY + 9, 0xFFFFFFFF);
        }
    }
}
