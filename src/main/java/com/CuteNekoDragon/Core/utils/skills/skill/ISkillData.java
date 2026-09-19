package com.CuteNekoDragon.Core.utils.skills.skill;

public interface ISkillData {

    int getXP(SkillType type);

    void setXP(SkillType type, int xp);

    int getLevel(SkillType type);

    void setLevel(SkillType type, int level);

    int getChosenAbility(SkillType type, int level);

    void setChosenAbility(SkillType type, int level, int option);
}
