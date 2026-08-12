package com.CuteNekoDragon.Core.common.data;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import com.CuteNekoDragon.Core.SVOCore;

public class SVORecipeSeralizers {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister
            .create(ForgeRegistries.RECIPE_SERIALIZERS, SVOCore.MOD_ID);
}
