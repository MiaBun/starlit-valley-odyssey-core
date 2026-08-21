package com.CuteNekoDragon.Core.client.components;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

import com.CuteNekoDragon.Core.common.component.LunchboxTooltip;

public class ClientLunchboxTooltip implements ClientTooltipComponent {

    private static final int SLOT_SIZE = 18;
    private static final int SLOTS_PER_ROW = 3;

    private final NonNullList<ItemStack> items;

    public ClientLunchboxTooltip(LunchboxTooltip data) {
        this.items = data.items();
    }

    @Override
    public int getHeight() {
        int nonEmpty = countNonEmpty();
        if (nonEmpty == 0) return 0;
        int rows = (int) Math.ceil(nonEmpty / (double) SLOTS_PER_ROW);
        return rows * SLOT_SIZE + 2;
    }

    @Override
    public int getWidth(Font font) {
        int nonEmtpty = countNonEmpty();
        int cols = Math.min(Math.max(nonEmtpty, 1), SLOTS_PER_ROW);
        return cols * SLOT_SIZE;
    }

    @Override
    public void renderImage(Font font, int x, int y, GuiGraphics guiGraphics) {
        int i = 0;
        for (ItemStack stack : items) {
            if (stack.isEmpty()) continue;;

            int col = i % SLOTS_PER_ROW;
            int row = i / SLOTS_PER_ROW;
            int slotX = x + col * SLOT_SIZE;
            int slotY = y + row * SLOT_SIZE;

            guiGraphics.renderItem(stack, slotX, slotY);
            guiGraphics.renderItemDecorations(font, stack, slotX, slotY);
            i++;
        }
    }

    private int countNonEmpty() {
        int count = 0;
        for (ItemStack stack : items) {
            if (!stack.isEmpty()) count++;
        }
        return count;
    }
}
