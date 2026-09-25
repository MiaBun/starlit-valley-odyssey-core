package com.CuteNekoDragon.Core.common.recipe.serializers;

import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;

import com.CuteNekoDragon.Core.common.item.SackItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class AbstractSackUpgradeRecipe extends ShapedRecipe {

    public static final String TAG_OVERFLOW = "SVOCore_PendingOverflow";

    protected AbstractSackUpgradeRecipe(ShapedRecipe compose) {
        super(compose.getId(), compose.getGroup(), compose.category(), compose.getRecipeWidth(),
                compose.getRecipeHeight(), compose.getIngredients(), compose.getResultItem(RegistryAccess.EMPTY));
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public boolean matches(CraftingContainer inv, Level level) {
        return super.matches(inv, level) && findSack(inv).isPresent();
    }

    protected Optional<ItemStack> findSack(CraftingContainer inv) {
        for (int slot = 0; slot < inv.getContainerSize(); slot++) {
            ItemStack stack = inv.getItem(slot);
            if (stack.getItem() instanceof SackItem) {
                return Optional.of(stack);
            }
        }
        return Optional.empty();
    }

    @Override
    public ItemStack assemble(CraftingContainer inv, RegistryAccess registryAccess) {
        ItemStack sack = findSack(inv).orElse(ItemStack.EMPTY);
        List<ItemStack> sackContents = SackItem.getContents(sack).collect(Collectors.toList());

        ItemStack result = super.assemble(inv, registryAccess).copy();
        List<ItemStack> leftover = new ArrayList<>();

        populateResult(result, sackContents, leftover);

        if (!leftover.isEmpty()) {
            ListTag overflowTag = new ListTag();
            for (ItemStack stack : leftover) {
                CompoundTag stackTag = new CompoundTag();
                stack.save(stackTag);
                overflowTag.add(stackTag);
            }
            result.getOrCreateTag().put(TAG_OVERFLOW, overflowTag);
        }

        return result;
    }

    protected abstract void populateResult(ItemStack result, List<ItemStack> sackContents, List<ItemStack> leftover);

    protected static void packMatching(ItemStack result, List<ItemStack> sackContents, List<ItemStack> leftover,
                                       Predicate<ItemStack> keep, int storageSize, String storageSizeTag) {
        NonNullList<ItemStack> slots = NonNullList.withSize(storageSize, ItemStack.EMPTY);
        int nextSlot = 0;

        for (ItemStack stack : sackContents) {
            if (keep.test(stack) && nextSlot < storageSize) {
                slots.set(nextSlot++, stack);
            } else {
                leftover.add(stack);
            }
        }

        CompoundTag tag = result.getOrCreateTag();
        tag.putInt(storageSizeTag, storageSize);
        ContainerHelper.saveAllItems(tag, slots);
    }
}
