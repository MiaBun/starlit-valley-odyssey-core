package com.CuteNekoDragon.Core.client.util;

import net.minecraft.nbt.CompoundTag;

import com.CuteNekoDragon.Core.utils.skills.skill.PlayerSkillData;
import lombok.Getter;

public class ClientSkillData {

    @Getter
    private static final PlayerSkillData DATA = new PlayerSkillData();

    public static void updateFrom(CompoundTag tag) {
        DATA.deserializeNBT(tag);
    }
}
