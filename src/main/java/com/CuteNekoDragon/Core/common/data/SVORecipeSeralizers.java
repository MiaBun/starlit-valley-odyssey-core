package com.CuteNekoDragon.Core.common.data;

import com.CuteNekoDragon.Core.common.recipe.NBTShapedRecipe.NbtShapedSerializer;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import com.CuteNekoDragon.Core.SVOCore;
import net.minecraftforge.registries.RegistryObject;

public class SVORecipeSeralizers {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister
            .create(ForgeRegistries.RECIPE_SERIALIZERS, SVOCore.MOD_ID);

    public static final RegistryObject<NbtShapedSerializer> NBT_SHAPED = RECIPE_SERIALIZERS.register("nbt_shaped", NbtShapedSerializer::new);
}
