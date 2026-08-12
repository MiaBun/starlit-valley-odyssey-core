package com.CuteNekoDragon.Core.common.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.utils.DurabilityUtil;

@Mod.EventBusSubscriber(modid = SVOCore.MOD_ID)
public class PlayerDeathHandler {

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent e) {
        if (e.getEntity() instanceof ServerPlayer serverPlayer) {
            var inventory = serverPlayer.getInventory();
            DurabilityUtil.damageItems(DurabilityUtil.getHotbarItems(inventory), serverPlayer);
            DurabilityUtil.damageItems(DurabilityUtil.getArmorItems(inventory), serverPlayer);
        }
    }
}
