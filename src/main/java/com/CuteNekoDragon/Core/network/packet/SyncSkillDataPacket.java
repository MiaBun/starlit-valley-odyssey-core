package com.CuteNekoDragon.Core.network.packet;

import com.CuteNekoDragon.Core.client.util.ClientSkillData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncSkillDataPacket {

    private final CompoundTag data;


    public SyncSkillDataPacket(CompoundTag data) {
        this.data = data;
    }

    public static void encode(SyncSkillDataPacket packet, FriendlyByteBuf buf) {
        buf.writeNbt(packet.data);
    }

    public static SyncSkillDataPacket decode(FriendlyByteBuf buf) {
        return new SyncSkillDataPacket(buf.readNbt());
    }

    public static void handle(SyncSkillDataPacket packet, Supplier<NetworkEvent.Context> ctxS) {
        NetworkEvent.Context ctx = ctxS.get();
        ctx.enqueueWork(() -> ClientSkillData.updateFrom(packet.data));
        ctx.setPacketHandled(true);
    }
}
