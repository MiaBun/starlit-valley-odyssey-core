package org.CuteNekoDragon.svo_core.client;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.CuteNekoDragon.svo_core.common.CommonProxy;

public class ClientProxy extends CommonProxy {

    public ClientProxy() {
        super();

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    }
}
