package com.CuteNekoDragon.Core.utils.skills.ability;

import com.CuteNekoDragon.Core.utils.skills.skill.SkillType;

import java.util.EnumMap;
import java.util.Map;

public class AbilityRegistry {

    private static final Map<SkillType, Ability[][]> REGISTRY = new EnumMap<>(SkillType.class);

    public static void register(SkillType type, int level, Ability optionA, Ability optionB) {
        REGISTRY.computeIfAbsent(type, t -> new Ability[SkillType.MAX_LEVEL + 1][2]);
        REGISTRY.get(type)[level][0] = optionA;
        REGISTRY.get(type)[level][1] = optionB;
    }

    public static boolean hasChoice(SkillType type, int level) {
        Ability[][] byLevel = REGISTRY.get(type);
        return byLevel != null && level < byLevel.length && byLevel[level][0] != null;
    }

    public static Ability getAbility(SkillType type, int level, int option) {
        Ability[][] byLevel = REGISTRY.get(type);
        if (byLevel == null || level < 0 || level >= byLevel.length) return null;
        return byLevel[level][option];
    }
}
