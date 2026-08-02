package com.CuteNekoDragon.Core.common.datagen.recipes;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class recipeProvider extends RecipeProvider {

    public recipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {}
}
