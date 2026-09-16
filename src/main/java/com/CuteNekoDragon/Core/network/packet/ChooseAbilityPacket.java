package com.CuteNekoDragon.Core.network.packet;

import com.CuteNekoDragon.Core.common.capability.SkillCapability;
import com.CuteNekoDragon.Core.utils.skills.ability.Ability;
import com.CuteNekoDragon.Core.utils.skills.ability.AbilityRegistry;
import com.CuteNekoDragon.Core.utils.skills.skill.SkillType;
import com.CuteNekoDragon.Core.utils.skills.skill.SkillXPManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ChooseAbilityPacket {

    private final SkillType type;
    private final int level;
    private final int option;

    public ChooseAbilityPacket(SkillType type, int level, int option) {
        this.type = type;
        this.level = level;
        this.option = option;
    }

    public static void encode(ChooseAbilityPacket packet, FriendlyByteBuf buf) {
        buf.writeEnum(packet.type);
        buf.writeVarInt(packet.level);
        buf.writeVarInt(packet.option);
    }

    public static ChooseAbilityPacket decode(FriendlyByteBuf buf) {
        return new ChooseAbilityPacket(buf.readEnum(SkillType.class), buf.readVarInt(), buf.readVarInt());
    }

    public static void handle(ChooseAbilityPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context ctx = contextSupplier.get();
        ctx.enqueueWork(() -> {
            ServerPlayer sp = ctx.getSender();
            if(sp == null) return;

            sp.getCapability(SkillCapability.SKILL_DATA).ifPresent(data -> {
                if (data.getLevel(packet.type) < packet.level) return;
                if(data.getChosenAbility(packet.type, packet.level) != -1) return;

                Ability ability = AbilityRegistry.getAbility(packet.type, packet.level, packet.option);
                if(ability == null) return;

                data.setChosenAbility(packet.type, packet.level, packet.option);
                ability.onChosen(sp);
            });

            SkillXPManager.sendTo(sp);
        });
        ctx.setPacketHandled(true);
    }
}
