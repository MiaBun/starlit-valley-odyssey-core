package com.CuteNekoDragon.Core.utils.tooltips.value;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.utils.tooltips.glyph.GlyphRef;
import com.CuteNekoDragon.Core.utils.tooltips.util.Colors;
import com.CuteNekoDragon.Core.utils.tooltips.util.NumberFormatter;
import com.CuteNekoDragon.Core.utils.tooltips.util.TextUtil;
import com.google.gson.JsonObject;

import javax.annotation.Nullable;

public record ValueDefinition(
                              String id,
                              TagKey<Item> tag,
                              boolean requireTag,
                              String labelKey,
                              String labelFallback,
                              String icon,
                              @Nullable GlyphRef glyph,
                              boolean glyphAtEnd,
                              @Nullable TextColor iconColor,
                              @Nullable TextColor labelColor,
                              @Nullable TextColor valueColor,
                              String unit,
                              NumberFormatter.Style numberStyle,
                              int decimals,
                              boolean showStackTotal,
                              boolean hideZero) {

    @SuppressWarnings("removal")
    public static ValueDefinition parse(String id, JsonObject json) {
        String tagId = GsonHelper.getAsString(json, "tag", SVOCore.MOD_ID + ":tooltip/" + id);
        return new ValueDefinition(
                id,
                TagKey.create(Registries.ITEM, new ResourceLocation(tagId)),
                GsonHelper.getAsBoolean(json, "require_tag", true),
                GsonHelper.getAsString(json, "label", SVOCore.key("value." + id)),
                TextUtil.titleCase(id),
                GsonHelper.getAsString(json, "icon", ""),
                json.has("glyph") ? GlyphRef.parse(json.get("glyph")) : null,
                "end".equals(GsonHelper.getAsString(json, "glyph_position", "start")),
                Colors.text(json, "icon_color"),
                Colors.text(json, "label_color"),
                Colors.text(json, "value_color"),
                GsonHelper.getAsString(json, "unit", ""),
                NumberFormatter.Style.parse(GsonHelper.getAsString(json, "format", "full")),
                GsonHelper.getAsInt(json, "decimals", 1),
                GsonHelper.getAsBoolean(json, "stack_total", false),
                GsonHelper.getAsBoolean(json, "hide_zero", true));
    }

    public String format(double amount) {
        return NumberFormatter.format(amount, numberStyle, decimals) + unit;
    }
}
