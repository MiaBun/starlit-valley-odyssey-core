package com.CuteNekoDragon.Core.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SelectSackItemPacket {

    private final int slotId;
    private final int selectedItemIndex;

    public SelectSackItemPacket(int slotId, int selectedItemIndex) {
        this.slotId = slotId;
        this.selectedItemIndex = selectedItemIndex;
    }

    public static SelectSackItemPacket decode(FriendlyByteBuf buf) {
        int slotId = buf.readVarInt();
        int selectedItemIndex = buf.readVarInt();
        if (selectedItemIndex < 0 && selectedItemIndex != -1) {
            throw new IllegalArgumentException("Invalid selectedItemIndex: " + selectedItemIndex);
        }
        return new SelectSackItemPacket(slotId, selectedItemIndex);
    }

    public static void encode(SelectSackItemPacket packet, FriendlyByteBuf buf) {
        buf.writeVarInt(packet.slotId);
        buf.writeVarInt(packet.selectedItemIndex);
    }

    public static void handle(SelectSackItemPacket packet, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.enqueueWork(() -> {
            var player = ctx.getSender();
            if (player == null) return;

        });
        ctx.setPacketHandled(true);
    }

    public int getSlotId() {
        return slotId;
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }
}
