package com.CuteNekoDragon.Core.client.util;

import com.CuteNekoDragon.Core.utils.skills.skill.PlayerSkillData;
import lombok.Getter;
import net.minecraft.nbt.CompoundTag;

public class ClientSkillData {

    @Getter
    private static final PlayerSkillData DATA = new PlayerSkillData();

    public static void updateFrom(CompoundTag tag) {
        DATA.deserializeNBT(tag);
    }

}
