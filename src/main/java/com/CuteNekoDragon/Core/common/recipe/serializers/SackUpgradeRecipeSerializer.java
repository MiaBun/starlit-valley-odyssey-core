package com.CuteNekoDragon.Core.common.recipe.serializers;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class SackUpgradeRecipeSerializer<T extends AbstractSackUpgradeRecipe> implements RecipeSerializer<T> {


    private final Function<ShapedRecipe, T> factory;

    public SackUpgradeRecipeSerializer(Function<ShapedRecipe, T> factory) {
        this.factory = factory;
    }

    @Override
    public T fromJson(ResourceLocation id, JsonObject json) {
        return factory.apply(RecipeSerializer.SHAPED_RECIPE.fromJson(id, json));
    }

    @Override
    public T fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
        return factory.apply(RecipeSerializer.SHAPED_RECIPE.fromNetwork(id, buf));
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf, T recipe) {
        RecipeSerializer.SHAPED_RECIPE.toNetwork(buf, recipe);
    }
}
