package com.CuteNekoDragon.Core.common.container;

import com.CuteNekoDragon.Core.common.data.SVOContainers;
import com.CuteNekoDragon.Core.common.item.LunchboxItem;
import lombok.Getter;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;


public class LunchboxContainer extends AbstractContainerMenu {

    public enum LunchboxTier {
        TIER_3(3,  new int[]{62, 80, 98}),
        TIER_4(4,  new int[]{62, 80, 98, 116}),
        TIER_5(5,  new int[]{44, 62, 80, 98, 116}),
        TIER_6(6,  new int[]{26, 44, 62, 80, 98, 116}),
        TIER_8(8,  new int[]{26, 44, 62, 80, 98, 116, 134, 152}),
        TIER_9(9,  new int[]{8, 26, 44, 62, 80, 98, 116, 134, 152});

        public final int storageSize;
        public final int[] slotX;

        LunchboxTier(int storageSize, int[] slotX) {
            this.storageSize = storageSize;
            this.slotX = slotX;
        }

        public static LunchboxTier fromStorageSize(int size) {
            for (LunchboxTier t : values()) {
                if (t.storageSize == size) return t;
            }
            throw new IllegalArgumentException("No tier for storage size " + size);
        }
    }


    private final ItemStack storageItem;
    private final SimpleContainer storageInventory;
    @Getter
    private final int storageSize;
    private final int cols;
    @Getter
    private final int rows;

    private static final int[] GRID_X = {8, 26, 44, 62, 80, 98, 116, 134, 152};
    private static final int STORAGE_ROW_1_Y = 51;
    private static final int STORAGE_ROW_2_Y = 69;
    private static final int STORAGE_ROW_3_Y = 87;
    private static final int PLAYER_INV_ROW_1_Y = 109;

    public LunchboxContainer(int containerId, Inventory inventory, FriendlyByteBuf data) {
        this(containerId, inventory, data.readItem());
    }

    private static final int SLOTS_PER_ROW = 9;

    public LunchboxContainer(int containerId, Inventory playerInventory, ItemStack storItem) {
        super(SVOContainers.STORAGE_CONTAINER.get(), containerId);
        this.storageItem = storItem;

        this.storageSize = LunchboxItem.getStorageSize(storageItem);
        this.cols = Math.min(storageSize, SLOTS_PER_ROW);
        this.rows = (int) Math.ceil(storageSize / (double) SLOTS_PER_ROW);
        this.storageInventory = new SimpleContainer(storageSize);

        loadFromNBT(storageItem);

        LunchboxTier tier = LunchboxTier.fromStorageSize(storageSize);
        for (int i = 0; i < storageSize; i++) {
            int x = tier.slotX[i];
            this.addSlot(new Slot(storageInventory, i, x, 20) {
                @Override
                public boolean mayPlace(ItemStack stack) {
                    return !(stack.getItem() instanceof LunchboxItem);
                }
            });
        }
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    private NonNullList<ItemStack> getStorageItems() {
        NonNullList<ItemStack> items = NonNullList.withSize(storageInventory.getContainerSize(), ItemStack.EMPTY);
        for (int i = 0; i < storageInventory.getContainerSize(); i++) {
            items.set(i, storageInventory.getItem(i));
        }
        return items;
    }

    private void loadFromNBT(ItemStack item) {
        NonNullList<ItemStack> items = NonNullList.withSize(storageSize, ItemStack.EMPTY);
        if (item.hasTag() && item.getTag().contains("Items", Tag.TAG_LIST)) {
            ContainerHelper.loadAllItems(item.getTag(), items);
        }
        for (int i = 0; i < items.size(); i++) {
            storageInventory.setItem(i, items.get(i));
        }
    }

    public void saveToNBT() {
        CompoundTag tag = storageItem.getOrCreateTag();
        ContainerHelper.saveAllItems(tag, (NonNullList<ItemStack>) getStorageItems());
        tag.putInt("StorageSize", storageSize);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(i);

        if (slot != null && slot.hasItem()) {
            ItemStack slotStack = slot.getItem();
            itemStack = slotStack.copy();

            if (i < storageSize) {
                if (!this.moveItemStackTo(slotStack, storageSize, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(slotStack, 0, storageSize, false)) {
                return ItemStack.EMPTY;
            }
            if (slotStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        saveToNBT();
        return itemStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    private void addPlayerInventory(Inventory inventory) {
        for (int i = 0; i < 3; i++) {
            int y = PLAYER_INV_ROW_1_Y + i * 18;
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(inventory, j + i * 9 + 9, GRID_X[j], y));
            }
        }
    }

    private void addPlayerHotbar(Inventory inventory) {
        int y = PLAYER_INV_ROW_1_Y + 3 * 18 + 4; // +4px standard chest gap
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(inventory, i, GRID_X[i], y));
        }
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        if (!player.level().isClientSide) {
            saveToNBT();
        }
    }
}
