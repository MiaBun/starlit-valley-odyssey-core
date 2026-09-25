package com.CuteNekoDragon.Core.client.event;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.client.SVOKeybinds;
import com.CuteNekoDragon.Core.client.screen.SkillTreeScreen;
import com.CuteNekoDragon.Core.network.SVONetworkHandler;
import com.CuteNekoDragon.Core.network.packet.RequestSyncSkillPacket;

@Mod.EventBusSubscriber(modid = SVOCore.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientSkillsEvents {

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.screen != null) return;

        while (SVOKeybinds.OPEN_SKILLS.consumeClick()) {
            SVONetworkHandler.INSTANCE.sendToServer(new RequestSyncSkillPacket());
            mc.setScreen(new SkillTreeScreen());
        }
    }
}
