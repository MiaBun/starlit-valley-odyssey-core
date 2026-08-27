package com.CuteNekoDragon.Core.common.datagen.recipes.removeRecipes;

import com.CuteNekoDragon.Core.common.datagen.GlobalDatagenArrays;
import com.CuteNekoDragon.Core.utils.recipes.RecipeBlacklist;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

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

        RecipeBlacklist.blacklistId("minecraft:golden_sword");
        RecipeBlacklist.blacklistId("minecraft:golden_axe");
        RecipeBlacklist.blacklistId("minecraft:golden_pickaxe");
        RecipeBlacklist.blacklistId("minecraft:golden_shovel");
        RecipeBlacklist.blacklistId("minecraft:golden_hoe");
        RecipeBlacklist.blacklistId("minecraft:golden_helmet");
        RecipeBlacklist.blacklistId("minecraft:golden_chestplate");
        RecipeBlacklist.blacklistId("minecraft:golden_leggings");
        RecipeBlacklist.blacklistId("minecraft:golden_boots");

        RecipeBlacklist.blacklistId("minecraft:diamond_sword");
        RecipeBlacklist.blacklistId("minecraft:diamond_axe");
        RecipeBlacklist.blacklistId("minecraft:diamond_pickaxe");
        RecipeBlacklist.blacklistId("minecraft:diamond_shovel");
        RecipeBlacklist.blacklistId("minecraft:diamond_hoe");
        RecipeBlacklist.blacklistId("minecraft:diamond_helmet");
        RecipeBlacklist.blacklistId("minecraft:diamond_chestplate");
        RecipeBlacklist.blacklistId("minecraft:diamond_leggings");
        RecipeBlacklist.blacklistId("minecraft:diamond_boots");

        RecipeBlacklist.blacklistId("minecraft:netherite_sword_smithing");
        RecipeBlacklist.blacklistId("minecraft:netherite_axe_smithing");
        RecipeBlacklist.blacklistId("minecraft:netherite_pickaxe_smithing");
        RecipeBlacklist.blacklistId("minecraft:netherite_shovel_smithing");
        RecipeBlacklist.blacklistId("minecraft:netherite_hoe_smithing");
        RecipeBlacklist.blacklistId("minecraft:netherite_helmet_smithing");
        RecipeBlacklist.blacklistId("minecraft:netherite_chestplate_smithing");
        RecipeBlacklist.blacklistId("minecraft:netherite_leggings_smithing");
        RecipeBlacklist.blacklistId("minecraft:netherite_boots_smithing");

        RecipeBlacklist.blacklistId("sophisticatedbackpacks:copper_backpack");
        RecipeBlacklist.blacklistId("sophisticatedbackpacks:iron_backpack");
        RecipeBlacklist.blacklistId("sophisticatedbackpacks:iron_backpack_from_copper");
        RecipeBlacklist.blacklistId("sophisticatedbackpacks:gold_backpack");
        RecipeBlacklist.blacklistId("sophisticatedbackpacks:diamond_backpack");
        RecipeBlacklist.blacklistId("sophisticatedbackpacks:backpack");
        RecipeBlacklist.blacklistId("sophisticatedbackpacks:stack_upgrade_starter_tier");
        RecipeBlacklist.blacklistId("sophisticatedbackpacks:stack_upgrade_tier_1");
        RecipeBlacklist.blacklistId("sophisticatedbackpacks:stack_upgrade_tier_1_from_starter");

        RecipeBlacklist.blacklistId("sophisticatedstorage:backpack_stack_upgrade_starter_tier_from_storage_stack_upgrade_tier_1_plus");
        RecipeBlacklist.blacklistId("sophisticatedstorage:backpack_stack_upgrade_tier_1_from_storage_stack_upgrade_tier_2");

        RecipeBlacklist.blacklistId("sophisticatedbackpacks:stack_upgrade_tier_2");
        RecipeBlacklist.blacklistId("sophisticatedstorage:backpack_stack_upgrade_tier_2_from_storage_stack_upgrade_tier_3");

        RecipeBlacklist.blacklistId("sophisticatedbackpacks:stack_upgrade_tier_2");
        RecipeBlacklist.blacklistId("sophisticatedstorage:backpack_stack_upgrade_tier_2_from_storage_stack_upgrade_tier_3");

        RecipeBlacklist.blacklistId("sophisticatedbackpacks:stack_upgrade_tier_3");
        RecipeBlacklist.blacklistId("sophisticatedstorage:backpack_stack_upgrade_tier_3_from_storage_stack_upgrade_tier_4");

        RecipeBlacklist.blacklistId("sophisticatedbackpacks:stack_upgrade_tier_4");
        RecipeBlacklist.blacklistId("sophisticatedstorage:backpack_stack_upgrade_tier_4_from_storage_stack_upgrade_tier_5");

        RecipeBlacklist.blacklistId("sophisticatedstorage:stack_upgrade_tier_1");

        RecipeBlacklist.blacklistId("sophisticatedstorage:storage_stack_upgrade_tier_2_from_backpack_stack_upgrade_tier_1");
        RecipeBlacklist.blacklistId("sophisticatedstorage:stack_upgrade_tier_2");
        RecipeBlacklist.blacklistId("sophisticatedstorage:stack_upgrade_tier_2_from_tier_1_plus");

        RecipeBlacklist.blacklistId("sophisticatedstorage:storage_stack_upgrade_tier_3_from_backpack_stack_upgrade_tier_2");
        RecipeBlacklist.blacklistId("sophisticatedstorage:stack_upgrade_tier_3");

        RecipeBlacklist.blacklistId("sophisticatedstorage:storage_stack_upgrade_tier_4_from_backpack_stack_upgrade_tier_3");
        RecipeBlacklist.blacklistId("sophisticatedstorage:stack_upgrade_tier_4");

        RecipeBlacklist.blacklistId("sophisticatedstorage:storage_stack_upgrade_tier_5_from_backpack_stack_upgrade_tier_4");
        RecipeBlacklist.blacklistId("sophisticatedstorage:stack_upgrade_tier_5");

        RecipeBlacklist.blacklistId("sophisticatedstorage:copper_barrel");
        RecipeBlacklist.blacklistId("sophisticatedstorage:iron_barrel");
        RecipeBlacklist.blacklistId("sophisticatedstorage:iron_barrel_from_copper_barrel");
        RecipeBlacklist.blacklistId("sophisticatedstorage:gold_barrel");
        RecipeBlacklist.blacklistId("sophisticatedstorage:diamond_barrel");

        RecipeBlacklist.blacklistId("sophisticatedstorage:copper_chest");
        RecipeBlacklist.blacklistId("sophisticatedstorage:iron_chest");
        RecipeBlacklist.blacklistId("sophisticatedstorage:iron_chest_from_copper_chest");
        RecipeBlacklist.blacklistId("sophisticatedstorage:gold_chest");
        RecipeBlacklist.blacklistId("sophisticatedstorage:diamond_chest");

        for(Item item : GlobalDatagenArrays.RemovedItems) {
            RecipeBlacklist.blacklistOutput(ForgeRegistries.ITEMS.getKey(item.asItem()).toString());
        }
    }
}
