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
import net.p3pp3rf1y.sophisticatedcore.util.ColorHelper;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.common.data.SVOTags;
import com.CuteNekoDragon.Core.common.data.items.SVOItems;
import com.CuteNekoDragon.Core.common.data.svogt.SVOMachines;
import com.CuteNekoDragon.Core.common.item.SackItem;
import com.tterrag.registrate.util.entry.ItemEntry;

import java.util.Map;
import java.util.function.Consumer;

import static com.CuteNekoDragon.Core.common.data.items.SVOItems.DYED_SACKS;

public class ShapedRecipesProvider {

    public static void buildRecipes(Consumer<FinishedRecipe> consumer) {
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
