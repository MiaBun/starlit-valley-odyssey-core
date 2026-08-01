package com.CuteNekoDragon.Core.common;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.common.data.SVOCreativeTab;
import com.CuteNekoDragon.Core.common.data.SVOItemPrices;
import com.CuteNekoDragon.Core.common.data.SVOItemTooltips;
import com.CuteNekoDragon.Core.common.data.items.SVOItems;
import com.CuteNekoDragon.Core.config.SVOConfig;

public class CommonProxy {

    @SuppressWarnings("removal")
    public CommonProxy() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.register(this);
        bus.addListener(SVOConfig::onLoad);

        SVOCore.REGISTRATE.registerEventListeners(bus);

        SVOItems.init();
        SVOCreativeTab.init();
    }

    @SubscribeEvent
    public void onCommonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SVOItemPrices.ProvidePrices();
            SVOItemTooltips.ProvideTooltips();
        });
    }
}
