package com.CuteNekoDragon.Core.common;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.common.data.*;
import com.CuteNekoDragon.Core.common.data.blocks.SVOBlocks;
import com.CuteNekoDragon.Core.common.data.items.SVOItems;
import com.CuteNekoDragon.Core.common.data.svogt.SVOGTRecipeTypes;
import com.CuteNekoDragon.Core.common.data.svogt.SVOMachines;
import com.CuteNekoDragon.Core.config.SVOConfig;
import com.CuteNekoDragon.Core.network.SVONetworkHandler;

public class CommonProxy {

    @SuppressWarnings("removal")
    public CommonProxy() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.register(this);
        bus.addListener(SVOConfig::onLoad);

        SVOCore.REGISTRATE.registerEventListeners(bus);

        SVONetworkHandler.init();
        SVOBlocks.init();
        SVOBlockEntities.init();
        SVOItems.init();
        SVOCreativeTab.init();
        SVOContainers.CONTAINERS.register(bus);
        SVORecipeSeralizers.RECIPE_SERIALIZERS.register(bus);

        bus.addGenericListener(MachineDefinition.class, this::registerMachines);
        bus.addGenericListener(GTRecipeType.class, this::registerRecipeTypes);
    }

    @SubscribeEvent
    public void onCommonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SVOItemPrices.ProvidePrices();
            SVOItemTooltips.ProvideTooltips();
        });
    }

    public void registerMachines(GTCEuAPI.RegisterEvent<ResourceLocation, MachineDefinition> event) {
        SVOMachines.init();
    }

    public void registerRecipeTypes(GTCEuAPI.RegisterEvent<ResourceLocation, GTRecipeType> event) {
        SVOGTRecipeTypes.init();
    }
}
