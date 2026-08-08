package com.CuteNekoDragon.Core.common.svogt;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;

import net.minecraft.data.recipes.FinishedRecipe;

import com.CuteNekoDragon.Core.SVOCore;

import java.util.function.Consumer;

@GTAddon
public class SVOGTAddon implements IGTAddon {

    @Override
    public GTRegistrate getRegistrate() {
        return SVOCore.REGISTRATE;
    }

    @Override
    public void initializeAddon() {}

    @Override
    public String addonModId() {
        return SVOCore.MOD_ID;
    }

    @Override
    public void registerCovers() {}

    @Override
    public void addRecipes(Consumer<FinishedRecipe> provider) {
        SVOGTRecipes.init(provider);
    }
}
