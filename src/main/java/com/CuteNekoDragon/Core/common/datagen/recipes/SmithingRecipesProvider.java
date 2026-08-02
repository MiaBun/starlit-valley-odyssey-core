package com.CuteNekoDragon.Core.common.datagen.recipes;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.common.item.SVOSmithingTemplate;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

import static com.CuteNekoDragon.Core.common.data.items.SVOItems.UPGRADE_TEMPLATES;

public class SmithingRecipesProvider extends RecipeProvider {

    public SmithingRecipesProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {

        ItemEntry<SVOSmithingTemplate> goldTemplate = UPGRADE_TEMPLATES.get("gold");
        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(goldTemplate.get()),
                Ingredient.of(Items.IRON_SWORD),
                Ingredient.of(Items.GOLD_INGOT),
                RecipeCategory.COMBAT,
                Items.GOLDEN_SWORD
        )
                .unlocks("has_gold_ingot", has(Items.GOLD_INGOT))
                .save(consumer, SVOCore.id("golden_sword_smithing"));
    }


}
