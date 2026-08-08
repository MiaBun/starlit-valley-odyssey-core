package com.CuteNekoDragon.Core.common.svogt;

import com.CuteNekoDragon.Core.SVOCore;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import com.CuteNekoDragon.Core.common.data.svogt.SVOGTRecipeTypes;

import java.util.function.Consumer;

public class SVOGTRecipes {

    public static void init(Consumer<FinishedRecipe> provider) {
        SVOGTRecipeTypes.CHARKOAL_KILN_RECIPES.recipeBuilder(SVOCore.id("log_to_coal"))
                .inputItems(ItemTags.LOGS)
                .outputItems(Items.COAL)
                .duration(400)
                .save(provider);
    }
}
