package com.CuteNekoDragon.Core.common.recipe.builders;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;

public class SackUpgradeRecipeBuilder implements RecipeBuilder {

    private final RecipeSerializer<?> serializer;
    private final Item result;
    private final int count;
    private final List<String> rows = new ArrayList<>();
    private final Map<Character, Ingredient> key = new LinkedHashMap<>();
    private final Advancement.Builder advancement = Advancement.Builder.advancement();
    @Nullable
    private String group;
    @Nullable
    private CompoundTag resultNbt;

    public SackUpgradeRecipeBuilder(RecipeSerializer<?> serializer, ItemLike result, int count) {
        this.serializer = serializer;
        this.result = result.asItem();
        this.count = count;
    }

    public static SackUpgradeRecipeBuilder shaped(RecipeSerializer<?> serializer, ItemLike result) {
        return new SackUpgradeRecipeBuilder(serializer, result, 1);
    }

    public static SackUpgradeRecipeBuilder shaped(RecipeSerializer<?> serializer, ItemLike result, int count) {
        return new SackUpgradeRecipeBuilder(serializer, result, count);
    }

    public static SackUpgradeRecipeBuilder shaped(RecipeSerializer<?> serializer, ItemStack result) {
        SackUpgradeRecipeBuilder builder = new SackUpgradeRecipeBuilder(serializer, result.getItem(), result.getCount());
        if (result.hasTag()) {
            builder.nbt(result.getTag());
        }
        return builder;
    }

    public SackUpgradeRecipeBuilder nbt(CompoundTag tag) {
        if (this.resultNbt == null) {
            this.resultNbt = new CompoundTag();
        }
        this.resultNbt.merge(tag.copy());
        return this;
    }

    public SackUpgradeRecipeBuilder pattern(String row) {
        if (!this.rows.isEmpty() && row.length() != this.rows.get(0).length()) {
            throw new IllegalArgumentException("Pattern must be the same width on every line: " + row);
        }
        this.rows.add(row);
        return this;
    }

    public SackUpgradeRecipeBuilder define(Character symbol, TagKey<Item> tag) {
        return define(symbol, Ingredient.of(tag));
    }

    public SackUpgradeRecipeBuilder define(Character symbol, ItemLike item) {
        return define(symbol, Ingredient.of(item));
    }

    public SackUpgradeRecipeBuilder define(Character symbol, Ingredient ingredient) {
        if (this.key.containsKey(symbol)) {
            throw new IllegalArgumentException("Symbol '" + symbol + "' is already defined");
        }
        if (symbol == ' ') {
            throw new IllegalArgumentException("Symbol ' ' (whitespace) is reserved and cannot be defined");
        }
        this.key.put(symbol, ingredient);
        return this;
    }

    public SackUpgradeRecipeBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public SackUpgradeRecipeBuilder unlockedBy(String name, CriterionTriggerInstance criterion) {
        this.advancement.addCriterion(name, criterion);
        return this;
    }

    @Override
    public Item getResult() {
        return this.result;
    }

    @Override
    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        ensureValid(id);
        this.advancement
                .parent(ResourceLocation.withDefaultNamespace("recipes/root"))
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
                .requirements(RequirementsStrategy.OR);

        consumer.accept(new Result(id, this.serializer, this.group == null ? "" : this.group, this.rows, this.key,
                this.result, this.count, this.resultNbt == null ? null : this.resultNbt.copy(),
                this.advancement, id.withPrefix("recipes/svo_core_upgrades/")));
    }

    private void ensureValid(ResourceLocation id) {
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + id + " -- add at least one unlockedBy(...)");
        }
        if (this.rows.isEmpty()) {
            throw new IllegalStateException("No pattern is defined for recipe " + id);
        }

        Set<Character> usedSymbols = new HashSet<>();
        for (String row : this.rows) {
            for (char c : row.toCharArray()) {
                if (c != ' ') usedSymbols.add(c);
            }
        }
        for (Character symbol : this.key.keySet()) {
            if (!usedSymbols.contains(symbol)) {
                throw new IllegalStateException("Symbol '" + symbol + "' defined but not used in pattern for recipe " + id);
            }
        }
        for (Character symbol : usedSymbols) {
            if (!this.key.containsKey(symbol)) {
                throw new IllegalStateException("Symbol '" + symbol + "' used in pattern but not defined for recipe " + id);
            }
        }
    }

    private static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final RecipeSerializer<?> serializer;
        private final String group;
        private final List<String> pattern;
        private final Map<Character, Ingredient> key;
        private final Item result;
        private final int count;
        @Nullable
        private final CompoundTag resultNbt;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        Result(ResourceLocation id, RecipeSerializer<?> serializer, String group, List<String> pattern,
               Map<Character, Ingredient> key, Item result, int count, @Nullable CompoundTag resultNbt,
               Advancement.Builder advancement, ResourceLocation advancementId) {
            this.id = id;
            this.serializer = serializer;
            this.group = group;
            this.pattern = pattern;
            this.key = key;
            this.result = result;
            this.count = count;
            this.resultNbt = resultNbt;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            if (!this.group.isEmpty()) {
                json.addProperty("group", this.group);
            }

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
            if (this.count > 1) {
                resultJson.addProperty("count", this.count);
            }
            if (this.resultNbt != null && !this.resultNbt.isEmpty()) {
                resultJson.addProperty("nbt", this.resultNbt.toString());
            }
            json.add("result", resultJson);
        }

        @Override
        public RecipeSerializer<?> getType() {
            return this.serializer;
        }

        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        @Override
        @Nullable
        public JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        @Override
        @Nullable
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}