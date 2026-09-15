package com.CuteNekoDragon.Core.utils.skills.ability;

import net.minecraft.server.level.ServerPlayer;

public interface Ability {

    String getID();

    String getDescription();

    default void onChosen(ServerPlayer player) {}

    default void onRemoved(ServerPlayer player) {}
}
