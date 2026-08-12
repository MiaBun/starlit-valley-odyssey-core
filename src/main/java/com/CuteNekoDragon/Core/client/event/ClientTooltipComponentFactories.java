package com.CuteNekoDragon.Core.client.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.client.components.ClientSackTooltip;
import com.CuteNekoDragon.Core.common.component.SackTooltip;

@Mod.EventBusSubscriber(modid = SVOCore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientTooltipComponentFactories {

    @SubscribeEvent
    public static void registerTooltips(RegisterClientTooltipComponentFactoriesEvent e) {
        e.register(SackTooltip.class, ClientSackTooltip::new);
    }
}
