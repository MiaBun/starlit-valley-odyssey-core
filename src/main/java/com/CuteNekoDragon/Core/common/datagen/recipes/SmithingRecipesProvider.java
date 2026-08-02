package com.CuteNekoDragon.Core.common.datagen.recipes;

import dev.ithundxr.createnumismatics.content.backend.Coin;
import dev.ithundxr.createnumismatics.registry.NumismaticsItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.common.item.SVOSmithingTemplate;
import com.tterrag.registrate.util.entry.ItemEntry;

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
                Items.GOLDEN_SWORD)
                .unlocks("has_gold_ingot", has(Items.GOLD_INGOT))
                .save(consumer, SVOCore.id("smithing/golden_sword"));

        // Chainmail

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(NumismaticsItems.getCoin(Coin.COG)),
                        Ingredient.of(Items.LEATHER_HELMET),
                        Ingredient.of(Items.CHAIN),
                        RecipeCategory.COMBAT,
                        Items.CHAINMAIL_HELMET)
                .unlocks("has_chain", has(Items.CHAIN))
                .save(consumer, SVOCore.id("smithing/chainmail_helmet"));

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(NumismaticsItems.getCoin(Coin.COG)),
                        Ingredient.of(Items.LEATHER_CHESTPLATE),
                        Ingredient.of(Items.CHAIN),
                        RecipeCategory.COMBAT,
                        Items.CHAINMAIL_CHESTPLATE)
                .unlocks("has_chain", has(Items.CHAIN))
                .save(consumer, SVOCore.id("smithing/chainmail_chestplate"));

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(NumismaticsItems.getCoin(Coin.COG)),
                        Ingredient.of(Items.LEATHER_LEGGINGS),
                        Ingredient.of(Items.CHAIN),
                        RecipeCategory.COMBAT,
                        Items.CHAINMAIL_LEGGINGS)
                .unlocks("has_chain", has(Items.CHAIN))
                .save(consumer, SVOCore.id("smithing/chainmail_leggings"));

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(NumismaticsItems.getCoin(Coin.COG)),
                        Ingredient.of(Items.LEATHER_BOOTS),
                        Ingredient.of(Items.CHAIN),
                        RecipeCategory.COMBAT,
                        Items.CHAINMAIL_BOOTS)
                .unlocks("has_chain", has(Items.CHAIN))
                .save(consumer, SVOCore.id("smithing/chainmail_boots"));
    }
}
