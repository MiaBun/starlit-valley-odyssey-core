package com.CuteNekoDragon.Core.utils.tooltips;

import net.minecraft.network.chat.TextColor;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TooltipStyle {

    public TooltipStyle(List<String> order, Placement placement, boolean modItemsOnly, String separatorText, @Nullable TextColor separatorColor, String detailIndent, @Nullable TextColor detailColor, CustomTextSettings customText) {
        this.order = order;
        this.placement = placement;
        this.modItemsOnly = modItemsOnly;
        this.separatorText = separatorText;
        this.separatorColor = separatorColor;
        this.detailIndent = detailIndent;
        this.detailColor = detailColor;
        this.customText = customText;
    }

    public enum Placement { AFTER_NAME, END }

    public record CustomTextSettings(int linesPerPage, int maxWidth, @Nullable TextColor color, boolean pageIndicator, float autoPageSeconds, boolean ctrlScrollFlips) {}

    static final String DEFAULT_JSON = """
            {
              "placement": "after_name",
              "mod_items_only": false,
              "order": ["rarity", "price", "mana", "eu", "life_essence", "source",
                        "separator", "custom_text", "separator", "shift_hint"],
              "separator": { "text": "", "color": "dark_gray" },
              "details": { "indent": "  ", "color": "gray" },
              "custom_text": {
                "lines_per_page": 4, "max_width": 200, "color": "gray",
                "page_indicator": true, "auto_page_seconds": 0, "ctrl_scroll_flips": true
              },
              "values": {
                "price":        { "icon": "◈", "icon_color": "gold",         "label_color": "gray", "value_color": "gold",         "format": "full",    "decimals": 2, "stack_total": true },
                "mana":         { "icon": "✦", "icon_color": "aqua",         "label_color": "gray", "value_color": "aqua",         "format": "compact" },
                "eu":           { "icon": "⚡", "icon_color": "yellow",       "label_color": "gray", "value_color": "yellow",       "format": "compact", "unit": " EU" },
                "life_essence": { "icon": "❤", "icon_color": "dark_red",     "label_color": "gray", "value_color": "red",          "format": "compact", "unit": " LP" },
                "source":       { "icon": "✧", "icon_color": "light_purple", "label_color": "gray", "value_color": "light_purple", "format": "compact" }
              }
            }
            """;

    private static volatile TooltipStyle current;

    private final List<String> order;
    private final Placement placement;
    private final boolean modItemsOnly;
    private final String separatorText;
    @Nullable private final TextColor separatorColor;
    private final String detailIndent;
    @Nullable private final TextColor detailColor;
    private final CustomTextSettings customText;

}
