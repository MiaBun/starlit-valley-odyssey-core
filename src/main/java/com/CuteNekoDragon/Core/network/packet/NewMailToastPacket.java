package com.CuteNekoDragon.Core.network.packet;


import com.CuteNekoDragon.Core.client.util.NewMailToast;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class NewMailToastPacket {

    private final Component npcName;
    private final Component letterTitle;

    public NewMailToastPacket(Component npcName, Component letterTitle) {
        this.letterTitle = letterTitle;
        this.npcName = npcName;
    }

    public NewMailToastPacket(FriendlyByteBuf buf) {
        this.npcName = buf.readComponent();
        this.letterTitle = buf.readComponent();
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeComponent(npcName);
        buf.writeComponent(letterTitle);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        NetworkEvent.Context ctx = context.get();
        ctx.enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () ->
                Minecraft.getInstance().getToasts().addToast(new NewMailToast(npcName, letterTitle))));
        ctx.setPacketHandled(true);
    }
}
