package com.CuteNekoDragon.Core.common.datagen.recipes;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.common.data.SVOTags;
import com.CuteNekoDragon.Core.common.datagen.recipes.Shaped.ShapedRecipesProvider;
import com.CuteNekoDragon.Core.common.datagen.recipes.Smithing.SmithingRecipesProvider;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.CraftingBookCategory;

import java.util.function.Consumer;

@SuppressWarnings("removal")
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
