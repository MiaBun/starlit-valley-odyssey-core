package com.CuteNekoDragon.Core.common.recipe.builders;

import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class SackUpgradeRecipeBuilder implements RecipeBuilder {

    @Override
    public RecipeBuilder unlockedBy(String s, CriterionTriggerInstance criterionTriggerInstance) {
        return null;
    }

    @Override
    public RecipeBuilder group(@Nullable String s) {
        return null;
    }

    @Override
    public Item getResult() {
        return null;
    }

    @Override
    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation) {

    }
}
