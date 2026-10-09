package com.CuteNekoDragon.Core.utils.tooltips.glyph;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.GsonHelper;

import com.CuteNekoDragon.Core.SVOCore;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Nullable;

public final class Glyphs {

    private static final Logger LOGGER = LogUtils.getLogger();
    private static final Set<ResourceLocation> WARNED = ConcurrentHashMap.newKeySet();

    private static final Map<ResourceLocation, Glyph> CODE = new ConcurrentHashMap<>();
    private static volatile Map<ResourceLocation, Glyph> data = Map.of();

    private Glyphs() {}

    public static void register(ResourceLocation id, Glyph glyph) {
        CODE.put(id, glyph);
    }

    @SuppressWarnings("removal")
    public static ResourceLocation qualify(String name) {
        return name.indexOf(':') >= 0 ? new ResourceLocation(name) : SVOCore.id(name);
    }

    @Nullable
    public static Glyph get(ResourceLocation id) {
        Glyph g = data.get(id);
        if (g == null) g = CODE.get(id);
        if (g == null && WARNED.add(id)) LOGGER.warn("Unknown tooltip glyph '{}'", id);
        return g;
    }

    public static void setData(Map<ResourceLocation, Glyph> glyphs) {
        data = Map.copyOf(glyphs);
        WARNED.clear();
    }

    @SuppressWarnings("removal")
    public static Map<ResourceLocation, Glyph> load(ResourceManager manager) {
        Map<ResourceLocation, Glyph> out = new HashMap<>();
        for (Map.Entry<ResourceLocation, Resource> e : manager
                .listResources("tooltip", p -> p.getPath().equals("tooltip/glyphs.json")).entrySet()) {
            String ns = e.getKey().getNamespace();
            try (Reader reader = e.getValue().openAsReader()) {
                JsonObject json = GsonHelper.parse(reader);
                for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
                    try {
                        out.put(new ResourceLocation(ns, entry.getKey()),
                                Glyph.fromJson(entry.getValue().getAsJsonObject()));
                    } catch (Exception ex) {
                        LOGGER.error("Invalid tooltip glyph '{}' in {}", entry.getKey(), e.getKey(), ex);
                    }
                }
            } catch (Exception ex) {
                LOGGER.error("Failed to load {}", e.getKey(), ex);
            }
        }
        return out;
    }
}
