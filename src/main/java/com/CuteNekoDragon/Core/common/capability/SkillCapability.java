package com.CuteNekoDragon.Core.common.capability;

import com.CuteNekoDragon.Core.utils.skills.skill.ISkillData;
import com.CuteNekoDragon.Core.utils.skills.skill.PlayerSkillData;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class SkillCapability {

    public static final Capability<ISkillData> SKILL_DATA = CapabilityManager.get(new CapabilityToken<>() {
    });

    public static PlayerSkillData getOrDefault(Entity entity) {
        return (PlayerSkillData) entity.getCapability(SKILL_DATA)
                .orElseThrow(() -> new IllegalStateException("Skill data capability missing"));
    }

    public static class Provider implements ICapabilitySerializable<CompoundTag> {
        private final PlayerSkillData data = new PlayerSkillData();
        private final LazyOptional<ISkillData> optional = LazyOptional.of(() -> data);


        @Nonnull
        @Override
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
            return cap == SKILL_DATA ? optional.cast() : LazyOptional.empty();
        }

        @Override public CompoundTag serializeNBT() { return data.serializeNBT(); }
        @Override public void deserializeNBT(CompoundTag nbt) { data.deserializeNBT(nbt); }
    }
}
