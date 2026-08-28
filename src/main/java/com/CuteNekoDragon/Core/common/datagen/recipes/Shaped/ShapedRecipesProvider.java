package com.CuteNekoDragon.Core.common.datagen.recipes.Shaped;

import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;
import net.p3pp3rf1y.sophisticatedcore.crafting.ShapeBasedRecipeBuilder;
import net.p3pp3rf1y.sophisticatedcore.util.ColorHelper;
import net.p3pp3rf1y.sophisticatedstorage.init.ModBlocks;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.common.data.SVOTags;
import com.CuteNekoDragon.Core.common.data.items.SVOItems;
import com.CuteNekoDragon.Core.common.data.svogt.SVOMachines;
import com.CuteNekoDragon.Core.common.item.SVOSmithingTemplate;
import com.CuteNekoDragon.Core.common.item.SackItem;
import com.tterrag.registrate.util.entry.ItemEntry;

import java.util.Map;
import java.util.function.Consumer;

import static com.CuteNekoDragon.Core.common.data.items.SVOItems.DYED_SACKS;
import static com.CuteNekoDragon.Core.common.data.items.SVOItems.UPGRADE_TEMPLATES;

public class ShapedRecipesProvider {

    public static void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ItemEntry<SVOSmithingTemplate> copperTemplate = UPGRADE_TEMPLATES.get("copper");
        ItemEntry<SVOSmithingTemplate> ironTemplate = UPGRADE_TEMPLATES.get("iron");
        ItemEntry<SVOSmithingTemplate> goldTemplate = UPGRADE_TEMPLATES.get("gold");
        ItemEntry<SVOSmithingTemplate> diamondTemplate = UPGRADE_TEMPLATES.get("diamond");
        ItemEntry<SVOSmithingTemplate> iridiumTemplate = UPGRADE_TEMPLATES.get("iridium");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SVOMachines.CHARKOAL_KILN.getItem())
                .pattern("ABA")
                .pattern("A A")
                .pattern("ABA")
                .define('A', ItemTags.LOGS)
                .define('B', Items.COPPER_INGOT)
                .unlockedBy("has_copper_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .save(consumer, SVOCore.id("shaped/charcoal_kiln"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SVOItems.LUNCHBOX)
                .pattern(" C ")
                .pattern("DAE")
                .pattern(" B ")
                .define('A', SVOTags.Items.SACK)
                .define('B', Items.APPLE)
                .define('C', Items.CARROT)
                .define('D', Items.WHEAT)
                .define('E', Items.POTATO)
                .unlockedBy("has_apple", InventoryChangeTrigger.TriggerInstance.hasItems(Items.APPLE))
                .save(consumer, SVOCore.id("shaped/sack_to_lunchbox"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BACKPACK.get()) // TODO: proper recipe that includes
                                                                                 // leather and cloth
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.STRING)
                .define('B', SVOItems.SACK)
                .unlockedBy("has_sack", InventoryChangeTrigger.TriggerInstance.hasItems(SVOItems.SACK))
                .save(consumer, SVOCore.id("shaped/sack_to_backpack"));

        ShapeBasedRecipeBuilder
                .shaped(ModBlocks.COPPER_BARREL_ITEM.get(), ModBlocks.STORAGE_TIER_UPGRADE_RECIPE_SERIALIZER.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', copperTemplate.get())
                .define('B', ModBlocks.BARREL_ITEM.get())
                .unlockedBy("has_barrel", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.BARREL_ITEM.get()))
                .save(consumer, SVOCore.id("shaped/copper_barrel"));

        ShapeBasedRecipeBuilder
                .shaped(ModBlocks.IRON_BARREL_ITEM.get(), ModBlocks.STORAGE_TIER_UPGRADE_RECIPE_SERIALIZER.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ironTemplate.get())
                .define('B', ModBlocks.COPPER_BARREL_ITEM.get())
                .unlockedBy("has_barrel",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_BARREL_ITEM.get()))
                .save(consumer, SVOCore.id("shaped/iron_barrel"));

        ShapeBasedRecipeBuilder
                .shaped(ModBlocks.GOLD_BARREL_ITEM.get(), ModBlocks.STORAGE_TIER_UPGRADE_RECIPE_SERIALIZER.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', goldTemplate.get())
                .define('B', ModBlocks.IRON_BARREL_ITEM.get())
                .unlockedBy("has_barrel",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.IRON_BARREL_ITEM.get()))
                .save(consumer, SVOCore.id("shaped/gold_barrel"));

        ShapeBasedRecipeBuilder
                .shaped(ModBlocks.DIAMOND_BARREL_ITEM.get(), ModBlocks.STORAGE_TIER_UPGRADE_RECIPE_SERIALIZER.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', diamondTemplate.get())
                .define('B', ModBlocks.GOLD_BARREL_ITEM.get())
                .unlockedBy("has_barrel",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.GOLD_BARREL_ITEM.get()))
                .save(consumer, SVOCore.id("shaped/diamond_barrel"));

        ShapeBasedRecipeBuilder
                .shaped(ModBlocks.COPPER_CHEST_ITEM.get(), ModBlocks.STORAGE_TIER_UPGRADE_RECIPE_SERIALIZER.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', copperTemplate.get())
                .define('B', ModBlocks.CHEST_ITEM.get())
                .unlockedBy("has_chest", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.CHEST_ITEM.get()))
                .save(consumer, SVOCore.id("shaped/copper_chest"));

        ShapeBasedRecipeBuilder
                .shaped(ModBlocks.IRON_CHEST_ITEM.get(), ModBlocks.STORAGE_TIER_UPGRADE_RECIPE_SERIALIZER.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ironTemplate.get())
                .define('B', ModBlocks.COPPER_CHEST_ITEM.get())
                .unlockedBy("has_chest",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_CHEST_ITEM.get()))
                .save(consumer, SVOCore.id("shaped/iron_chest"));

        ShapeBasedRecipeBuilder
                .shaped(ModBlocks.GOLD_CHEST_ITEM.get(), ModBlocks.STORAGE_TIER_UPGRADE_RECIPE_SERIALIZER.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', goldTemplate.get())
                .define('B', ModBlocks.IRON_CHEST_ITEM.get())
                .unlockedBy("has_chest",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.IRON_CHEST_ITEM.get()))
                .save(consumer, SVOCore.id("shaped/gold_chest"));

        ShapeBasedRecipeBuilder
                .shaped(ModBlocks.DIAMOND_CHEST_ITEM.get(), ModBlocks.STORAGE_TIER_UPGRADE_RECIPE_SERIALIZER.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', diamondTemplate.get())
                .define('B', ModBlocks.GOLD_CHEST_ITEM.get())
                .unlockedBy("has_chest",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.GOLD_CHEST_ITEM.get()))
                .save(consumer, SVOCore.id("shaped/diamond_chest"));

        ShapeBasedRecipeBuilder
                .shaped(ModBlocks.COPPER_SHULKER_BOX_ITEM.get(), ModBlocks.STORAGE_TIER_UPGRADE_RECIPE_SERIALIZER.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', copperTemplate.get())
                .define('B', ModBlocks.SHULKER_BOX_ITEM.get())
                .unlockedBy("has_shulker",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.SHULKER_BOX_ITEM.get()))
                .save(consumer, SVOCore.id("shaped/copper_shulker"));

        ShapeBasedRecipeBuilder
                .shaped(ModBlocks.IRON_SHULKER_BOX_ITEM.get(), ModBlocks.STORAGE_TIER_UPGRADE_RECIPE_SERIALIZER.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ironTemplate.get())
                .define('B', ModBlocks.COPPER_SHULKER_BOX_ITEM.get())
                .unlockedBy("has_shulker",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.COPPER_SHULKER_BOX_ITEM.get()))
                .save(consumer, SVOCore.id("shaped/iron_shulker"));

        ShapeBasedRecipeBuilder
                .shaped(ModBlocks.GOLD_SHULKER_BOX_ITEM.get(), ModBlocks.STORAGE_TIER_UPGRADE_RECIPE_SERIALIZER.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', goldTemplate.get())
                .define('B', ModBlocks.IRON_SHULKER_BOX_ITEM.get())
                .unlockedBy("has_shulker",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.IRON_SHULKER_BOX_ITEM.get()))
                .save(consumer, SVOCore.id("shaped/gold_shulker"));

        ShapeBasedRecipeBuilder
                .shaped(ModBlocks.DIAMOND_SHULKER_BOX_ITEM.get(),
                        ModBlocks.STORAGE_TIER_UPGRADE_RECIPE_SERIALIZER.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', diamondTemplate.get())
                .define('B', ModBlocks.GOLD_SHULKER_BOX_ITEM.get())
                .unlockedBy("has_shulker",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.GOLD_SHULKER_BOX_ITEM.get()))
                .save(consumer, SVOCore.id("shaped/diamond_shulker"));

        for (Map.Entry<DyeColor, ItemEntry<SackItem>> entry : DYED_SACKS.entrySet()) {
            DyeColor color = entry.getKey();
            ItemEntry<SackItem> sackItem = entry.getValue();
            ItemStack output = new ItemStack(ModItems.BACKPACK.get(), 1);
            float[] dyeRgb = color.getTextureDiffuseColors();
            int clothColor = ColorHelper.getColor(dyeRgb);
            output.getOrCreateTag().putInt("clothColor", clothColor);
            VanillaRecipeHelper.addShapedRecipe(consumer, SVOCore.id("sack_to_backpack_" + color.getName()), output,
                    "AAA",
                    "ABA",
                    "AAA",
                    'A', Items.STRING,
                    'B', sackItem);
        }
    }
}
