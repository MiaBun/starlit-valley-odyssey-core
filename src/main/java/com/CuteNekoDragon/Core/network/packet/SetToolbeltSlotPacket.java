package com.CuteNekoDragon.Core.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import com.CuteNekoDragon.Core.common.container.ToolbeltContainer;

import java.util.function.Supplier;

public class SetToolbeltSlotPacket {

    private final int slot;

    public SetToolbeltSlotPacket(int slot) {
        this.slot = slot;
    }

    public SetToolbeltSlotPacket(FriendlyByteBuf buf) {
        this.slot = buf.readVarInt();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeVarInt(slot);
    }

    public void handle(Supplier<NetworkEvent.Context> ctxC) {
        NetworkEvent.Context ctx = ctxC.get();
        ctx.enqueueWork(() -> {
            ServerPlayer player = ctx.getSender();
            if (player == null) return;

            ToolbeltContainer container = ToolbeltContainer.getOpenContainerFor(player);
            if (container != null) {
                container.setSelectedSlot(slot);
            }
        });
        ctx.setPacketHandled(true);
    }
}
