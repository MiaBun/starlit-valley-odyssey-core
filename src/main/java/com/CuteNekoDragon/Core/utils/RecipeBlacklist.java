package com.CuteNekoDragon.Core.utils;

import com.CuteNekoDragon.Core.common.datagen.recipes.removeRecipes.RecipeRemovals;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("removal")
public class RecipeBlacklist {

    public static final Set<ResourceLocation> BY_ID = new HashSet<>();
    public static final Set<String> BY_MOD = new HashSet<>();
    public static final Set<String> BY_OUTPUT = new HashSet<>();
    public static final Set<String> BY_INPUT = new HashSet<>();

    public static boolean loaded = false;

    public static void load() {
        if (loaded) return;
        loaded = true;

        BY_ID.clear();
        BY_MOD.clear();
        BY_OUTPUT.clear();
        BY_INPUT.clear();

        RecipeRemovals.register();
    }

    public static void blacklistId(String id) {
        BY_ID.add(new ResourceLocation(id));
    }

    public static void blacklistMod(String modId) {
        BY_MOD.add(modId);
    }

    public static void blacklistOutput(String itemId) {
        BY_OUTPUT.add(itemId);
    }

    public static void blacklistInput(String itemId) {
        BY_INPUT.add(itemId);
    }

    @SuppressWarnings("deprecation")
    public static boolean shouldRemoveParsed(ResourceLocation id, Recipe<?> recipe) {
        if(BY_ID.contains(id) || BY_MOD.contains(id.getNamespace())) return true;

        String outputID = BuiltInRegistries.ITEM.getKey(recipe.getResultItem(RegistryAccess.EMPTY).getItem()).toString();
        if (BY_OUTPUT.contains(outputID)) return true;

        for(Ingredient ingredient : recipe.getIngredients()) {
            for(ItemStack stack : ingredient.getItems()) {
                String itemId = BuiltInRegistries.ITEM.getKey(stack.getItem()).toString();
                if(BY_INPUT.contains(itemId)) return true;
            }
        }
        return false;
    }

    public static boolean shouldRemoveRaw(ResourceLocation id, JsonElement json) {
        if (BY_ID.contains(id) || BY_MOD.contains(id.getNamespace())) return true;

        if (!json.isJsonObject()) return false;
        JsonObject obj = json.getAsJsonObject();

        if (obj.has("result")) {
            JsonElement result = obj.get("result");
            String resStr = "";
            if (result.isJsonPrimitive()) resStr = result.getAsString();
            else if (result.isJsonObject() && result.getAsJsonObject().has("item"))
                resStr = result.getAsJsonObject().get("item").getAsString();

            if (BY_OUTPUT.contains(resStr)) return true;
        }

        String rawJson = obj.toString();
        for (String inputItem : BY_INPUT) {
            if (rawJson.contains("\"item\":\"" + inputItem + "\"") ||
                    rawJson.contains("\"item\": \"" + inputItem + "\""))
                return true;
        }
        return false;
    }
}
