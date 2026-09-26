package com.CuteNekoDragon.Core.common.recipe.serializers;

import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

import com.CuteNekoDragon.Core.common.data.SVORecipeSeralizers;
import com.google.gson.JsonObject;

public class KeepContentsSmithingRecipe extends SmithingTransformRecipe {

    public static final String TAG_Items = "Items";

    final Ingredient template;
    final Ingredient base;
    final Ingredient addition;
    final ItemStack result;

    public KeepContentsSmithingRecipe(ResourceLocation id, Ingredient template, Ingredient base,
                                      Ingredient addition, ItemStack result) {
        super(id, template, base, addition, result);
        this.template = template;
        this.base = base;
        this.addition = addition;
        this.result = result;
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess access) {
        ItemStack out = this.result.copy();
        CompoundTag baseTag = container.getItem(1).getTag();
        if (baseTag != null && baseTag.contains(TAG_Items, Tag.TAG_LIST)) {
            out.getOrCreateTag().put(TAG_Items, baseTag.getList(TAG_Items, Tag.TAG_COMPOUND).copy());
        }
        return out;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SVORecipeSeralizers.SMITHING_KEEP_CONTENTS.get();
    }

    public static class Serializer implements RecipeSerializer<KeepContentsSmithingRecipe> {

        @Override
        public KeepContentsSmithingRecipe fromJson(ResourceLocation id, JsonObject json) {
            Ingredient template = Ingredient.fromJson(GsonHelper.getNonNull(json, "template"));
            Ingredient base = Ingredient.fromJson(GsonHelper.getNonNull(json, "base"));
            Ingredient addition = Ingredient.fromJson(GsonHelper.getNonNull(json, "addition"));
            ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
            return new KeepContentsSmithingRecipe(id, template, base, addition, result);
        }

        @Override
        public KeepContentsSmithingRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            Ingredient template = Ingredient.fromNetwork(buf);
            Ingredient base = Ingredient.fromNetwork(buf);
            Ingredient addition = Ingredient.fromNetwork(buf);
            ItemStack result = buf.readItem();
            return new KeepContentsSmithingRecipe(id, template, base, addition, result);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, KeepContentsSmithingRecipe recipe) {
            recipe.template.toNetwork(buf);
            recipe.base.toNetwork(buf);
            recipe.addition.toNetwork(buf);
            buf.writeItem(recipe.result);
        }
    }
}
