package com.CuteNekoDragon.Core.utils.skills.skill;

import com.CuteNekoDragon.Core.common.capability.SkillCapability;
import com.CuteNekoDragon.Core.network.SVONetworkHandler;
import com.CuteNekoDragon.Core.network.packet.SyncSkillDataPacket;
import earth.terrarium.adastra.common.network.NetworkHandler;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.PacketDistributor;

public class SkillXPManager {

    public static void addXP(ServerPlayer player, SkillType type, int amount) {
        if (amount <= 0) return;

        player.getCapability(SkillCapability.SKILL_DATA).ifPresent(data -> {
            int level = data.getLevel(type);
            if (level >= SkillType.MAX_LEVEL) return;

            int xp = data.getXP(type) + amount;
            int needed = xpForLevel(level + 1);

            while (xp >= needed && level < SkillType.MAX_LEVEL) {
                xp -= needed;
                level++;
                onLevelUp(player, type, level);
                needed = xpForLevel(level + 1);
            }

            data.setXP(type, xp);
            data.setLevel(type, level);
        });
        sendTo(player);
    }

    private static void onLevelUp(ServerPlayer player, SkillType type, int newLevel) {

    }

    public static boolean hasAbility(Player player, SkillType type, String abilityID) {
        return false;
    }

    public static int xpForLevel(int level)  {
        return (int) Math.round(50 * Math.pow(level, 1.6));
    }

    public static void sendTo(ServerPlayer player) {
        player.getCapability(SkillCapability.SKILL_DATA).ifPresent(data -> {
            if (data instanceof PlayerSkillData psd) {
                SVONetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new SyncSkillDataPacket(psd.serializeNBT()));
            }
        });
    }
}
