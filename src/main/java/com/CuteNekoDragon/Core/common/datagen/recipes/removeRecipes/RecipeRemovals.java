package com.CuteNekoDragon.Core.common.datagen.recipes.removeRecipes;

import com.CuteNekoDragon.Core.utils.recipes.RecipeBlacklist;

public class RecipeRemovals {

    public static void register() {

        RecipeBlacklist.blacklistId("gtceu:shaped/chainmail_helmet");
        RecipeBlacklist.blacklistId("gtceu:shaped/chainmail_chestplate");
        RecipeBlacklist.blacklistId("gtceu:shaped/chainmail_leggings");
        RecipeBlacklist.blacklistId("gtceu:shaped/chainmail_boots");

        RecipeBlacklist.blacklistId("minecraft:stone_sword");
        RecipeBlacklist.blacklistId("minecraft:stone_axe");
        RecipeBlacklist.blacklistId("minecraft:stone_pickaxe");
        RecipeBlacklist.blacklistId("minecraft:stone_shovel");
        RecipeBlacklist.blacklistId("minecraft:stone_hoe");

        RecipeBlacklist.blacklistId("minecraft:iron_sword");
        RecipeBlacklist.blacklistId("minecraft:iron_axe");
        RecipeBlacklist.blacklistId("minecraft:iron_pickaxe");
        RecipeBlacklist.blacklistId("minecraft:iron_shovel");
        RecipeBlacklist.blacklistId("minecraft:iron_hoe");
        RecipeBlacklist.blacklistId("minecraft:iron_helmet");
        RecipeBlacklist.blacklistId("minecraft:iron_chestplate");
        RecipeBlacklist.blacklistId("minecraft:iron_leggings");
        RecipeBlacklist.blacklistId("minecraft:iron_boots");
    }
}
