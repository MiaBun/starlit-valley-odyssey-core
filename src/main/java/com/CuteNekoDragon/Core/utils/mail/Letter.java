package com.CuteNekoDragon.Core.utils.mail;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import java.util.List;

public class Letter {

    @Getter
    private final UUID letterId;
    @Getter
    private final ResourceLocation templateId;
    @Getter
    private final ResourceLocation npcId;
    @Getter
    private final Component npcName;
    @Getter
    private final Component title;
    @Getter
    private final List<Component> body;
    @Getter
    private final long receivedTime;
    @Getter
    @Setter
    private boolean read;

    public Letter(UUID letterId, ResourceLocation templateId, ResourceLocation npcId, Component npcName, Component title, List<Component> body, long receivedTime, boolean read) {
        this.letterId = letterId;
        this.templateId = templateId;
        this.npcId = npcId;
        this.npcName = npcName;
        this.title = title;
        this.body = body;
        this.receivedTime = receivedTime;
        this.read = read;
    }

    public static Letter create(LetterTemplate template, ResourceLocation npcId, Component npcName, Map<String, String> placeholders) {
        String rawTitle = applyPlaceholders(template.getTitle(), placeholders);
        List<Component> body = new ArrayList<>();
        for (String line : template.getBodyLines()) {
            body.add(MailMarkdown.parseLine(applyPlaceholders(line, placeholders)));
        }
        return new Letter(UUID.randomUUID(), template.getId(), npcId, npcName, Component.literal(rawTitle), body, System.currentTimeMillis(), false);
    }

    private static String applyPlaceholders(String raw, Map<String, String> placeholders) {
        String result = raw;
        for (var entry: placeholders.entrySet()) {
            result = result.replace("{" + entry.getKey() + "}", entry.getValue());
        }
        return result;
    }

    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putUUID("LetterId", letterId);
        tag.putString("TemplateId", templateId.toString());
        tag.putString("NpcId", npcId.toString());
        tag.putString("NpcName", Component.Serializer.toJson(npcName));
        tag.putString("Title", Component.Serializer.toJson(title));
        tag.putLong("ReceivedTime", receivedTime);
        tag.putBoolean("Read", read);

        var bodyList = new net.minecraft.nbt.ListTag();
        for (Component line : body) {
            bodyList.add(net.minecraft.nbt.StringTag.valueOf(Component.Serializer.toJson(line)));
        }
        tag.put("Body", bodyList);
        return tag;
    }

    @SuppressWarnings("removal")
    public static Letter deserializeNBT(CompoundTag tag) {
        UUID letterId = tag.getUUID("LetterId");
        ResourceLocation templateId = new ResourceLocation(tag.getString("TemplateId"));
        ResourceLocation npcId = new ResourceLocation(tag.getString("NpcId"));
        Component npcName = Component.Serializer.fromJson(tag.getString("NpcName"));
        Component title = Component.Serializer.fromJson(tag.getString("Title"));
        long receivedTime = tag.getLong("ReceivedTime");
        boolean read = tag.getBoolean("Read");

        List<Component> body = new ArrayList<>();
        var bodyList = tag.getList("Body", net.minecraft.nbt.Tag.TAG_STRING);
        for (int i = 0; i < bodyList.size(); i++) {
            body.add(Component.Serializer.fromJson(bodyList.getString(i)));
        }

        return new Letter(letterId, templateId, npcId, npcName == null ? Component.empty() : npcName,
                title == null ? Component.empty() : title, body, receivedTime, read);
    }

    public void writeToBuf(FriendlyByteBuf buf) {
        buf.writeUUID(letterId);
        buf.writeResourceLocation(templateId);
        buf.writeResourceLocation(npcId);
        buf.writeComponent(npcName);
        buf.writeComponent(title);
        buf.writeVarInt(body.size());
        for (Component line : body) {
            buf.writeComponent(line);
        }
        buf.writeLong(receivedTime);
        buf.writeBoolean(read);
    }

    public static Letter readFromBuf(FriendlyByteBuf buf) {
        UUID letterId = buf.readUUID();
        ResourceLocation templateId = buf.readResourceLocation();
        ResourceLocation npcId = buf.readResourceLocation();
        Component npcName = buf.readComponent();
        Component title = buf.readComponent();
        int lineCount = buf.readVarInt();
        List<Component> body = new ArrayList<>(lineCount);
        for (int i = 0; i < lineCount; i++) {
            body.add(buf.readComponent());
        }
        long receivedTime = buf.readLong();
        boolean read = buf.readBoolean();
        return new Letter(letterId, templateId, npcId, npcName, title, body, receivedTime, read);
    }
}
