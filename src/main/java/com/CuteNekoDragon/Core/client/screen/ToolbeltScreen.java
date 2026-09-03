package com.CuteNekoDragon.Core.client.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import com.CuteNekoDragon.Core.common.container.ToolbeltContainer;
import com.CuteNekoDragon.Core.common.item.ToolbeltItem;
import com.CuteNekoDragon.Core.network.SVONetworkHandler;
import com.CuteNekoDragon.Core.network.packet.SetToolbeltSlotPacket;
import com.CuteNekoDragon.Core.utils.ui.CenteredEditBox;

@SuppressWarnings("removal")
public class ToolbeltScreen extends AbstractContainerScreen<ToolbeltContainer> {

    private final ResourceLocation texture;
    private static final int PLAYER_INV_ROW_1_Y = 109;
    private static final int HOTBAR_BOX_BORDER = 0xFFAAAAAA;
    private static final int HOTBAR_BOX_FILL = 0xFF000000;
    private static final int PANEL_FILL = 0xFFC6C6C6;
    private static final int PANEL_BORDER = 0xFF373737;

    private CenteredEditBox slotInput;

    public ToolbeltScreen(ToolbeltContainer menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);

        int storageWidth = menu.getStorageSize();
        this.texture = new ResourceLocation("svo_core",
                "textures/gui/container/toolbelt/slots_" + storageWidth + ".png");
        this.imageWidth = 175;

        this.imageHeight = PLAYER_INV_ROW_1_Y + 3 * 18 + 4 + 18 + 6;
        this.inventoryLabelY = 40;
    }

    @Override
    protected void init() {
        super.init();
        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;

        int size = 22;
        int inputX = leftPos + imageWidth + 8;
        int inputY = topPos + 20;

        slotInput = new CenteredEditBox(this.font, inputX, inputY, size, size, Component.literal("Slot"));
        slotInput.setMaxLength(1);
        slotInput.setFilter(s -> s.isEmpty() || s.matches("[1-9]"));
        slotInput.setValue(String.valueOf(ToolbeltItem.getSelectedSlot(menu.getStorageItemStack())));
        slotInput.setResponder(this::onSlotChanged);
        addRenderableWidget(slotInput);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float particleTick) {
        this.renderBg(guiGraphics, particleTick, mouseX, mouseY);
        super.render(guiGraphics, mouseX, mouseY, particleTick);

        String label = "Hotbar";
        int labelWidth = this.font.width(label);
        guiGraphics.drawString(this.font, label,
                (slotInput.getX() + (slotInput.getWidth() - labelWidth) / 2) + 5,
                slotInput.getY() - 12,
                0xFFFFFF, false);

        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        guiGraphics.blit(texture, leftPos, topPos, 0, 0, imageWidth, imageHeight, 256, 256);

        int panelX = leftPos + imageWidth - 2;
        int panelY = topPos;
        int panelWidth = 60;
        int panelHeight = 50;

        guiGraphics.fill(panelX, panelY, panelX + panelWidth, panelY + panelHeight, PANEL_BORDER);
        guiGraphics.fill(panelX, panelY + 2, panelX + panelWidth - 2, panelY + panelHeight - 2, PANEL_FILL);

        int x = leftPos + imageWidth + 8;
        int y = topPos + 20;
        int size = 22;

        guiGraphics.fill(x, y, x + size, y + size, HOTBAR_BOX_BORDER);
        guiGraphics.fill(x + 1, y + 1, x + size - 1, y + size - 1, HOTBAR_BOX_FILL);
    }

    private void onSlotChanged(String value) {
        if (value.isEmpty()) return;
        int slot = Integer.parseInt(value);
        if (slot < 1 || slot > 9) return;
        SVONetworkHandler.INSTANCE.sendToServer(new SetToolbeltSlotPacket(slot));
    }
}
