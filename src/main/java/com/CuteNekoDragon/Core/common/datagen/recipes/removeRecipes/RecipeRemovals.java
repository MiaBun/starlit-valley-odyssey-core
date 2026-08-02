package com.CuteNekoDragon.Core.common.datagen.recipes.removeRecipes;

import com.CuteNekoDragon.Core.utils.RecipeBlacklist;

public class RecipeRemovals {

    public static void register() {
        RecipeBlacklist.blacklistId("minecraft:golden_sword");

        RecipeBlacklist.blacklistId("gtceu:shaped/chainmail_helmet");
        RecipeBlacklist.blacklistId("gtceu:shaped/chainmail_chestplate");
        RecipeBlacklist.blacklistId("gtceu:shaped/chainmail_leggings");
        RecipeBlacklist.blacklistId("gtceu:shaped/chainmail_boots");
    }
}
