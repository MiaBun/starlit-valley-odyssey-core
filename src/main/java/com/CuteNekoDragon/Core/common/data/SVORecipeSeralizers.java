package com.CuteNekoDragon.Core.common.data;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.common.recipe.NBTShapedRecipe.NbtShapedSerializer;

public class SVORecipeSeralizers {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister
            .create(ForgeRegistries.RECIPE_SERIALIZERS, SVOCore.MOD_ID);

    public static final RegistryObject<NbtShapedSerializer> NBT_SHAPED = RECIPE_SERIALIZERS.register("nbt_shaped",
            NbtShapedSerializer::new);
}
