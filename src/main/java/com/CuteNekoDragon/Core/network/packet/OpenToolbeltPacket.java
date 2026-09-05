package com.CuteNekoDragon.Core.network.packet;

import com.CuteNekoDragon.Core.common.component.ToolbeltLogic;
import earth.terrarium.adastra.common.network.NetworkHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class OpenToolbeltPacket {

    public OpenToolbeltPacket() {

    }

    public OpenToolbeltPacket(FriendlyByteBuf buf) {

    }

    public static void encode(OpenToolbeltPacket msg, FriendlyByteBuf buf) {
    }

    public static OpenToolbeltPacket decode(FriendlyByteBuf buf) {
        return new OpenToolbeltPacket();
    }

    public static void handle(OpenToolbeltPacket msg, Supplier<NetworkEvent.Context> ctxSup) {
        NetworkEvent.Context ctx = ctxSup.get();
        ctx.enqueueWork(() -> {
            ServerPlayer player = ctx.getSender();
            if (player != null) {
                ToolbeltLogic.handleOpen(player);
            }
        });
        ctx.setPacketHandled(true);
    }
}
