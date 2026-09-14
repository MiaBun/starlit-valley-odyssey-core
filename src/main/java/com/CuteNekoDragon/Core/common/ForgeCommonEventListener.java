package com.CuteNekoDragon.Core.common;

import com.CuteNekoDragon.Core.common.capability.SkillCapability;
import com.CuteNekoDragon.Core.utils.skills.skill.ISkillData;
import com.CuteNekoDragon.Core.utils.skills.skill.PlayerSkillData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.common.capability.MailCapability;
import com.CuteNekoDragon.Core.common.capability.PlayerMailData;
import com.CuteNekoDragon.Core.common.data.SVOCommands;
import com.CuteNekoDragon.Core.network.SVONetworkHandler;
import com.CuteNekoDragon.Core.network.packet.SyncMailDataPacket;
import com.CuteNekoDragon.Core.utils.mail.LetterTemplateLoader;

@Mod.EventBusSubscriber(modid = SVOCore.MOD_ID)
public class ForgeCommonEventListener {

    @SuppressWarnings("removal")
    private static final ResourceLocation CAP_ID = new ResourceLocation(SVOCore.MOD_ID, "mail_data");

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        SVOCommands.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent e) {
        e.register(PlayerMailData.class);
        e.register(ISkillData.class);
    }

    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(CAP_ID, new MailCapability.Provider());
            event.addCapability(CAP_ID, new SkillCapability.Provider());
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {

        PlayerSkillData oldSkillData = SkillCapability.getOrDefault(event.getOriginal());
        PlayerSkillData newSkillData = SkillCapability.getOrDefault(event.getEntity());
        newSkillData.copyFrom(oldSkillData);

        PlayerMailData oldData = MailCapability.getOrDefault(event.getOriginal());
        PlayerMailData newData = MailCapability.getOrDefault(event.getEntity());
        newData.copyFrom(oldData);
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            PlayerMailData data = MailCapability.getOrDefault(serverPlayer);
            SVONetworkHandler.sendLetterToPlayer(serverPlayer,
                    new SyncMailDataPacket(data.getLetters(), data.isToastEnabled()));
        }
    }

    @SubscribeEvent
    public static void registerReloadListeners(AddReloadListenerEvent event) {
        event.addListener(new LetterTemplateLoader());
    }
}
