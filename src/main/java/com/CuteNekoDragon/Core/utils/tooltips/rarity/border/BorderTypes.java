package com.CuteNekoDragon.Core.utils.tooltips.rarity.border;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.util.GsonHelper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class BorderTypes {

    @FunctionalInterface
    public interface Factory {
        BorderRenderer create(JsonObject json);
    }

    private static final Map<String, Factory> TYPES = new ConcurrentHashMap<>();

    static {

    }

    private BorderTypes() {}

    public static void register(String type, Factory factory) {
        TYPES.put(type, factory);
    }

    public static BorderRenderer parse(JsonObject json) {
        String type = GsonHelper.getAsString(json, "type", "gradient");
        Factory factory = TYPES.get(type);
        if (factory == null) throw new JsonParseException("Unknown tooltip border type '" + type + "' (known: " + TYPES.keySet() + ")");
        return factory.create(json);
    }
}
