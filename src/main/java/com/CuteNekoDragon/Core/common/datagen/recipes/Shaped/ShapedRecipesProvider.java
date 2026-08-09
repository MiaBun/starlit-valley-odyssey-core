package com.CuteNekoDragon.Core.common.datagen.recipes.Shaped;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import com.CuteNekoDragon.Core.common.data.svogt.SVOMachines;

import java.util.function.Consumer;

public class ShapedRecipesProvider {

    public static void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SVOMachines.CHARKOAL_KILN.getItem())
                .pattern("ABA")
                .pattern("A A")
                .pattern("ABA")
                .define('A', ItemTags.LOGS)
                .define('B', Items.COPPER_INGOT)
                .unlockedBy("has_copper_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .save(consumer);
    }
}
