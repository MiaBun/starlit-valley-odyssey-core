package com.CuteNekoDragon.Core.common.recipe.NBTShapedRecipe;

import com.CuteNekoDragon.Core.common.data.SVORecipeSeralizers;
import earth.terrarium.adastra.common.registry.ModRecipeSerializers;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class NBTShapedRecipe implements CraftingRecipe {

    private final ResourceLocation id;
    private final String group;
    private final CraftingBookCategory category;
    private final int width;
    private final int height;
    private final NonNullList<Ingredient> ingredients;
    private final ItemStack result;
    private final boolean showNotification;

    private final Ingredient baseItem;
    private final List<String> copyTags;
    private final Map<String, String> retargetTags;
    private final CompoundTag setTag;

    public NBTShapedRecipe(ResourceLocation id, String group, CraftingBookCategory category, int width, int height, NonNullList<Ingredient> ingredients, ItemStack result, boolean showNotification, Ingredient baseItem, List<String> copyTags, Map<String, String> retargetTags, CompoundTag setTag) {
        this.id = id;
        this.group = group;
        this.category = category;
        this.width = width;
        this.height = height;
        this.ingredients = ingredients;
        this.result = result;
        this.showNotification = showNotification;
        this.baseItem = baseItem;
        this.copyTags = copyTags;
        this.retargetTags = retargetTags;
        this.setTag = setTag;
    }


    @Override
    public boolean matches(CraftingContainer craftingContainer, Level level) {
        for (int i = 0; i <= craftingContainer.getWidth() - this.width; i++) {
            for (int j = 0; j <= craftingContainer.getHeight() - this.height; j++) {
                if (this.matchesAt(craftingContainer, i, j, true)) return true;
                if (this.matchesAt(craftingContainer, i, j, false)) return false;
            }
        }
        return false;
    }

    private boolean matchesAt(CraftingContainer container, int offsetX, int offsetY, boolean mirrored) {
        for (int i = 0; i < container.getWidth(); i++) {
            for (int j = 0; j < container.getHeight(); j++) {
                int k = i - offsetX;
                int l = j - offsetY;
                Ingredient ingredient = Ingredient.EMPTY;
                if (k >= 0 && l >= 0 && k < this.width && l < this.height) {
                    ingredient = mirrored
                            ? this.ingredients.get(this.width - k - 1 + l * this.width)
                            : this.ingredients.get(k + l * this.width);
                }
                if (!ingredient.test(container.getItem(i + j * container.getWidth()))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public ItemStack assemble(CraftingContainer craftingContainer, RegistryAccess registryAccess) {
        ItemStack output = this.result.copy();

        CompoundTag sourceTag = findBaseItemTag(craftingContainer);
        if (sourceTag != null) {
            CompoundTag outputTag = output.getOrCreateTag();

            for (String tagName : this.copyTags) {
                if (sourceTag.contains(tagName)) {
                    outputTag.put(tagName, sourceTag.get(tagName).copy());
                }
            }

            for (Map.Entry<String, String> entry : this.retargetTags.entrySet()) {
                String from = entry.getKey();
                String to = entry.getValue();
                if (sourceTag.contains(from)) {
                    outputTag.put(to, sourceTag.get(from).copy());
                }
            }
        }

        if (this.setTag != null && !this.setTag.isEmpty()) {
            output.getOrCreateTag().merge(this.setTag);
        }
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Nullable
    private CompoundTag findBaseItemTag(CraftingContainer container) {
        if (this.baseItem == Ingredient.EMPTY) {
            return null;
        }

        ItemStack fallback = null;
        for (int i = 0; i < container.getContainerSize(); i++) {
            ItemStack stack = container.getItem(i);
            if (stack.isEmpty() || !this.baseItem.test(stack)) continue;
            ;

            if (stack.hasTag()) {
                return stack.getTag();
            }
            if (fallback == null) {
                fallback = stack;
            }
        }
        return fallback != null ? fallback.getTag() : null;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.result;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SVORecipeSeralizers.NBT_SHAPED.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeType.CRAFTING;
    }

    @Override
    public String getGroup() {
        return this.group;
    }

    @Override
    public CraftingBookCategory category() {
        return this.category;
    }

    public boolean showNotification() {
        return this.showNotification;
    }

    public int getRecipeWidth() {
        return this.width;
    }

    public int getRecipeHeight() {
        return this.height;
    }

    public Ingredient getBaseItem() {
        return this.baseItem;
    }

    public List<String> getCopyTags() {
        return this.copyTags;
    }

    public Map<String, String> getRetargetTags() {
        return this.retargetTags;
    }

    public CompoundTag getSetTag() {
        return this.setTag;

    }
}
