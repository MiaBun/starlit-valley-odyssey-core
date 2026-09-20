package com.CuteNekoDragon.Core.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import com.CuteNekoDragon.Core.client.util.ClientMailCache;
import com.CuteNekoDragon.Core.utils.mail.Letter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class SyncMailDataPacket {

    private final List<Letter> letters;
    private final boolean toastEnabled;
    private final boolean indicatorEnabled;

    public SyncMailDataPacket(List<Letter> letters, boolean toastEnabled, boolean indicatorEnabled) {
        this.letters = letters;
        this.toastEnabled = toastEnabled;
        this.indicatorEnabled = indicatorEnabled;
    }

    public SyncMailDataPacket(FriendlyByteBuf buf) {
        int count = buf.readVarInt();
        this.letters = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            letters.add(Letter.readFromBuf(buf));
        }
        this.toastEnabled = buf.readBoolean();
        this.indicatorEnabled = buf.readBoolean();
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeVarInt(letters.size());
        for (Letter letter : letters) {
            letter.writeToBuf(buf);
        }
        buf.writeBoolean(toastEnabled);
        buf.writeBoolean(indicatorEnabled);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        NetworkEvent.Context ctx = context.get();
        ctx.enqueueWork(() -> ClientMailCache.update(letters, toastEnabled, indicatorEnabled));
        ctx.setPacketHandled(true);
    }
}
