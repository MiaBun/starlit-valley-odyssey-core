package com.CuteNekoDragon.Core.common.datagen.recipes.Smithing;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;

import com.CuteNekoDragon.Core.common.data.items.SVOItems;
import com.CuteNekoDragon.Core.common.item.SVOSmithingTemplate;
import com.CuteNekoDragon.Core.utils.recipes.SmithingRecipeHelper;
import com.tterrag.registrate.util.entry.ItemEntry;
import dev.ithundxr.createnumismatics.content.backend.Coin;
import dev.ithundxr.createnumismatics.registry.NumismaticsItems;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static com.CuteNekoDragon.Core.common.data.items.SVOItems.UPGRADE_TEMPLATES;

public class SmithingRecipesProvider {

    private static final Map<ItemLike, Item> CHAINMAIL_RECIPES = Map.of(
            Items.LEATHER_HELMET, Items.CHAINMAIL_HELMET,
            Items.LEATHER_CHESTPLATE, Items.CHAINMAIL_CHESTPLATE,
            Items.LEATHER_LEGGINGS, Items.CHAINMAIL_LEGGINGS,
            Items.LEATHER_BOOTS, Items.CHAINMAIL_BOOTS);

    private static final Map<ItemLike, Item> STONE_RECIPES = Map.of(
            Items.WOODEN_AXE, Items.STONE_AXE,
            Items.WOODEN_HOE, Items.STONE_HOE,
            Items.WOODEN_PICKAXE, Items.STONE_PICKAXE,
            Items.WOODEN_SHOVEL, Items.STONE_SHOVEL,
            Items.WOODEN_SWORD, Items.STONE_SWORD);

    private static final Map<ItemLike, Item> REMAINING_RECIPES = Map.ofEntries(
            Map.entry(Items.STONE_AXE, SVOItems.COPPER_AXE.asItem()),
            Map.entry(Items.STONE_PICKAXE, SVOItems.COPPER_PICKAXE.asItem()),
            Map.entry(Items.STONE_HOE, SVOItems.COPPER_HOE.asItem()),
            Map.entry(Items.STONE_SHOVEL, SVOItems.COPPER_SHOVEL.asItem()),
            Map.entry(Items.STONE_SWORD, SVOItems.COPPER_SWORD.asItem()),
            Map.entry(Items.CHAINMAIL_HELMET, SVOItems.COPPER_HELMET.asItem()),
            Map.entry(Items.CHAINMAIL_CHESTPLATE, SVOItems.COPPER_CHESTPLATE.asItem()),
            Map.entry(Items.CHAINMAIL_LEGGINGS, SVOItems.COPPER_LEGGINGS.asItem()),
            Map.entry(Items.CHAINMAIL_BOOTS, SVOItems.COPPER_BOOTS.asItem()),
            Map.entry(SVOItems.COPPER_AXE.asItem(), Items.IRON_AXE),
            Map.entry(SVOItems.COPPER_PICKAXE.asItem(), Items.IRON_PICKAXE),
            Map.entry(SVOItems.COPPER_HOE.asItem(), Items.IRON_HOE),
            Map.entry(SVOItems.COPPER_SHOVEL.asItem(), Items.IRON_SHOVEL),
            Map.entry(SVOItems.COPPER_SWORD.asItem(), Items.IRON_SWORD),
            Map.entry(SVOItems.COPPER_HELMET.asItem(), Items.IRON_HELMET),
            Map.entry(SVOItems.COPPER_CHESTPLATE.asItem(), Items.IRON_CHESTPLATE),
            Map.entry(SVOItems.COPPER_LEGGINGS.asItem(), Items.IRON_LEGGINGS),
            Map.entry(SVOItems.COPPER_BOOTS.asItem(), Items.IRON_BOOTS),
            Map.entry(Items.IRON_AXE, Items.GOLDEN_AXE),
            Map.entry(Items.IRON_PICKAXE, Items.GOLDEN_PICKAXE),
            Map.entry(Items.IRON_HOE, Items.GOLDEN_HOE),
            Map.entry(Items.IRON_SHOVEL, Items.GOLDEN_SHOVEL),
            Map.entry(Items.IRON_SWORD, Items.GOLDEN_SWORD),
            Map.entry(Items.IRON_HELMET, Items.GOLDEN_HELMET),
            Map.entry(Items.IRON_CHESTPLATE, Items.GOLDEN_CHESTPLATE),
            Map.entry(Items.IRON_LEGGINGS, Items.GOLDEN_LEGGINGS),
            Map.entry(Items.IRON_BOOTS, Items.GOLDEN_BOOTS),
            Map.entry(Items.GOLDEN_AXE, Items.DIAMOND_AXE),
            Map.entry(Items.GOLDEN_PICKAXE, Items.DIAMOND_PICKAXE),
            Map.entry(Items.GOLDEN_HOE, Items.DIAMOND_HOE),
            Map.entry(Items.GOLDEN_SHOVEL, Items.DIAMOND_SHOVEL),
            Map.entry(Items.GOLDEN_SWORD, Items.DIAMOND_SWORD),
            Map.entry(Items.GOLDEN_HELMET, Items.DIAMOND_HELMET),
            Map.entry(Items.GOLDEN_CHESTPLATE, Items.DIAMOND_CHESTPLATE),
            Map.entry(Items.GOLDEN_LEGGINGS, Items.DIAMOND_LEGGINGS),
            Map.entry(Items.GOLDEN_BOOTS, Items.DIAMOND_BOOTS),

            Map.entry(Items.DIAMOND_AXE, SVOItems.IRIDIUM_AXE.asItem()),
            Map.entry(Items.DIAMOND_HOE, SVOItems.IRIDIUM_HOE.asItem()),
            Map.entry(Items.DIAMOND_PICKAXE, SVOItems.IRIDIUM_PICKAXE.asItem()),
            Map.entry(Items.DIAMOND_SHOVEL, SVOItems.IRIDIUM_SHOVEL.asItem()),
            Map.entry(Items.DIAMOND_SWORD, SVOItems.IRIDIUM_SWORD.asItem()),

            Map.entry(Items.DIAMOND_HELMET, SVOItems.IRIDIUM_HELMET.asItem()),
            Map.entry(Items.DIAMOND_CHESTPLATE, SVOItems.IRIDIUM_CHESTPLATE.asItem()),
            Map.entry(Items.DIAMOND_LEGGINGS, SVOItems.IRIDIUM_LEGGINGS.asItem()),
            Map.entry(Items.DIAMOND_BOOTS, SVOItems.IRIDIUM_BOOTS.asItem())

    );

    public static void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ItemEntry<SVOSmithingTemplate> copperTemplate = UPGRADE_TEMPLATES.get("copper");
        ItemEntry<SVOSmithingTemplate> ironTemplate = UPGRADE_TEMPLATES.get("iron");
        ItemEntry<SVOSmithingTemplate> goldTemplate = UPGRADE_TEMPLATES.get("gold");
        ItemEntry<SVOSmithingTemplate> diamondTemplate = UPGRADE_TEMPLATES.get("diamond");
        ItemEntry<SVOSmithingTemplate> iridiumTemplate = UPGRADE_TEMPLATES.get("iridium");

        SmithingRecipeHelper.makeSmithingRecipe(consumer, copperTemplate.get(), ModItems.BACKPACK.get(),
                Items.COPPER_INGOT, ModItems.COPPER_BACKPACK.get(),
                ModItems.SMITHING_BACKPACK_UPGRADE_RECIPE_SERIALIZER.get());
        SmithingRecipeHelper.makeSmithingRecipe(consumer, ironTemplate.get(), ModItems.COPPER_BACKPACK.get(),
                Items.IRON_INGOT, ModItems.IRON_BACKPACK.get(),
                ModItems.SMITHING_BACKPACK_UPGRADE_RECIPE_SERIALIZER.get());
        SmithingRecipeHelper.makeSmithingRecipe(consumer, goldTemplate.get(), ModItems.IRON_BACKPACK.get(),
                Items.GOLD_INGOT, ModItems.GOLD_BACKPACK.get(),
                ModItems.SMITHING_BACKPACK_UPGRADE_RECIPE_SERIALIZER.get());
        SmithingRecipeHelper.makeSmithingRecipe(consumer, diamondTemplate.get(), ModItems.GOLD_BACKPACK.get(),
                Items.DIAMOND, ModItems.DIAMOND_BACKPACK.get(),
                ModItems.SMITHING_BACKPACK_UPGRADE_RECIPE_SERIALIZER.get());

        SmithingRecipeHelper.makeSmithingRecipe(consumer, copperTemplate.get(), SVOItems.LUNCHBOX, Items.COPPER_INGOT,
                SVOItems.DELUXE_LUNCHBOX.get());

        SmithingRecipeHelper.makeSmithingRecipe(consumer, ironTemplate.get(), SVOItems.DELUXE_LUNCHBOX,
                Items.IRON_INGOT,
                SVOItems.SUPER_DELUXE_LUNCHBOX.get());

        SmithingRecipeHelper.makeSmithingRecipe(consumer, goldTemplate.get(), SVOItems.SUPER_DELUXE_LUNCHBOX,
                Items.GOLD_INGOT,
                SVOItems.PREMIUM_LUNCHBOX.get());

        SmithingRecipeHelper.makeSmithingRecipe(consumer, diamondTemplate.get(), SVOItems.PREMIUM_LUNCHBOX,
                Items.DIAMOND,
                SVOItems.ULTIMATE_LUNCHBOX.get());

        SmithingRecipeHelper.makeSmithingRecipe(consumer, iridiumTemplate.get(), SVOItems.ULTIMATE_LUNCHBOX,
                SVOItems.IRIDIUM_INGOT,
                SVOItems.COLLECTORS_LUNCHBOX.get());

        SmithingRecipeHelper.makeSmithingRecipe(consumer, copperTemplate.get(), SVOItems.TOOLBELT, Items.COPPER_INGOT,
                SVOItems.DELUXE_TOOLBELT.get());

        SmithingRecipeHelper.makeSmithingRecipe(consumer, ironTemplate.get(), SVOItems.DELUXE_TOOLBELT,
                Items.IRON_INGOT,
                SVOItems.SUPER_DELUXE_TOOLBELT.get());

        SmithingRecipeHelper.makeSmithingRecipe(consumer, goldTemplate.get(), SVOItems.SUPER_DELUXE_TOOLBELT,
                Items.GOLD_INGOT,
                SVOItems.PREMIUM_TOOLBELT.get());

        SmithingRecipeHelper.makeSmithingRecipe(consumer, diamondTemplate.get(), SVOItems.PREMIUM_TOOLBELT,
                Items.DIAMOND,
                SVOItems.ULTIMATE_TOOLBELT.get());

        SmithingRecipeHelper.makeSmithingRecipe(consumer, iridiumTemplate.get(), SVOItems.ULTIMATE_TOOLBELT,
                SVOItems.IRIDIUM_INGOT,
                SVOItems.COLLECTORS_TOOLBELT.get());

        SmithingRecipeHelper.makeSmithingRecipe(consumer, iridiumTemplate.get(), ModItems.DIAMOND_BACKPACK.get(),
                SVOItems.IRIDIUM_INGOT, SVOItems.IRIDIUM_BACKPACK.get(),
                ModItems.SMITHING_BACKPACK_UPGRADE_RECIPE_SERIALIZER.get());

        SmithingRecipeHelper.makeSmithingRecipe(consumer, copperTemplate.get(), ModItems.UPGRADE_BASE.get(),
                Items.COPPER_INGOT,
                ModItems.STACK_UPGRADE_STARTER_TIER.get());

        SmithingRecipeHelper.makeSmithingRecipe(consumer, ironTemplate.get(), ModItems.STACK_UPGRADE_STARTER_TIER.get(),
                Items.IRON_INGOT,
                ModItems.STACK_UPGRADE_TIER_1.get());

        SmithingRecipeHelper.makeSmithingRecipe(consumer, goldTemplate.get(), ModItems.STACK_UPGRADE_TIER_1.get(),
                Items.GOLD_INGOT,
                ModItems.STACK_UPGRADE_TIER_2.get());

        SmithingRecipeHelper.makeSmithingRecipe(consumer, diamondTemplate.get(), ModItems.STACK_UPGRADE_TIER_2.get(),
                Items.DIAMOND,
                ModItems.STACK_UPGRADE_TIER_3.get());

        SmithingRecipeHelper.makeSmithingRecipe(consumer, iridiumTemplate.get(), ModItems.STACK_UPGRADE_TIER_3.get(),
                SVOItems.IRIDIUM_INGOT,
                ModItems.STACK_UPGRADE_TIER_4.get());

        SmithingRecipeHelper.makeSmithingRecipe(consumer, copperTemplate.get(),
                net.p3pp3rf1y.sophisticatedstorage.init.ModItems.UPGRADE_BASE.get(), Items.COPPER_INGOT,
                net.p3pp3rf1y.sophisticatedstorage.init.ModItems.STACK_UPGRADE_TIER_1.get(),
                "storage_stack_upgrade_tier_1");

        SmithingRecipeHelper.makeSmithingRecipe(consumer, ironTemplate.get(),
                net.p3pp3rf1y.sophisticatedstorage.init.ModItems.STACK_UPGRADE_TIER_1.get(), Items.IRON_INGOT,
                net.p3pp3rf1y.sophisticatedstorage.init.ModItems.STACK_UPGRADE_TIER_2.get(),
                "storage_stack_upgrade_tier_2");

        SmithingRecipeHelper.makeSmithingRecipe(consumer, goldTemplate.get(),
                net.p3pp3rf1y.sophisticatedstorage.init.ModItems.STACK_UPGRADE_TIER_2.get(), Items.GOLD_INGOT,
                net.p3pp3rf1y.sophisticatedstorage.init.ModItems.STACK_UPGRADE_TIER_3.get(),
                "storage_stack_upgrade_tier_3");

        SmithingRecipeHelper.makeSmithingRecipe(consumer, diamondTemplate.get(),
                net.p3pp3rf1y.sophisticatedstorage.init.ModItems.STACK_UPGRADE_TIER_3.get(), Items.DIAMOND,
                net.p3pp3rf1y.sophisticatedstorage.init.ModItems.STACK_UPGRADE_TIER_4.get(),
                "storage_stack_upgrade_tier_4");

        SmithingRecipeHelper.makeSmithingRecipe(consumer, iridiumTemplate.get(),
                net.p3pp3rf1y.sophisticatedstorage.init.ModItems.STACK_UPGRADE_TIER_4.get(), SVOItems.IRIDIUM_INGOT,
                net.p3pp3rf1y.sophisticatedstorage.init.ModItems.STACK_UPGRADE_TIER_5.get(),
                "storage_stack_upgrade_tier_5");

        for (Map.Entry<ItemLike, Item> entry : CHAINMAIL_RECIPES.entrySet()) {
            ItemLike baseItem = entry.getKey();
            Item resultItem = entry.getValue();

            SmithingRecipeHelper.makeSmithingRecipe(consumer, NumismaticsItems.getCoin(Coin.COG), baseItem, Items.CHAIN,
                    resultItem);
        }

        for (Map.Entry<ItemLike, Item> entry : STONE_RECIPES.entrySet()) {
            ItemLike baseItem = entry.getKey();
            Item resultItem = entry.getValue();

            SmithingRecipeHelper.makeSmithingRecipe(consumer, NumismaticsItems.getCoin(Coin.COG), baseItem,
                    Items.COBBLESTONE, resultItem);
        }

        for (Map.Entry<ItemLike, Item> entry : REMAINING_RECIPES.entrySet()) {
            ItemLike baseItem = entry.getKey();
            Item resultItem = entry.getValue();
            String itemTier = getMaterialPrefix(resultItem);

            if (itemTier.equals("copper")) {
                SmithingRecipeHelper.makeSmithingRecipe(consumer, copperTemplate.get(), baseItem, Items.COPPER_INGOT,
                        resultItem);
            }
            if (itemTier.equals("iron")) {
                SmithingRecipeHelper.makeSmithingRecipe(consumer, ironTemplate.get(), baseItem, Items.IRON_INGOT,
                        resultItem);
            }
            if (itemTier.equals("golden")) {
                SmithingRecipeHelper.makeSmithingRecipe(consumer, goldTemplate.get(), baseItem, Items.GOLD_INGOT,
                        resultItem);
            }
            if (itemTier.equals("diamond")) {
                SmithingRecipeHelper.makeSmithingRecipe(consumer, diamondTemplate.get(), baseItem, Items.DIAMOND,
                        resultItem);
            }
            if (itemTier.equals("iridium")) {
                SmithingRecipeHelper.makeSmithingRecipe(consumer, iridiumTemplate.get(), baseItem,
                        SVOItems.IRIDIUM_INGOT,
                        resultItem);
            }
        }
    }

    private static final List<String> GEAR_SUFFIXES = List.of(
            "sword", "pickaxe", "axe", "shovel", "hoe",
            "helmet", "chestplate", "leggings", "boots");

    public static String getMaterialPrefix(ItemLike item) {
        String path = ForgeRegistries.ITEMS.getKey(item.asItem()).getPath();
        return GEAR_SUFFIXES.stream()
                .filter(suffix -> path.endsWith("_" + suffix))
                .findFirst()
                .map(suffix -> path.substring(0, path.length() - suffix.length() - 1))
                .orElse(path); // no known suffix matched, return whole path as fallback
    }
}
