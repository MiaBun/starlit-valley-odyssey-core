package com.CuteNekoDragon.Core.network;

import com.CuteNekoDragon.Core.network.packet.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import com.CuteNekoDragon.Core.SVOCore;

public class SVONetworkHandler {

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            ResourceLocation.fromNamespaceAndPath(SVOCore.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals);

    private static int packetId = 0;

    private static int id() {
        return packetId++;
    }

    public static void init() {
        INSTANCE.registerMessage(id(),
                SelectSackItemPacket.class,
                SelectSackItemPacket::encode,
                SelectSackItemPacket::decode,
                SelectSackItemPacket::handle);

        INSTANCE.registerMessage(id(),
                SetToolbeltSlotPacket.class,
                SetToolbeltSlotPacket::encode,
                SetToolbeltSlotPacket::new,
                SetToolbeltSlotPacket::handle);

        INSTANCE.registerMessage(id(),
                OpenToolbeltPacket.class,
                OpenToolbeltPacket::encode,
                OpenToolbeltPacket::decode,
                OpenToolbeltPacket::handle);

        INSTANCE.registerMessage(id(),
                RadialSelectPacket.class,
                RadialSelectPacket::encode,
                RadialSelectPacket::decode,
                RadialSelectPacket::handle);

        INSTANCE.registerMessage(id(),
                SyncMailDataPacket.class,
                SyncMailDataPacket::write,
                SyncMailDataPacket::new,
                SyncMailDataPacket::handle);

        INSTANCE.registerMessage(id(),
                NewMailToastPacket.class,
                NewMailToastPacket::write,
                NewMailToastPacket::new,
                NewMailToastPacket::handle);
    }

    public static void sendLetterToPlayer(ServerPlayer player, Object packet) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), packet);
    }
}
