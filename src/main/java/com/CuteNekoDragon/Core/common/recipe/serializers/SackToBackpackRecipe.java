package com.CuteNekoDragon.Core.common.recipe.serializers;

import com.CuteNekoDragon.Core.common.data.SVORecipeSeralizers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.p3pp3rf1y.sophisticatedbackpacks.api.CapabilityBackpackWrapper;
import net.p3pp3rf1y.sophisticatedcore.util.InventoryHelper;

import java.util.List;

public class SackToBackpackRecipe extends AbstractSackUpgradeRecipe{

    public SackToBackpackRecipe(ShapedRecipe compose) {
        super(compose);
    }

    @Override
    protected void  populateResult(ItemStack result, List<ItemStack> sackContents, List<ItemStack> leftOver) {
        result.getCapability(CapabilityBackpackWrapper.getCapabilityInstance()).ifPresent(wrapped -> {
            wrapped.getInventoryHandler();
            leftOver.addAll(InventoryHelper.insertIntoInventory(sackContents, wrapped.getInventoryHandler(), false));
        });
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SVORecipeSeralizers.SACK_TO_BACKPACK.get();
    }
}

