package com.CuteNekoDragon.Core.common.recipe.serializers;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;

import com.CuteNekoDragon.Core.common.data.SVORecipeSeralizers;
import com.CuteNekoDragon.Core.common.item.LunchboxItem;

import java.util.List;

public class SackToLunchboxRecipe extends AbstractSackUpgradeRecipe {

    public SackToLunchboxRecipe(ShapedRecipe compose) {
        super(compose);
    }

    @Override
    protected void populateResult(ItemStack result, List<ItemStack> sackContents, List<ItemStack> leftover) {
        LunchboxItem item = (LunchboxItem) result.getItem();
        packMatching(result, sackContents, leftover, ItemStack::isEdible,
                item.getBaseStorageSize(), LunchboxItem.TAG_StorageSize);
        result.getOrCreateTag().putInt(LunchboxItem.TAG_CooldownLength, item.getBaseCooldownLength());
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SVORecipeSeralizers.SACK_TO_LUNCHBOX.get();
    }
}
