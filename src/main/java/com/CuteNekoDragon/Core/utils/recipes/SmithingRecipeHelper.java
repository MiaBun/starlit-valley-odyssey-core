package com.CuteNekoDragon.Core.utils.recipes;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

import com.CuteNekoDragon.Core.SVOCore;

import java.util.Objects;
import java.util.function.Consumer;

import static com.tterrag.registrate.providers.RegistrateRecipeProvider.has;

public class SmithingRecipeHelper {

    public static void makeSmithingRecipe(Consumer<FinishedRecipe> consumer, ItemLike template, ItemLike base,
                                          ItemLike addition, Item result) {
        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(template),
                Ingredient.of(base),
                Ingredient.of(addition),
                RecipeCategory.COMBAT,
                result)
                .unlocks("has_" + Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(addition.asItem())).getPath(),
                        has(addition))
                .save(consumer, SVOCore.id(
                        "smithing/" + Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(result.asItem())).getPath()));
    }
}
