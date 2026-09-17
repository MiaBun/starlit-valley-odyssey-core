package com.CuteNekoDragon.Core.common.ability;

import com.CuteNekoDragon.Core.utils.skills.ability.Ability;
import net.minecraft.server.level.ServerPlayer;

import java.util.function.Consumer;

public class SimpleAbility implements Ability {

    private final String id;
    private final String description;
    private final Consumer<ServerPlayer> onChosen;

    public SimpleAbility(String id, String description) {
        this(id, description, p -> {});
    }

    public SimpleAbility(String id, String description, Consumer<ServerPlayer> onChosen) {
        this.id = id;
        this.description = description;
        this.onChosen = onChosen;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void onChosen(ServerPlayer player) {
        onChosen.accept(player);
    }
}
