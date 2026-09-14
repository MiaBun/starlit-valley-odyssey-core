package com.CuteNekoDragon.Core.common.capability;

import com.CuteNekoDragon.Core.utils.skills.skill.ISkillData;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SkillCapability {

    public static final Capability<ISkillData> SKILL_DATA = CapabilityManager.get(new CapabilityToken<>() {
    });

    public static class Provider implements ICapabilitySerializable<CompoundTag> {



        @Override
        public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction direction) {
            return null;
        }

        @Override
        public CompoundTag serializeNBT() {
            return null;
        }

        @Override
        public void deserializeNBT(CompoundTag compoundTag) {

        }
    }
}
