package com.CuteNekoDragon.Core.utils.skills.skill;

import lombok.Getter;

public enum SkillType {

    FARMING("Farming"),
    MINING("Mining"),
    FORAGING("Foraging"),
    FISHING("Fishing"),
    COMBAT("Combat"),
    TECH_TREE("Tech Tree"),
    ELDER_EDA("The Elder Eda");

    public static final int MAX_LEVEL = 15;

    @Getter
    private final String displayName;

    SkillType(String displayName) {
        this.displayName = displayName;
    }

}
