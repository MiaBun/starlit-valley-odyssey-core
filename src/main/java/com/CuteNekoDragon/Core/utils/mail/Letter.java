package com.CuteNekoDragon.Core.utils.mail;

import net.minecraft.resources.ResourceLocation;

import net.minecraft.network.chat.Component;

import java.util.UUID;
import java.util.List;

public class Letter {

    private final UUID letterId;
    private final ResourceLocation templateId;
    private final ResourceLocation npcId;
    private final Component npcName;
    private final Component title;
    private final List<Component> body;
    private final long receivedTime;
    private boolean read;

    public Letter(UUID letterId, ResourceLocation templateId, ResourceLocation npcId, Component npcName, Component title, List<Component> body, long receivedTime) {
        this.letterId = letterId;
        this.templateId = templateId;
        this.npcId = npcId;
        this.npcName = npcName;
        this.title = title;
        this.body = body;
        this.receivedTime = receivedTime;
    }
}
