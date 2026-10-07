package com.CuteNekoDragon.Core.utils.tooltips;

import com.CuteNekoDragon.Core.SVOCore;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SVOCore.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class TooltipClient {

    private TooltipClient() {}

    @SubscribeEvent
    public static void onRegisterRelaodListeners(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(new TooltipResourceLoader());
    }

}
