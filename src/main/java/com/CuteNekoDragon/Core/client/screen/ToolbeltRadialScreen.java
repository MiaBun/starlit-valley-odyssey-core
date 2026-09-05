package com.CuteNekoDragon.Core.client.screen;

import com.CuteNekoDragon.Core.common.item.ToolbeltItem;
import com.CuteNekoDragon.Core.network.SVONetworkHandler;
import com.CuteNekoDragon.Core.network.packet.OpenToolbeltPacket;
import com.CuteNekoDragon.Core.network.packet.RadialSelectPacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;

public class ToolbeltRadialScreen extends Screen {

    private static final int RADIUS = 70;
    private static final int SLOT_SIZE = 24;
    private static final int CENTER_SIZE = 28;
    private static final int CENTER_DEAD_ZONE = 20;

    private static final int SELECTION_NONE = -1;
    private static final int SELECTION_CENTER = -2;

    private final NonNullList<ItemStack> storedItems;
    private final int storageSize;

    private int hoveredIndex = SELECTION_NONE;

    public ToolbeltRadialScreen(ItemStack toolbeltStack) {
        super(Component.translatable("gui.svo_core.toolbelt_radial"));
        this.storageSize = ToolbeltItem.getStorageSize(toolbeltStack);
        this.storedItems = NonNullList.withSize(storageSize, ItemStack.EMPTY);
        if (toolbeltStack.hasTag() && toolbeltStack.getTag().contains(ToolbeltItem.TAG_Items)) {
            ContainerHelper.loadAllItems(toolbeltStack.getTag(), this.storedItems);
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void tick() {
        super.tick();
        if (!hasAltDown()) {
            confirmSelection();
            this.onClose();
        }
    }

    @Override
    public void onClose() {
        if (this.minecraft != null && this.minecraft.screen == this) {
            this.minecraft.setScreen(null);
        }
    }

    private void confirmSelection() {
        if (hoveredIndex == SELECTION_CENTER) {
            SVONetworkHandler.INSTANCE.sendToServer(new OpenToolbeltPacket());
        } else if (hoveredIndex >= 0) {
            SVONetworkHandler.INSTANCE.sendToServer(new RadialSelectPacket(hoveredIndex));
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        updateHovered(mouseX, mouseY, centerX, centerY);

        graphics.fill(0, 0, this.width, this.height, 0x60000000);
        renderCenterButton(graphics, centerX, centerY);
        renderItemSlots(graphics, centerX, centerY);

        super.render(graphics, mouseX, mouseY, partialTick);
    }

    private void renderCenterButton(GuiGraphics graphics, int centerX, int centerY) {
        boolean hovered = hoveredIndex == SELECTION_CENTER;
        int half = CENTER_SIZE / 2;

        graphics.fill(centerX - half, centerY - half, centerX + half, centerY + half, 0xCC202020);
        graphics.renderOutline(centerX - half, centerY - half, CENTER_SIZE, CENTER_SIZE,
                hovered ? 0xFFFFFFFF : 0xFF808080);
    }

    private void renderItemSlots(GuiGraphics graphics, int centerX, int centerY) {
        int half = SLOT_SIZE / 2;

        for (int i = 0; i < storageSize; i++) {
            double angle = (2 * Math.PI * i / storageSize) - (Math.PI / 2);
            int slotX = centerX + (int) Math.round(Math.cos(angle) * RADIUS) - half;
            int slotY = centerY + (int) Math.round(Math.sin(angle) * RADIUS) - half;

            boolean hovered = hoveredIndex == i;
            graphics.fill(slotX, slotY, slotX + SLOT_SIZE, slotY + SLOT_SIZE, hovered ? 0xCC4C4C4C : 0xCC202020);
            graphics.renderOutline(slotX, slotY, SLOT_SIZE, SLOT_SIZE, hovered ? 0xFFFFFFFF : 0xFF808080);

            ItemStack stack = storedItems.get(i);
            if (!stack.isEmpty()) {
                graphics.renderItem(stack, slotX + 4, slotY + 4);
                graphics.renderItemDecorations(this.font, stack, slotX + 4, slotY + 4);
            }
        }
    }

    private void updateHovered(int mouseX, int mouseY, int centerX, int centerY) {
        double dx = mouseX - centerX;
        double dy = mouseY - centerY;
        double dist = Math.sqrt(dx * dx + dy * dy);

        if (dist < CENTER_DEAD_ZONE) {
            hoveredIndex = SELECTION_CENTER;
            return;
        }

        if (dist > RADIUS + SLOT_SIZE / 2.0) {
            hoveredIndex = SELECTION_NONE;
            return;
        }

        double angle = Math.atan2(dy, dx) + (Math.PI / 2);
        if (angle < 0) {
            angle += 2 * Math.PI;
        }

        double segment = (2 * Math.PI) / storageSize;
        hoveredIndex = (int) Math.floor((angle + segment / 2) / segment) % storageSize;
    }
}
