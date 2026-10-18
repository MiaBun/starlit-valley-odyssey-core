package com.CuteNekoDragon.Core.utils.price;

import net.minecraft.resources.ResourceLocation;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class PriceModifiers {

    private static final Map<ResourceLocation, PriceModifier> MODIFIERS = Collections.synchronizedMap(new LinkedHashMap<>());

    private PriceModifiers() {}

    public static void register(ResourceLocation id, PriceModifier modifier) {
        MODIFIERS.put(id, modifier);
    }

    public static void unregister(ResourceLocation id) {
        MODIFIERS.remove(id);
    }

    public static Collection<PriceModifier> all() {
        synchronized (MODIFIERS) {
            return java.util.List.copyOf(MODIFIERS.values());
        }
    }

    public static void registerModifiers() {

    }
}
