package com.CuteNekoDragon.Core.utils.tooltips.glyph;

import com.google.gson.JsonElement;
import net.minecraft.resources.ResourceLocation;
import javax.annotation.Nullable;

public record GlyphRef(@Nullable ResourceLocation name, @Nullable Glyph inline) {

    public static GlyphRef parse(JsonElement json) {
        if (json.isJsonObject()) return new GlyphRef(null, Glyph.fromJson(json.getAsJsonObject()));
        return new GlyphRef(Glyphs.qualify(json.getAsString()), null);
    }

    @Nullable
    public Glyph resolve() {
        return inline != null ? inline : Glyphs.get(name);
    }
}
