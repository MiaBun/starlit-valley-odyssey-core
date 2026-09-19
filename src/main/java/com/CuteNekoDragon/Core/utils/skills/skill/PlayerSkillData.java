package com.CuteNekoDragon.Core.utils.skills.skill;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class PlayerSkillData implements ISkillData, INBTSerializable<CompoundTag> {

    private final Map<SkillType, Integer> xpMap = new EnumMap<>(SkillType.class);
    private final Map<SkillType, Integer> levelMap = new EnumMap<>(SkillType.class);
    private final Map<SkillType, int[]> abilityMap = new EnumMap<>(SkillType.class);

    public PlayerSkillData() {
        for (SkillType type : SkillType.values()) {
            xpMap.put(type, 0);
            levelMap.put(type, 0);
            int[] arr = new int[SkillType.MAX_LEVEL + 1];
            Arrays.fill(arr, -1);
            abilityMap.put(type, arr);
        }
    }

    @Override
    public int getXP(SkillType type) {
        return xpMap.getOrDefault(type, 0);
    }

    @Override
    public void setXP(SkillType type, int xp) {
        xpMap.put(type, xp);
    }

    @Override
    public int getLevel(SkillType type) {
        return levelMap.getOrDefault(type, 0);
    }

    @Override
    public void setLevel(SkillType type, int level) {
        levelMap.put(type, level);
    }

    @Override
    public int getChosenAbility(SkillType type, int level) {
        return abilityMap.get(type)[level];
    }

    @Override
    public void setChosenAbility(SkillType type, int level, int option) {
        abilityMap.get(type)[level] = option;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag root = new CompoundTag();
        for (SkillType type : SkillType.values()) {
            CompoundTag t = new CompoundTag();
            t.putInt("xp", xpMap.get(type));
            t.putInt("level", levelMap.get(type));
            t.putIntArray("abilities", abilityMap.get(type));
            root.put(type.name(), t);
        }
        return root;
    }

    @Override
    public void deserializeNBT(CompoundTag compoundTag) {
        for (SkillType type : SkillType.values()) {
            if (compoundTag.contains(type.name())) {
                CompoundTag t = compoundTag.getCompound(type.name());
                xpMap.put(type, t.getInt("xp"));
                levelMap.put(type, t.getInt("level"));
                int[] arr = t.getIntArray("abilities");
                if (arr.length == SkillType.MAX_LEVEL + 1) {
                    abilityMap.put(type, arr);
                }
            }
        }
    }

    public void copyFrom(PlayerSkillData other) {
        this.deserializeNBT(other.serializeNBT());
    }
}
