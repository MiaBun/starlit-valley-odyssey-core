package com.CuteNekoDragon.Core.utils.tooltips.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.network.chat.TextColor;

import javax.annotation.Nullable;

public final class Colors {
    private Colors() {}

    public static int argb(JsonElement el) {
        if (el.isJsonPrimitive() && el.getAsJsonPrimitive().isNumber()) {
            return (int) el.getAsLong();
        }
        return argb(el.getAsString());
    }

    public static int argb(String s) {
        String hex = s.trim();
        if (hex.startsWith("#")) hex = hex.substring(1);
        else if (hex.startsWith("0x") || hex.startsWith("0X")) hex = hex.substring(2);
        try {
            long v = Long.parseLong(hex, 16);
            if (hex.length() <= 6) v |= 0xFF000000L;
            return (int) v;
        } catch (NumberFormatException e) {
            throw new JsonParseException("Invalid colour '" + s + "', expected #RRGGBB or #AARRGGBB");
        }
    }

    public static int argb(JsonObject json, String key, int fallback) {
        return json.has(key) ? argb(json.get(key)) : fallback;
    }

    @Nullable
    public static TextColor text(JsonObject json, String key) {
        if (!json.has(key) || json.get(key).isJsonNull()) return null;
        String raw = json.get(key).getAsString();
        TextColor c = TextColor.parseColor(raw);
        if (c == null) throw new JsonParseException("Invalid text colour '" + raw + "'");
        return c;
    }

    public static int lerp(int a, int b, float t) {
        int aa = a >>> 24, ar = (a >> 16) & 0xFF, ag = (a >> 8) & 0xFF, ab = a & 0xFF;
        int ba = b >>> 24, br = (b >> 16) & 0xFF, bg = (b >> 8) & 0xFF, bb = b & 0xFF;
        return ((int) (aa + (ba - aa) * t) << 24)
                | ((int) (ar + (br - ar) * t) << 16)
                | ((int) (ag + (bg - ag) * t) << 8)
                | (int) (ab + (bb - ab) * t);
    }

    public static int withAlpha(int argb, int alpha) {
        return (Math.max(0, Math.min(255, alpha)) << 24) | (argb & 0xFFFFFF);
    }

    public static int sample(int[] palette, float t) {
        if (palette.length == 1) return palette[0];
        float pos = (t - (float) Math.floor(t)) * palette.length;
        int i = (int) pos;
        return lerp(palette[i % palette.length], palette[(i + 1) % palette.length], pos - i);
    }
}
