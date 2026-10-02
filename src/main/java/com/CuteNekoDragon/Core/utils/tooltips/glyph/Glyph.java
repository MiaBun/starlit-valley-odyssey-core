package com.CuteNekoDragon.Core.utils.tooltips.glyph;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.utils.tooltips.util.Colors;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;

import javax.annotation.Nullable;

public record Glyph(ResourceLocation font, String chars, boolean tint, @Nullable TextColor color) {

    private static final TextColor WHITE = TextColor.fromLegacyFormat(ChatFormatting.WHITE);

    public static ResourceLocation defaultFont() {
        return SVOCore.id("icons");
    }

    public static Glyph of(ResourceLocation font, String chars) {
        return new Glyph(font, chars, false, null);
    }

    @SuppressWarnings("removal")
    public static Glyph fromJson(JsonObject json) {
        String chars = GsonHelper.getAsString(json, "char");
        if (chars.isEmpty()) throw new JsonParseException("Glyph 'char' must not be empty");
        ResourceLocation font = json.has("font")
                ? new ResourceLocation(GsonHelper.getAsString(json, "font"))
                : defaultFont();
        return new Glyph(font, chars, GsonHelper.getAsBoolean(json, "tint", false), Colors.text(json, "color"));
    }

    public MutableComponent toComponent(@Nullable TextColor fallbackTint) {
        TextColor c;
        if (!tint) c = WHITE; // white = the texture's real colours (font colour is a multiply)
        else c = color != null ? color : (fallbackTint != null ? fallbackTint : WHITE);
        return Component.literal(chars).withStyle(s -> s.withFont(font).withColor(c));
    }
}
