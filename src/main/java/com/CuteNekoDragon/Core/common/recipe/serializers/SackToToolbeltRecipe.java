package com.CuteNekoDragon.Core.common.recipe.serializers;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;

import com.CuteNekoDragon.Core.common.data.SVORecipeSeralizers;
import com.CuteNekoDragon.Core.common.item.ToolbeltItem;

import java.util.List;

public class SackToToolbeltRecipe extends AbstractSackUpgradeRecipe {

    public SackToToolbeltRecipe(ShapedRecipe compose) {
        super(compose);
    }

    @Override
    protected void populateResult(ItemStack result, List<ItemStack> sackContents, List<ItemStack> leftover) {
        ToolbeltItem item = (ToolbeltItem) result.getItem();
        packMatching(result, sackContents, leftover, ItemStack::isDamageableItem,
                item.getBaseStorageSize(), ToolbeltItem.TAG_StorageSize);
        result.getOrCreateTag().putInt(ToolbeltItem.TAG_Slot, 1);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SVORecipeSeralizers.SACK_TO_TOOLBELT.get();
    }
}
