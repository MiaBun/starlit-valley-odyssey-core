package com.CuteNekoDragon.Core.client;

import com.CuteNekoDragon.Core.client.components.ClientSackTooltip;
import com.CuteNekoDragon.Core.common.component.SackTooltip;
import net.minecraftforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import com.CuteNekoDragon.Core.common.CommonProxy;

public class ClientProxy extends CommonProxy {

    @SuppressWarnings("removal")
    public ClientProxy() {
        super();

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    }

}
