package com.CuteNekoDragon.Core.common.recipe.NBTShapedRecipe;

import com.CuteNekoDragon.Core.common.recipe.NBTShapedRecipeBuilder;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.crafting.CraftingHelper;

import java.util.*;

public class NbtShapedSerializer implements RecipeSerializer<NBTShapedRecipe> {

    private static final int MAX_WIDTH = 3;
    private static final int MAX_HEIGHT = 3;

    @Override
    public NBTShapedRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
        String group = GsonHelper.getAsString(json, "group", "");
        CraftingBookCategory category = CraftingBookCategory.CODEC.byName(
                GsonHelper.getAsString(json, "category", null), CraftingBookCategory.MISC);

        Map<String, Ingredient> key = keyFromJson(GsonHelper.getAsJsonObject(json, "key"));
        String[] pattern = shrink(patternFromJson(GsonHelper.getAsJsonArray(json, "pattern")));
        int width = pattern[0].length();
        int height = pattern.length;
        NonNullList<Ingredient> ingredients = dissolvePattern(pattern, key, width, height);

        ItemStack result = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "result"), true);
        boolean showNotification = GsonHelper.getAsBoolean(json, "show_notification", true);

        Ingredient baseItem = json.has("base_item")
                ? Ingredient.fromJson(json.get("base_item"))
                : Ingredient.EMPTY;

        List<String> copyTags = new ArrayList<>();
        if (json.has("copy_tags")) {
            for (JsonElement element : GsonHelper.getAsJsonArray(json, "copy_tags")) {
                copyTags.add(element.getAsString());
            }
        }

        Map<String, String> retargetTags = new LinkedHashMap<>();
        if (json.has("retarget_tags")) {
            JsonObject retargetJson = GsonHelper.getAsJsonObject(json, "retarget_tags");
            for (Map.Entry<String, JsonElement> entry : retargetJson.entrySet()) {
                retargetTags.put(entry.getKey(), entry.getValue().getAsString());
            }
        }

        CompoundTag setTag = new CompoundTag();
        if (json.has("set_tag")) {
            String snbt = GsonHelper.getAsString(json, "set_tag");
            try {
                setTag = TagParser.parseTag(snbt);
            } catch (CommandSyntaxException e) {
                throw new JsonSyntaxException("Invalid set_tag NBT for recipe " + recipeId + ": " + e.getMessage());
            }
        }

        return new NBTShapedRecipe(recipeId, group, category, width, height, ingredients,
                result, showNotification, baseItem, copyTags, retargetTags, setTag);
    }

    @Override
    public NBTShapedRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
        int width = buffer.readVarInt();
        int height = buffer.readVarInt();
        String group = buffer.readUtf();
        CraftingBookCategory category = buffer.readEnum(CraftingBookCategory.class);

        NonNullList<Ingredient> ingredients = NonNullList.withSize(width * height, Ingredient.EMPTY);
        for (int i = 0; i < ingredients.size(); i++) {
            ingredients.set(i, Ingredient.fromNetwork(buffer));
        }

        ItemStack result = buffer.readItem();
        boolean showNotification = buffer.readBoolean();

        Ingredient baseItem = Ingredient.fromNetwork(buffer);

        int copyCount = buffer.readVarInt();
        List<String> copyTags = new ArrayList<>(copyCount);
        for (int i = 0; i < copyCount; i++) {
            copyTags.add(buffer.readUtf());
        }

        int retargetCount = buffer.readVarInt();
        Map<String, String> retargetTags = new LinkedHashMap<>();
        for (int i = 0; i < retargetCount; i++) {
            String from = buffer.readUtf();
            String to = buffer.readUtf();
            retargetTags.put(from, to);
        }

        CompoundTag setTag = buffer.readNbt();
        if (setTag == null) setTag = new CompoundTag();

        return new NBTShapedRecipe(recipeId, group, category, width, height, ingredients,
                result, showNotification, baseItem, copyTags, retargetTags, setTag);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, NBTShapedRecipe recipe) {
        buffer.writeVarInt(recipe.getRecipeWidth());
        buffer.writeVarInt(recipe.getRecipeHeight());
        buffer.writeUtf(recipe.getGroup());
        buffer.writeEnum(recipe.category());

        for (Ingredient ingredient : recipe.getIngredients()) {
            ingredient.toNetwork(buffer);
        }

        buffer.writeItem(recipe.getResultItem(null));
        buffer.writeBoolean(recipe.showNotification());

        recipe.getBaseItem().toNetwork(buffer);

        buffer.writeVarInt(recipe.getCopyTags().size());
        for (String tag : recipe.getCopyTags()) {
            buffer.writeUtf(tag);
        }

        buffer.writeVarInt(recipe.getRetargetTags().size());
        for (Map.Entry<String, String> entry : recipe.getRetargetTags().entrySet()) {
            buffer.writeUtf(entry.getKey());
            buffer.writeUtf(entry.getValue());
        }

        buffer.writeNbt(recipe.getSetTag());
    }

    private static String[] patternFromJson(JsonArray patternArray) {
        String[] pattern = new String[patternArray.size()];
        if (pattern.length > MAX_HEIGHT) {
            throw new JsonSyntaxException("Invalid pattern: too many rows, " + MAX_HEIGHT + " is maximum");
        } else if (pattern.length == 0) {
            throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");
        }

        for (int i = 0; i < pattern.length; i++) {
            String row = GsonHelper.convertToString(patternArray.get(i), "pattern[" + i + "]");
            if (row.length() > MAX_WIDTH) {
                throw new JsonSyntaxException("Invalid pattern: too many columns, " + MAX_WIDTH + " is maximum");
            }
            if (i > 0 && pattern[0].length() != row.length()) {
                throw new JsonSyntaxException("Invalid pattern: each row must be the same width");
            }
            pattern[i] = row;
        }
        return pattern;
    }

    private static Map<String, Ingredient> keyFromJson(JsonObject json) {
        Map<String, Ingredient> map = Maps.newHashMap();
        for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
            if (entry.getKey().length() != 1) {
                throw new JsonSyntaxException("Invalid key entry: '" + entry.getKey()
                        + "' is an invalid symbol (must be 1 character only).");
            }
            if (" ".equals(entry.getKey())) {
                throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
            }
            map.put(entry.getKey(), Ingredient.fromJson(entry.getValue()));
        }
        map.put(" ", Ingredient.EMPTY);
        return map;
    }

    private static NonNullList<Ingredient> dissolvePattern(String[] pattern, Map<String, Ingredient> keys,
                                                           int width, int height) {
        NonNullList<Ingredient> ingredients = NonNullList.withSize(width * height, Ingredient.EMPTY);
        Set<String> unused = Sets.newHashSet(keys.keySet());
        unused.remove(" ");

        for (int row = 0; row < pattern.length; row++) {
            for (int col = 0; col < pattern[row].length(); col++) {
                String symbol = pattern[row].substring(col, col + 1);
                Ingredient ingredient = keys.get(symbol);
                if (ingredient == null) {
                    throw new JsonSyntaxException("Pattern references symbol '" + symbol
                            + "' but it's not defined in the key");
                }
                unused.remove(symbol);
                ingredients.set(col + width * row, ingredient);
            }
        }

        if (!unused.isEmpty()) {
            throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + unused);
        }
        return ingredients;
    }

    private static String[] shrink(String... pattern) {
        int minCol = Integer.MAX_VALUE;
        int maxCol = 0;
        int topTrim = 0;
        int bottomBlank = 0;

        for (int row = 0; row < pattern.length; row++) {
            String line = pattern[row];
            minCol = Math.min(minCol, firstNonSpace(line));
            int last = lastNonSpace(line);
            maxCol = Math.max(maxCol, last);
            if (last < 0) {
                if (topTrim == row) topTrim++;
                bottomBlank++;
            } else {
                bottomBlank = 0;
            }
        }

        if (pattern.length == bottomBlank) {
            throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");
        }

        String[] shrunk = new String[pattern.length - bottomBlank - topTrim];
        for (int i = 0; i < shrunk.length; i++) {
            shrunk[i] = pattern[i + topTrim].substring(minCol, maxCol + 1);
        }
        return shrunk;
    }

    private static int firstNonSpace(String line) {
        int i = 0;
        while (i < line.length() && line.charAt(i) == ' ') i++;
        return i;
    }

    private static int lastNonSpace(String line) {
        int i = line.length() - 1;
        while (i >= 0 && line.charAt(i) == ' ') i--;
        return i;
    }
}
