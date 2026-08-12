package com.CuteNekoDragon.Core.client.components;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;

import com.CuteNekoDragon.Core.common.component.SackTooltip;
import com.CuteNekoDragon.Core.common.item.SackItem;
import lombok.Getter;

@SuppressWarnings("removal")
public class ClientSackTooltip implements ClientTooltipComponent {

    public static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation("minecraft",
            "textures/gui/container/bundle.png");
    private static final int MARGIN_Y = 4;
    private static final int BORDER_WIDTH = 1;
    private static final int TEX_SIZE = 128;
    private static final int SLOT_SIZE_X = 18;
    private static final int SLOT_SIZE_Y = 20;

    @Getter
    private static ItemStack trackedStack = ItemStack.EMPTY;
    @Getter
    private static int selectedIndex = 0;
    private static int trackedItemCount = 0;
    @Getter
    private static boolean hoveredThisFrame = false;

    private final NonNullList<ItemStack> items;
    private final int weight;
    private final int selected;

    public ClientSackTooltip(SackTooltip bundleTooltip) {
        this.items = bundleTooltip.getItems();
        this.weight = bundleTooltip.getWeight();

        ItemStack newStack = bundleTooltip.getContainerStack();
        if (bundleTooltip.getContainerStack() != trackedStack) {
            trackedStack = bundleTooltip.getContainerStack();
            selectedIndex = 0;
        }
        if (!ItemStack.isSameItemSameTags(newStack, trackedStack)) {
            SackItem.resetSelection();
        }
        trackedStack = newStack;
        trackedItemCount = items.size();
        selectedIndex = items.isEmpty() ? 0 : Mth.clamp(selectedIndex, 0, items.size() - 1);
        this.selected = SackItem.getSelectedItem(trackedStack);
    }

    public static void scroll(double delta) {
        if (!hoveredThisFrame || trackedItemCount == 0) return;
        selectedIndex = Mth.clamp(selectedIndex - (int) Math.signum(delta), 0, trackedItemCount - 1);
    }

    public static void resetHoverFlag() {
        hoveredThisFrame = false;
    }

    @Override
    public int getHeight() {
        hoveredThisFrame = true;
        return this.gridSizeY() * SLOT_SIZE_Y + 2 + MARGIN_Y;
    }

    @Override
    public int getWidth(Font font) {
        return this.gridSizeX() * SLOT_SIZE_X + 2;
    }

    @Override
    public void renderImage(Font font, int i, int j, GuiGraphics guiGraphics) {
        int k = this.gridSizeX();
        int l = this.gridSizeY();
        boolean bl = this.weight >= 64;
        int m = 0;

        for (int n = 0; n < l; n++) {
            for (int o = 0; o < k; o++) {
                int p = i + o * SLOT_SIZE_X + BORDER_WIDTH;
                int q = j + n * SLOT_SIZE_Y + BORDER_WIDTH;
                this.renderSlot(p, q, m++, bl, guiGraphics, font);
            }
        }

        this.drawBorder(i, j, k, l, guiGraphics);
    }

    private void renderSlot(int i, int j, int k, boolean bl, GuiGraphics guiGraphics, Font font) {
        if (k >= this.items.size()) {
            this.blit(guiGraphics, i, j, bl ? ClientSackTooltip.Texture.BLOCKED_SLOT : ClientSackTooltip.Texture.SLOT);
        } else {
            ItemStack itemStack = this.items.get(k);
            this.blit(guiGraphics, i, j, ClientSackTooltip.Texture.SLOT);
            guiGraphics.renderItem(itemStack, i + BORDER_WIDTH, j + BORDER_WIDTH, k);
            guiGraphics.renderItemDecorations(font, itemStack, i + 1, j + 1);
            if (k == this.selected) {
                AbstractContainerScreen.renderSlotHighlight(guiGraphics, i + BORDER_WIDTH, j + BORDER_WIDTH, 0);
            }
        }
    }

    private void drawBorder(int i, int j, int k, int l, GuiGraphics guiGraphics) {
        this.blit(guiGraphics, i, j, ClientSackTooltip.Texture.BORDER_CORNER_TOP);
        this.blit(guiGraphics, i + k * SLOT_SIZE_X + 1, j, ClientSackTooltip.Texture.BORDER_CORNER_TOP);

        for (int m = 0; m < k; m++) {
            this.blit(guiGraphics, i + BORDER_WIDTH + m * SLOT_SIZE_X, j,
                    ClientSackTooltip.Texture.BORDER_HORIZONTAL_TOP);
            this.blit(guiGraphics, i + BORDER_WIDTH + m * SLOT_SIZE_X, j + l * SLOT_SIZE_Y,
                    ClientSackTooltip.Texture.BORDER_HORIZONTAL_BOTTOM);
        }

        for (int m = 0; m < l; m++) {
            this.blit(guiGraphics, i, j + m * SLOT_SIZE_Y + BORDER_WIDTH, ClientSackTooltip.Texture.BORDER_VERTICAL);
            this.blit(guiGraphics, i + k * SLOT_SIZE_X + BORDER_WIDTH, j + m * SLOT_SIZE_Y + BORDER_WIDTH,
                    ClientSackTooltip.Texture.BORDER_VERTICAL);
        }

        this.blit(guiGraphics, i, j + l * SLOT_SIZE_Y, ClientSackTooltip.Texture.BORDER_CORNER_BOTTOM);
        this.blit(guiGraphics, i + k * SLOT_SIZE_X + BORDER_WIDTH, j + l * SLOT_SIZE_Y,
                ClientSackTooltip.Texture.BORDER_CORNER_BOTTOM);
    }

    private void blit(GuiGraphics guiGraphics, int i, int j, ClientSackTooltip.Texture texture) {
        guiGraphics.blit(TEXTURE_LOCATION, i, j, 0, texture.x, texture.y, texture.w, texture.h, TEX_SIZE, TEX_SIZE);
    }

    private int gridSizeX() {
        return Math.max(2, (int) Math.ceil(Math.sqrt(this.items.size() + 1.0)));
    }

    private int gridSizeY() {
        return (int) Math.ceil((this.items.size() + 1.0) / this.gridSizeX());
    }

    static enum Texture {

        SLOT(0, 0, 18, 20),
        BLOCKED_SLOT(0, 40, 18, 20),
        BORDER_VERTICAL(0, 18, 1, 20),
        BORDER_HORIZONTAL_TOP(0, 20, 18, 1),
        BORDER_HORIZONTAL_BOTTOM(0, 60, 18, 1),
        BORDER_CORNER_TOP(0, 20, 1, 1),
        BORDER_CORNER_BOTTOM(0, 60, 1, 1);

        public final int x;
        public final int y;
        public final int w;
        public final int h;

        private Texture(int j, int k, int l, int m) {
            this.x = j;
            this.y = k;
            this.w = l;
            this.h = m;
        }
    }
}
