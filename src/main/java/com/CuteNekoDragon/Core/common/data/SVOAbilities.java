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
        AbilityRegistry.register(SkillType.FARMING, 1, new SimpleAbility("farming_quality_1", "Boosts Crop Quality Frequency by 2%"), new SimpleAbility("farming_double_1", "Boosts Double Crop Chance by 5%"));
        AbilityRegistry.register(SkillType.FARMING, 2, new SimpleAbility("farming_quality_2", "Boosts Crop Quality Frequency by 2%"), new SimpleAbility("farming_double_2", "Boosts Double Crop Chance by 5%"));
        AbilityRegistry.register(SkillType.FARMING, 3, new SimpleAbility("farming_quality_3", "Boosts Crop Quality Frequency by 2%"), new SimpleAbility("farming_double_3", "Boosts Double Crop Chance by 5%"));
        AbilityRegistry.register(SkillType.FARMING, 4, new SimpleAbility("farming_quality_4", "Boosts Crop Quality Frequency by 2%"), new SimpleAbility("farming_double_4", "Boosts Double Crop Chance by 5%"));
        AbilityRegistry.register(SkillType.FARMING, 5, new SimpleAbility("farming_rancher", "Rancher: Animal products worth 20% more."), new SimpleAbility("farming_tiller", "Tiller: Crops worth 10% more."));
        AbilityRegistry.register(SkillType.FARMING, 6, new SimpleAbility("farming_quality_6", "Boosts Crop Quality Frequency by 2%"), new SimpleAbility("farming_double_6", "Boosts Double Crop Chance by 5%"));
        AbilityRegistry.register(SkillType.FARMING, 7, new SimpleAbility("farming_quality_7", "Boosts Crop Quality Frequency by 2%"), new SimpleAbility("farming_double_7", "Boosts Double Crop Chance by 5%"));
        AbilityRegistry.register(SkillType.FARMING, 8, new SimpleAbility("farming_quality_8", "Boosts Crop Quality Frequency by 2%"), new SimpleAbility("farming_double_8", "Boosts Double Crop Chance by 5%"));
        AbilityRegistry.register(SkillType.FARMING, 9, new SimpleAbility("farming_quality_9", "Boosts Crop Quality Frequency by 2%"), new SimpleAbility("farming_double_9", "Boosts Double Crop Chance by 5%"));
        AbilityRegistry.register(SkillType.FARMING, 10, new SimpleAbility("farming_coopmaster", "Coopmaster: Befriend coop animals quicker, incubation time cut in half."), new SimpleAbility("farming_artisan", "Artisan goods worth 40% more."));
        AbilityRegistry.register(SkillType.FARMING, 11, new SimpleAbility("farming_quality_11", "Boosts Crop Quality Frequency by 2%"), new SimpleAbility("farming_double_11", "Boosts Double Crop Chance by 5%"));
        AbilityRegistry.register(SkillType.FARMING, 12, new SimpleAbility("farming_quality_12", "Boosts Crop Quality Frequency by 2%"), new SimpleAbility("farming_double_12", "Boosts Double Crop Chance by 5%"));
        AbilityRegistry.register(SkillType.FARMING, 13, new SimpleAbility("farming_quality_13", "Boosts Crop Quality Frequency by 2%"), new SimpleAbility("farming_double_13", "Boosts Double Crop Chance by 5%"));
        AbilityRegistry.register(SkillType.FARMING, 14, new SimpleAbility("farming_quality_14", "Boosts Crop Quality Frequency by 2%"), new SimpleAbility("farming_double_14", "Boosts Double Crop Chance by 5%"));
        AbilityRegistry.register(SkillType.FARMING, 15, new SimpleAbility("farming_shepherd", "Befriend Barn animals quicker, Sheep produce wool faster."), new SimpleAbility("farming_agriculturist", "Agriculturist: All crops grow 10% faster."));
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
