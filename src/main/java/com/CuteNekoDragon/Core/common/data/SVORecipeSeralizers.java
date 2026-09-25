package com.CuteNekoDragon.Core.common.data;

import com.CuteNekoDragon.Core.common.recipe.serializers.SackToBackpackRecipe;
import com.CuteNekoDragon.Core.common.recipe.serializers.SackToLunchboxRecipe;
import com.CuteNekoDragon.Core.common.recipe.serializers.SackUpgradeRecipeSerializer;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import com.CuteNekoDragon.Core.SVOCore;
import net.minecraftforge.registries.RegistryObject;

public class SVORecipeSeralizers {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister
            .create(ForgeRegistries.RECIPE_SERIALIZERS, SVOCore.MOD_ID);

    public static final RegistryObject<SackUpgradeRecipeSerializer<SackToBackpackRecipe>> SACK_TO_BACKPACK = RECIPE_SERIALIZERS.register("sack_to_backpack", () -> new SackUpgradeRecipeSerializer<>(SackToBackpackRecipe::new));

    public static final RegistryObject<SackUpgradeRecipeSerializer<SackToLunchboxRecipe>> SACK_TO_LUNCHBOX = RECIPE_SERIALIZERS.register("sack_to_lunchbox", () -> new SackUpgradeRecipeSerializer<>(SackToLunchboxRecipe::new));
}
