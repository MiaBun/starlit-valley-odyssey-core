package com.CuteNekoDragon.Core.common.datagen.recipes;

import com.CuteNekoDragon.Core.common.datagen.recipes.Shaped.ShapedRecipesProvider;
import com.CuteNekoDragon.Core.common.datagen.recipes.Smithing.SmithingRecipesProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

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
