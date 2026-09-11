package com.CuteNekoDragon.Core.common.capability;

import lombok.Getter;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class MailCapability {

    public static final Capability<PlayerMailData> MAIL_DATA = CapabilityManager.get(new CapabilityToken<>() {});

    private MailCapability() {}

    public static LazyOptional<PlayerMailData> get(Player player) {
        return player.getCapability(MAIL_DATA);
    }

    public static PlayerMailData getOrDefault(Player player) {
        return get(player).orElseGet(PlayerMailData::new);
    }

    public static class Provider implements ICapabilityProvider, ICapabilitySerializable<CompoundTag> {

        @Getter
        private final PlayerMailData data = new PlayerMailData();
        private final LazyOptional<PlayerMailData> optional = LazyOptional.of(() -> data);

        @Override
        public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
            return MAIL_DATA.orEmpty(cap, optional);
        }

        @Override
        public CompoundTag serializeNBT() {
            return data.serializeNBT();
        }

        @Override
        public void deserializeNBT(CompoundTag tag) {
            data.deserializeNBT(tag);
        }
    }
}
