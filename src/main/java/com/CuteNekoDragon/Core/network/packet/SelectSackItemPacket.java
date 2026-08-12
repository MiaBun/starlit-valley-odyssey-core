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

    // decode constructor — mirrors the vanilla `private ...Packet(FriendlyByteBuf input)`
    public static SelectSackItemPacket decode(FriendlyByteBuf buf) {
        int slotId = buf.readVarInt();
        int selectedItemIndex = buf.readVarInt();
        if (selectedItemIndex < 0 && selectedItemIndex != -1) {
            throw new IllegalArgumentException("Invalid selectedItemIndex: " + selectedItemIndex);
        }
        return new SelectSackItemPacket(slotId, selectedItemIndex);
    }

    // encode — mirrors vanilla's `write(FriendlyByteBuf output)`
    public static void encode(SelectSackItemPacket packet, FriendlyByteBuf buf) {
        buf.writeVarInt(packet.slotId);
        buf.writeVarInt(packet.selectedItemIndex);
    }

    // handle — mirrors vanilla's `handle(ServerGamePacketListener)`
    public static void handle(SelectSackItemPacket packet, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.enqueueWork(() -> {
            var player = ctx.getSender(); // ServerPlayer, null-checked below
            if (player == null) return;

            // TODO: look up the sack in player.getInventory() (or open container) at packet.slotId,
            // validate selectedItemIndex against SackItem.getNumberOfItemsToShow(...),
            // and store/apply the selection server-side.
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
