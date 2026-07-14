package org.CuteNekoDragon.svo_core.common;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.CuteNekoDragon.svo_core.common.data.SvoCreativeTab;
import org.CuteNekoDragon.svo_core.common.data.SvoItems;
import org.CuteNekoDragon.svo_core.config.SVOConfig;

public class CommonProxy {

    public CommonProxy() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.register(this);
        bus.addListener(SVOConfig::onLoad);
        SvoItems.register(bus);
        SvoCreativeTab.register(bus);


    }
}
