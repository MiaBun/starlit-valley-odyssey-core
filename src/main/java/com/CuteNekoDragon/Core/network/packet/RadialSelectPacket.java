package com.CuteNekoDragon.Core.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class RadialSelectPacket {

    private final int slotIndex;

    public RadialSelectPacket(int slotIndex) {
        this.slotIndex = slotIndex;
    }

    public static void encode(RadialSelectPacket msg, FriendlyByteBuf buf) {
        buf.writeVarInt(msg.slotIndex);
    }

    public static RadialSelectPacket decode(FriendlyByteBuf buf) {
        return new RadialSelectPacket(buf.readVarInt());
    }

    public static void handle(RadialSelectPacket msg, Supplier<NetworkEvent.Context> ctxSup) {
        NetworkEvent.Context ctx = ctxSup.get();
        ctx.enqueueWork(() -> {
            ServerPlayer player = ctx.getSender();
            if (player != null) {
                // TODO: handle radial select
            }
        });
    }
}
