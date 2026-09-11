package com.CuteNekoDragon.Core.common;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.common.capability.MailCapability;
import com.CuteNekoDragon.Core.common.capability.PlayerMailData;
import com.CuteNekoDragon.Core.utils.mail.LetterTemplateLoader;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SVOCore.MOD_ID)
public class ForgeCommonEventListener {

    @SuppressWarnings("removal")
    private static final ResourceLocation CAP_ID = new ResourceLocation(SVOCore.MOD_ID, "mail_data");

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent e) {
        e.register(PlayerMailData.class);
    }

    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            event.addCapability(CAP_ID, new MailCapability.Provider());
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        PlayerMailData oldData = MailCapability.getOrDefault(event.getOriginal());
        PlayerMailData newData = MailCapability.getOrDefault(event.getEntity());
        newData.copyFrom(oldData);
    }

    // TODO: player login event

    @SubscribeEvent
    public static void registerReloadListeners(AddReloadListenerEvent event) {
        event.addListener(new LetterTemplateLoader());
    }
}
