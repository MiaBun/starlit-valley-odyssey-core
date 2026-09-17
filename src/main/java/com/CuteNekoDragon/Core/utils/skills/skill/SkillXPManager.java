package com.CuteNekoDragon.Core.utils.skills.skill;

import com.CuteNekoDragon.Core.common.capability.SkillCapability;
import com.CuteNekoDragon.Core.network.SVONetworkHandler;
import com.CuteNekoDragon.Core.network.packet.SyncSkillDataPacket;
import com.CuteNekoDragon.Core.utils.skills.ability.Ability;
import com.CuteNekoDragon.Core.utils.skills.ability.AbilityRegistry;
import earth.terrarium.adastra.common.network.NetworkHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
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
        player.sendSystemMessage(Component.literal(
                type.getDisplayName() + " leveled up to " + newLevel + "!"
        ).withStyle(ChatFormatting.GOLD));

        if (AbilityRegistry.hasChoice(type, newLevel)) {
            player.sendSystemMessage(Component.literal(
                    "Choose an ability: /skills choose " + type.name() + " " + newLevel + " <A|B>"
            ).withStyle(ChatFormatting.YELLOW));
        }
    }

    public static boolean hasAbility(Player player, SkillType type, String abilityID) {
        return player.getCapability(SkillCapability.SKILL_DATA).map(data -> {
            for (int lvl = 1; lvl <= data.getLevel(type); lvl++) {
                int chosen = data.getChosenAbility(type, lvl);
                if (chosen ==  -1) continue;
                Ability ability = AbilityRegistry.getAbility(type, lvl, chosen);
                if (ability != null && ability.getID().equals(abilityID)) return true;
            }
            return false;
        }).orElse(false);
    }

    public static int xpForLevel(int level)  {
        return (int) Math.round(GlobalSkillData.CURVE_BASE * Math.pow(level, GlobalSkillData.CURVE_EXPONENT));
    }

    public static void sendTo(ServerPlayer player) {
        player.getCapability(SkillCapability.SKILL_DATA).ifPresent(data -> {
            if (data instanceof PlayerSkillData psd) {
                SVONetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new SyncSkillDataPacket(psd.serializeNBT()));
            }
        });
    }

    public static void onRecipeOutput(ServerPlayer owner, int recipeTierWeight) {
        int xp = GlobalSkillData.XP_TECH_TREE_BASE * Math.max(1, recipeTierWeight);
        addXP(owner, SkillType.TECH_TREE, xp);
    }
}
