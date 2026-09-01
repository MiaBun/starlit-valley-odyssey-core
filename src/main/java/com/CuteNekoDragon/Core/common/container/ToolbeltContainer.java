package com.CuteNekoDragon.Core.common.container;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class ToolbeltContainer extends AbstractContainerMenu {

    public enum ToolbeltTier {
        TIER_3(3, new int[] { 62, 80, 98 }),
        TIER_4(4, new int[] { 62, 80, 98, 116 }),
        TIER_5(5, new int[] { 44, 62, 80, 98, 116 }),
        TIER_6(6, new int[] { 26, 44, 62, 80, 98, 116 }),
        TIER_8(8, new int[] { 26, 44, 62, 80, 98, 116, 134, 152 }),
        TIER_9(9, new int[] { 8, 26, 44, 62, 80, 98, 116, 134, 152 });

        public final int storageSize;
        public final int[] slotX;

        ToolbeltTier(int storageSize, int[] slotX) {
            this.storageSize = storageSize;
            this.slotX = slotX;
        }

        public static ToolbeltTier fromStorageSize(int size) {
            for (ToolbeltTier t : values()) {
                if (t.storageSize == size) return t;
            }
            throw new IllegalArgumentException("No tier for storage size " + size);
        }
    }

    protected ToolbeltContainer(@Nullable MenuType<?> menuType, int containerId) {
        super(menuType, containerId);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return false;
    }
}
