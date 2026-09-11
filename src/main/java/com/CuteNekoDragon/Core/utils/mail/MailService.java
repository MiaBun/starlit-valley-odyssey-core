package com.CuteNekoDragon.Core.utils.mail;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import java.util.HashMap;
import java.util.Map;

public final class MailService {

    public static boolean sendLetter(ServerPlayer player, ResourceLocation letterTemplateId, ResourceLocation npcId, Component npcDisplayname) {
        return sendLetter(player, letterTemplateId, npcId, npcDisplayname, Map.of());
    }

    public static boolean sendLetter(ServerPlayer player, ResourceLocation letterTemplateId, ResourceLocation npcId, Component npcDisplayname, Map<String, String> extraPlaceholders) {
        LetterTemplate template = LetterTemplateLoader.getTemplate(letterTemplateId);
        if (template == null) {
            return false;
        }

        Map<String, String> placeholders = new HashMap<>(extraPlaceholders);
        placeholders.putIfAbsent("player", player.getName().getString());

        Letter letter = Letter.create(template, npcId, npcDisplayname, placeholders);
        return true;
    }
}
