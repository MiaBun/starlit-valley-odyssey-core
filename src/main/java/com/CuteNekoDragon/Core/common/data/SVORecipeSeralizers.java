package com.CuteNekoDragon.Core.common.data;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.common.recipe.serializers.*;

public class SVORecipeSeralizers {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister
            .create(ForgeRegistries.RECIPE_SERIALIZERS, SVOCore.MOD_ID);

    public static final RegistryObject<SackUpgradeRecipeSerializer<SackToBackpackRecipe>> SACK_TO_BACKPACK = RECIPE_SERIALIZERS
            .register("sack_to_backpack", () -> new SackUpgradeRecipeSerializer<>(SackToBackpackRecipe::new));

    public static final RegistryObject<SackUpgradeRecipeSerializer<SackToLunchboxRecipe>> SACK_TO_LUNCHBOX = RECIPE_SERIALIZERS
            .register("sack_to_lunchbox", () -> new SackUpgradeRecipeSerializer<>(SackToLunchboxRecipe::new));

    public static final RegistryObject<SackUpgradeRecipeSerializer<SackToToolbeltRecipe>> SACK_TO_TOOLBELT = RECIPE_SERIALIZERS
            .register("sack_to_toolbelt", () -> new SackUpgradeRecipeSerializer<>(SackToToolbeltRecipe::new));

    public static final RegistryObject<RecipeSerializer<KeepContentsSmithingRecipe>> SMITHING_KEEP_CONTENTS = RECIPE_SERIALIZERS
            .register("smithing_keep_contents", KeepContentsSmithingRecipe.Serializer::new);
}
