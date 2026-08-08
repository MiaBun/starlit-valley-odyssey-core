package com.CuteNekoDragon.Core.common.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

import com.CuteNekoDragon.Core.common.datagen.recipes.ShapedRecipesProvider;
import com.CuteNekoDragon.Core.common.datagen.recipes.SmithingRecipesProvider;

import java.util.function.Consumer;

public class SVORecipeProvider extends RecipeProvider {

    public SVORecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipesProvider.buildRecipes(consumer);
        SmithingRecipesProvider.buildRecipes(consumer);
    }
}
