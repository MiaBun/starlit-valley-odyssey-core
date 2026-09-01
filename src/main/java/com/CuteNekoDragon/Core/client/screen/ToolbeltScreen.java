package com.CuteNekoDragon.Core.client.screen;

import com.CuteNekoDragon.Core.common.container.ToolbeltContainer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

@SuppressWarnings("removal")
public class ToolbeltScreen extends AbstractContainerScreen<ToolbeltContainer> {

    private final ResourceLocation texture;
    private static final int PLAYER_INV_ROW_1_Y = 109;

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
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float particleTick) {
        this.renderBg(guiGraphics, particleTick, mouseX, mouseY);
        super.render(guiGraphics, mouseX, mouseY, particleTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        guiGraphics.blit(texture, leftPos, topPos, 0, 0, imageWidth, imageHeight, 256, 256);
    }
}
