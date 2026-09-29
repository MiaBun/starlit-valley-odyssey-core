package com.CuteNekoDragon.Core.utils.lang;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public record LangEntry(String key) {

    public MutableComponent get(Object... args) {
        return Component.translatable(key, args);
    }
}
