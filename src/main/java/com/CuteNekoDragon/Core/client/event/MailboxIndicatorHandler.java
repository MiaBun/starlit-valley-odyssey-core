package com.CuteNekoDragon.Core.client.event;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.client.util.ClientMailCache;
import com.CuteNekoDragon.Core.client.util.ClientMailboxTracker;
import com.CuteNekoDragon.Core.common.data.SVOParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SVOCore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class MailboxIndicatorHandler {
    private static int tickCounter = 0;

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null) return;
        if (++tickCounter % 10 != 0) return;

        if (!ClientMailCache.isIndicatorEnabled()) return;
        boolean hasUnread = ClientMailCache.getLetters().stream().anyMatch(l -> !l.isRead());
        if (!hasUnread) return;

        for (BlockPos pos : ClientMailboxTracker.getPositions()) {
            mc.level.addParticle(SVOParticles.RED_EXCLAMATION.get(),
                    pos.getX() + 0.5, pos.getY() + 2.2, pos.getZ() + 0.5,
                    0, 0, 0);
        }
    }
}