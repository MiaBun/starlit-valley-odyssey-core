package com.CuteNekoDragon.Core.common.datagen.recipes;

import com.CuteNekoDragon.Core.utils.recipes.SmithingRecipeHelper;
import dev.ithundxr.createnumismatics.content.backend.Coin;
import dev.ithundxr.createnumismatics.registry.NumismaticsItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.common.item.SVOSmithingTemplate;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static com.CuteNekoDragon.Core.common.data.items.SVOItems.UPGRADE_TEMPLATES;

public class SmithingRecipesProvider extends RecipeProvider {

    public SmithingRecipesProvider(PackOutput output) {
        super(output);
    }

    private static final Map<ItemLike, Item> CHAINMAIL_RECIPES = Map.of(
            Items.LEATHER_HELMET, Items.CHAINMAIL_HELMET,
            Items.LEATHER_CHESTPLATE, Items.CHAINMAIL_CHESTPLATE,
            Items.LEATHER_LEGGINGS, Items.CHAINMAIL_LEGGINGS,
            Items.LEATHER_BOOTS, Items.CHAINMAIL_BOOTS);

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ItemEntry<SVOSmithingTemplate> goldTemplate = UPGRADE_TEMPLATES.get("gold");

        SmithingRecipeHelper.makeSmithingRecipe(consumer, goldTemplate.get(), Items.IRON_SWORD, Items.GOLD_INGOT, Items.GOLDEN_SWORD);

        for (Map.Entry<ItemLike, Item> entry : CHAINMAIL_RECIPES.entrySet()) {
            ItemLike baseItem = entry.getKey();
            Item resultItem = entry.getValue();

            SmithingRecipeHelper.makeSmithingRecipe(consumer, NumismaticsItems.getCoin(Coin.COG), baseItem, Items.CHAIN, resultItem);
        }
    }
}
