package com.CuteNekoDragon.Core.common.data.svogt;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;
import com.lowdragmc.lowdraglib.gui.texture.ProgressTexture;
import net.minecraft.core.registries.BuiltInRegistries;

@SuppressWarnings("deprecation")
public class SVOGTRecipeTypes {

    public static void init() {

    }

    public static final GTRecipeType CHARKOAL_KILN_RECIPES = register("charcoal_kiln")
            .setMaxIOSize(1, 1, 0, 0)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.FIRE);

    private static GTRecipeType register(String name) {
        var recipeType = new GTRecipeType(GTCEu.id(name), "log_carbonizer");
        GTRegistries.register(BuiltInRegistries.RECIPE_TYPE, recipeType.registryName, recipeType);
        GTRegistries.register(BuiltInRegistries.RECIPE_SERIALIZER, recipeType.registryName,
                new com.gregtechceu.gtceu.api.recipe.GTRecipeSerializer());
        GTRegistries.RECIPE_TYPES.register(recipeType.registryName, recipeType);
        return recipeType;
    }
}
