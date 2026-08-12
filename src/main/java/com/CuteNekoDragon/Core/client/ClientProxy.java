package com.CuteNekoDragon.Core.client;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import com.CuteNekoDragon.Core.common.CommonProxy;

public class ClientProxy extends CommonProxy {

    @SuppressWarnings("removal")
    public ClientProxy() {
        super();

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    }
}
