package com.CuteNekoDragon.Core.common.item;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.Tag;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class ToolbeltItem extends Item implements ICurioItem {

    private final int StorageSize;
    public static final String TAG_StorageSize = "StorageSize";
    public static final String TAG_Items = "Items";
    public static final String TAG_Slot = "Slot";

    public ToolbeltItem(Properties properties, int slots) {
        super(properties);
        this.StorageSize = slots;
    }

    public static int getStorageSize(ItemStack stack) {
        if (stack.hasTag() && stack.getTag().contains(TAG_StorageSize)) {
            return Math.min(9, Math.max(3, stack.getTag().getInt(TAG_StorageSize)));
        }
        return 9;
    }

    public static int getSelectedSlot(ItemStack stack) {
        if (stack.hasTag() && stack.getTag().contains(TAG_Slot)) {
            return Math.min(9, Math.max(1, stack.getTag().getInt(TAG_Slot)));
        }
        return 1;
    }

    public boolean hasStoredItems(ItemStack stack) {
        if (!stack.hasTag() || !stack.getTag().contains(TAG_Items, Tag.TAG_LIST)) {
            return false;
        }
        NonNullList<ItemStack> items = NonNullList.withSize(getStorageSize(stack), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(stack.getTag(), items);

        for (ItemStack conatained : items) {
            if (!conatained.isEmpty()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return slotContext.identifier().equals("toolbelt");
    }

    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        return true;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {

        LivingEntity entity = slotContext.entity();
        Level level = entity.level();

        if (level.isClientSide) return;

        if (!stack.hasTag() || !stack.getTag().contains(TAG_StorageSize)) return;

        if (!stack.hasTag() || !stack.getTag().contains(TAG_Slot)) return;

        if (!stack.hasTag() || !stack.getTag().contains(TAG_Items)) return;
    }
}
