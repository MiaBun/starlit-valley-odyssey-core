package com.CuteNekoDragon.Core.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import com.CuteNekoDragon.Core.utils.skills.skill.SkillXPManager;

import java.util.function.Supplier;

public class RequestSyncSkillPacket {

    public RequestSyncSkillPacket() {}

    public static void encode(RequestSyncSkillPacket packet, FriendlyByteBuf buf) {}

    public static RequestSyncSkillPacket decode(FriendlyByteBuf buf) {
        return new RequestSyncSkillPacket();
    }

    public static void handle(RequestSyncSkillPacket packet, Supplier<NetworkEvent.Context> ctxS) {
        NetworkEvent.Context ctx = ctxS.get();
        ctx.enqueueWork(() -> {
            ServerPlayer sp = ctx.getSender();
            if (sp != null) SkillXPManager.sendTo(sp);
        });
        ctx.setPacketHandled(true);
    }
}
