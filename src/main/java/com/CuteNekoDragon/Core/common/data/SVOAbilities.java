package com.CuteNekoDragon.Core.common.data;

import com.CuteNekoDragon.Core.common.ability.SimpleAbility;
import com.CuteNekoDragon.Core.utils.skills.ability.AbilityRegistry;
import com.CuteNekoDragon.Core.utils.skills.skill.SkillType;

public class SVOAbilities {

    public static void init() {
        registerMining();
        registerFarming();
        registerForaging();
        registerFishing();
        registerCombat();
        registerTechTree();
        registerElderEda();
    }

    private static void registerMining() {
        //AbilityRegistry.register(SkillType.MINING, 1, new SimpleAbility("", ""), new SimpleAbility("", ""));
    }

    private static void registerFarming() {

    }

    private static void registerForaging() {

    }

    private static void registerFishing() {

    }

    private static void registerCombat() {

    }

    private static void registerTechTree() {

    }

    private static void registerElderEda() {

    }
}
