package com.CuteNekoDragon.Core.common.recipe;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

import com.CuteNekoDragon.Core.common.data.SVORecipeSeralizers;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;

public class NBTShapedRecipeBuilder implements RecipeBuilder {

    private final RecipeCategory category;
    private final Item result;
    private final int count;
    private final List<String> rows = new ArrayList<>();
    private final Map<Character, Ingredient> key = new LinkedHashMap<>();
    private final Advancement.Builder advancement = Advancement.Builder.advancement();
    private String group = "";
    private CraftingBookCategory bookCategory = CraftingBookCategory.MISC;
    private boolean showNotification = true;

    private Ingredient baseItem = Ingredient.EMPTY;
    private final List<String> copyTags = new ArrayList<>();
    private final Map<String, String> retargetTags = new LinkedHashMap<>();
    private CompoundTag setTag = new CompoundTag();

    protected NBTShapedRecipeBuilder(RecipeCategory category, ItemLike result, int count) {
        this.category = category;
        this.result = result.asItem();
        this.count = count;
    }

    public static NBTShapedRecipeBuilder shaped(RecipeCategory category, ItemLike result) {
        return new NBTShapedRecipeBuilder(category, result, 1);
    }

    public static NBTShapedRecipeBuilder shaped(RecipeCategory category, ItemLike result, int count) {
        return new NBTShapedRecipeBuilder(category, result, count);
    }

    public NBTShapedRecipeBuilder pattern(String row) {
        this.rows.add(row);
        return this;
    }

    public NBTShapedRecipeBuilder define(Character symbol, TagKey<Item> tag) {
        return this.define(symbol, Ingredient.of(tag));
    }

    public NBTShapedRecipeBuilder define(Character symbol, ItemLike item) {
        return this.define(symbol, Ingredient.of(item));
    }

    public NBTShapedRecipeBuilder define(Character symbol, Ingredient ingredient) {
        if (this.key.containsKey(symbol)) {
            throw new IllegalArgumentException("Symbol '" + symbol + "' is already defined");
        }
        if (symbol == ' ') {
            throw new IllegalArgumentException("Symbol ' ' (whitespace) is reserved and cannot be defined");
        }
        this.key.put(symbol, ingredient);
        return this;
    }

    public NBTShapedRecipeBuilder group(String group) {
        this.group = group;
        return this;
    }

    public NBTShapedRecipeBuilder category(CraftingBookCategory category) {
        this.bookCategory = category;
        return this;
    }

    public NBTShapedRecipeBuilder showNotification(boolean show) {
        this.showNotification = show;
        return this;
    }

    public NBTShapedRecipeBuilder baseItem(ItemLike item) {
        this.baseItem = Ingredient.of(item);
        return this;
    }

    public NBTShapedRecipeBuilder baseItem(TagKey<Item> tag) {
        this.baseItem = Ingredient.of(tag);
        return this;
    }

    public NBTShapedRecipeBuilder baseItem(Ingredient ingredient) {
        this.baseItem = ingredient;
        return this;
    }

    public NBTShapedRecipeBuilder copyTag(String... tagNames) {
        this.copyTags.addAll(Arrays.asList(tagNames));
        return this;
    }

    public NBTShapedRecipeBuilder retargetTag(String from, String to) {
        this.retargetTags.put(from, to);
        return this;
    }

    public NBTShapedRecipeBuilder setTag(CompoundTag tag) {
        this.setTag = tag;
        return this;
    }

    /** Convenience for setting a single key on the output's hand-written tag. */
    public NBTShapedRecipeBuilder setTag(String key, Tag value) {
        this.setTag.put(key, value);
        return this;
    }

    @Override
    public NBTShapedRecipeBuilder unlockedBy(String criterionName, CriterionTriggerInstance criterionTrigger) {
        this.advancement.addCriterion(criterionName, criterionTrigger);
        return this;
    }

    @Override
    public Item getResult() {
        return this.result;
    }

    @Override
    public void save(Consumer<FinishedRecipe> finishedRecipeConsumer, ResourceLocation id) {
        this.ensureValid(id);
        this.advancement
                .parent(RecipeBuilder.ROOT_RECIPE_ADVANCEMENT)
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
                .rewards(AdvancementRewards.Builder.recipe(id))
                .requirements(RequirementsStrategy.OR);

        finishedRecipeConsumer.accept(new Result(
                id, this.result, this.count, this.group, this.bookCategory,
                this.rows, this.key, this.advancement,
                id.withPrefix("recipes/" + this.category.getFolderName() + "/"),
                this.baseItem, this.copyTags, this.retargetTags, this.setTag, this.showNotification));
    }

    private void ensureValid(ResourceLocation id) {
        if (this.rows.isEmpty()) {
            throw new IllegalStateException("No pattern is defined for shaped recipe " + id + "!");
        }

        Set<Character> unused = Sets.newHashSet(this.key.keySet());
        for (String row : this.rows) {
            for (int i = 0; i < row.length(); i++) {
                char c = row.charAt(i);
                if (!this.key.containsKey(c) && c != ' ') {
                    throw new IllegalStateException("Pattern in recipe " + id + " uses undefined symbol '" + c + "'");
                }
                unused.remove(c);
            }
        }
        if (!unused.isEmpty()) {
            throw new IllegalStateException("Ingredients are defined but not used in pattern for recipe " + id);
        }
        if (this.rows.size() == 1 && this.rows.get(0).length() == 1) {
            throw new IllegalStateException(
                    "Shaped recipe " + id + " only takes in a single item - should it be a shapeless recipe instead?");
        }
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + id);
        }
    }

    public static class Result implements FinishedRecipe {

        private final ResourceLocation id;
        private final Item result;
        private final int count;
        private final String group;
        private final CraftingBookCategory category;
        private final List<String> pattern;
        private final Map<Character, Ingredient> key;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;
        private final Ingredient baseItem;
        private final List<String> copyTags;
        private final Map<String, String> retargetTags;
        private final CompoundTag setTag;
        private final boolean showNotification;

        public Result(ResourceLocation id, Item result, int count, String group, CraftingBookCategory category,
                      List<String> pattern, Map<Character, Ingredient> key, Advancement.Builder advancement,
                      ResourceLocation advancementId, Ingredient baseItem, List<String> copyTags,
                      Map<String, String> retargetTags, CompoundTag setTag, boolean showNotification) {
            this.id = id;
            this.result = result;
            this.count = count;
            this.group = group;
            this.category = category;
            this.pattern = pattern;
            this.key = key;
            this.advancement = advancement;
            this.advancementId = advancementId;
            this.baseItem = baseItem;
            this.copyTags = copyTags;
            this.retargetTags = retargetTags;
            this.setTag = setTag;
            this.showNotification = showNotification;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            if (!this.group.isEmpty()) {
                json.addProperty("group", this.group);
            }
            json.addProperty("category", this.category.getSerializedName());

            JsonArray patternArray = new JsonArray();
            for (String row : this.pattern) {
                patternArray.add(row);
            }
            json.add("pattern", patternArray);

            JsonObject keyJson = new JsonObject();
            for (Map.Entry<Character, Ingredient> entry : this.key.entrySet()) {
                keyJson.add(String.valueOf(entry.getKey()), entry.getValue().toJson());
            }
            json.add("key", keyJson);

            JsonObject resultJson = new JsonObject();
            resultJson.addProperty("item", ForgeRegistries.ITEMS.getKey(this.result).toString());
            if (this.count != 1) {
                resultJson.addProperty("count", this.count);
            }
            json.add("result", resultJson);

            if (!this.showNotification) {
                json.addProperty("show_notification", false);
            }

            if (this.baseItem != Ingredient.EMPTY) {
                json.add("base_item", this.baseItem.toJson());
            }

            if (!this.copyTags.isEmpty()) {
                JsonArray tagArray = new JsonArray();
                this.copyTags.forEach(tagArray::add);
                json.add("copy_tags", tagArray);
            }

            if (!this.retargetTags.isEmpty()) {
                JsonObject retargetJson = new JsonObject();
                this.retargetTags.forEach(retargetJson::addProperty);
                json.add("retarget_tags", retargetJson);
            }

            if (this.setTag != null && !this.setTag.isEmpty()) {
                json.addProperty("set_tag", this.setTag.toString());
            }
        }

        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return SVORecipeSeralizers.NBT_SHAPED.get();
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}
