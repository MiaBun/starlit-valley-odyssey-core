package com.CuteNekoDragon.Core.network;

import com.CuteNekoDragon.Core.network.packet.SetToolbeltSlotPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.network.packet.SelectSackItemPacket;

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
    }
}
