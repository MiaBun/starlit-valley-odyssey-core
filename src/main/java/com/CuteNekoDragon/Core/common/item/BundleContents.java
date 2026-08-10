package com.CuteNekoDragon.Core.common.item;


import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.ShulkerBoxBlock;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public final class BundleContents{

    public static final String TAG_ITEMS = "Items";
    public static final String TAG_SELECTED = "SelectedItem";

    public static final float BUNDLE_IN_BUNDLE_WEIGHT = 1f / 16f;

    private BundleContents() {

    }

    public static List<ItemStack> getItems(ItemStack bundle) {
        CompoundTag tag = bundle.getTag();
        if (tag == null || !tag.contains(TAG_ITEMS)) return new ArrayList<>();
        ListTag list = tag.getList(TAG_ITEMS, 10);
        List<ItemStack> items = new ArrayList<>(list.size());
        for (int i = 0; i < list.size(); i++) {
            items.add(ItemStack.of(list.getCompound(i)));
        }
        return items;
    }

    public static int getSelectedItemindex (ItemStack bundle) {
        CompoundTag tag = bundle.getTag();
        if (tag == null || !tag.contains(TAG_SELECTED)) {
            return -1;
        }
        return tag.getInt(TAG_SELECTED);
    }

    @Nullable
    public static ItemStack getSelectedItem(ItemStack bundle) {
        int index = getSelectedItemindex(bundle);
        List<ItemStack> items = getItems(bundle);
        if (index < 0 || index >= items.size()) {
            return null;
        }
        return items.get(index);
    }

    public static boolean isEmpty(ItemStack bundle) {
        CompoundTag tag = bundle.getTag();
        return tag == null || !tag.contains(TAG_ITEMS) || tag.getList(TAG_ITEMS, 10).isEmpty();
    }


    public static float getWeight(ItemStack bundle) {
        float weight = 0f;
        for (ItemStack item : getItems(bundle)) {
            weight += getItemWeight(bundle) * item.getCount();
        }
        return weight;
    }

    private static float getItemWeight (ItemStack item) {
        if (isBundle(item)) {
            return getWeight(item) + BUNDLE_IN_BUNDLE_WEIGHT;
        }
        if (item.getItem() instanceof BlockItem blockItem
                && blockItem.getBlock() instanceof BeehiveBlock
                && item.hasTag()) {
            assert item.getTag() != null;
            if (item.getTag().contains("BlockEntityTag")
                    && item.getTag().getCompound("BlockEntityTag").contains("Bees")
                    && !item.getTag().getCompound("BlockEntityTag").getList("Bees", 10).isEmpty()) {
                return 1f;
            }
        }
        return 1f / item.getMaxStackSize();
    }
    public static boolean isBundle(ItemStack stack) {
        return stack.getItem() instanceof BundleItem;
    }

    public static boolean canItemBeInBundle(ItemStack stack) {
        if(stack.isEmpty()) return false;
        if (stack.getItem() instanceof BlockItem blockItem) {
            return !(blockItem.getBlock() instanceof ShulkerBoxBlock);
        }
        return true;
    }

    public static void writeItems(ItemStack bundle, List<ItemStack> items) {
        ListTag list = new ListTag();
        for (ItemStack item : items) {
            list.add(item.save(new CompoundTag()));
        }
        bundle.getOrCreateTag().put(TAG_ITEMS, list);
    }

    public static void setSelectedItem(ItemStack bundle, int index) {
        List<ItemStack> items = getItems(bundle);
        int current = getSelectedItemindex(bundle);
        int newIndex = (current != index && index >= 0 && index < items.size()) ? index : -1;
        if (newIndex == -1) {
            bundle.getOrCreateTag().remove(TAG_SELECTED);
        } else {
            bundle.getOrCreateTag().putInt(TAG_SELECTED, newIndex);
        }
    }

    private static void clearSelection(ItemStack bundle) {
        if (bundle.hasTag()) {
            assert bundle.getTag() != null;
            bundle.getTag().remove(TAG_SELECTED);
        }
    }

    public static int tryInsert(ItemStack bundle, ItemStack toAdd) {
        if (!canItemBeInBundle(toAdd)) {
            return 0;
        }

        float itemWeight = getItemWeight(toAdd);
        float currentWeight = getWeight(bundle);
        float remaining = 1f - currentWeight;
        if (remaining <= 0f || itemWeight <= 0f) {
            return 0;
        }

        int maxByWeight = (int) Math.floor(remaining / itemWeight + 1.0e-7);
        int amountToAdd = Math.min(toAdd.getCount(), Math.max(maxByWeight, 0));
        if (amountToAdd <= 0) {
            return 0;
        }
        List<ItemStack> items = getItems(bundle);

        int existingIndex = -1;
        if (toAdd.isStackable()) {
            for (int i = 0; i < items.size(); i++) {
                if (ItemStack.isSameItemSameTags(items.get(i), toAdd)) {
                    existingIndex = i;
                    break;
                }
            }
        }

        if (existingIndex != -1) {
            ItemStack existing = items.remove(existingIndex);
            existing.grow(amountToAdd);
            items.add(0, existing);
        }

        toAdd.shrink(amountToAdd);
        writeItems(bundle, items);
        return amountToAdd;
    }

    public static int tryTransfer(ItemStack bundle, Slot slot, Player player) {
        ItemStack slotStack = slot.getItem();
        if (!canItemBeInBundle(slotStack)) {
            return 0;
        }
        float itemWeight = getItemWeight(slotStack);
        float remaining = 1f - getWeight(bundle);
        int maxByWeight = itemWeight > 0f ? (int) Math.floor(remaining / itemWeight + 1.0e-7) : 0;
        if (maxByWeight <= 0) {
            return 0;
        }
        int takeCount = Math.min(slotStack.getCount(), maxByWeight);
        ItemStack taken = slot.safeTake(takeCount, takeCount, player);
        if (taken.isEmpty()) {
            return 0;
        }
        int inserted = tryInsert(bundle, taken);
        if (!taken.isEmpty()) {
            slot.set(taken);
        }
        return inserted;
    }

    @Nullable
    public static ItemStack removeOne(ItemStack bundle) {
        List<ItemStack> items = getItems(bundle);
        if (items.isEmpty()) {
            return null;
        }

        int selected = getSelectedItemindex(bundle);
        int removeIndex = (selected < 0 || selected >= items.size()) ? 0 : selected;
        ItemStack removed = items.remove(removeIndex).copy();

        writeItems(bundle, items);
        clearSelection(bundle);
        return removed;
    }
    public static void clear(ItemStack bundle) {
        if (bundle.hasTag()) {
            assert bundle.getTag() != null;
            bundle.getTag().remove(TAG_ITEMS);
            bundle.getTag().remove(TAG_SELECTED);
        }
    }
}
