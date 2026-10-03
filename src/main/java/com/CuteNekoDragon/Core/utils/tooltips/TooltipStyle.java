package com.CuteNekoDragon.Core.utils.tooltips;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.utils.tooltips.element.TooltipElement;
import com.CuteNekoDragon.Core.utils.tooltips.element.ValueElement;
import com.CuteNekoDragon.Core.utils.tooltips.util.Colors;
import com.CuteNekoDragon.Core.utils.tooltips.value.ValueDefinition;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class TooltipStyle {

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
    private final Map<String, ValueDefinition> values;
    private final Map<String, TooltipElement> valueElements;
    private final TagKey<Item> styledTag;

    private TooltipStyle(JsonObject json) {
        List<String> order = new ArrayList<>();
        for (JsonElement el : GsonHelper.getAsJsonArray(json, "order")) order.add(el.getAsString());
        this.order = Collections.unmodifiableList(order);

        this.placement = "end".equals(GsonHelper.getAsString(json, "placement", "after_name").toLowerCase(Locale.ROOT))
                ? Placement.END : Placement.AFTER_NAME;
        this.modItemsOnly = GsonHelper.getAsBoolean(json, "mod_items_only", false);

        JsonObject sep = GsonHelper.getAsJsonObject(json, "separator", new JsonObject());
        this.separatorText = GsonHelper.getAsString(sep, "text", "");
        this.separatorColor = Colors.text(sep, "color");

        JsonObject details = GsonHelper.getAsJsonObject(json, "details", new JsonObject());
        this.detailIndent = GsonHelper.getAsString(details, "indent", "  ");
        this.detailColor = Colors.text(details, "color");

        JsonObject ct = GsonHelper.getAsJsonObject(json, "custom_text", new JsonObject());
        this.customText = new CustomTextSettings(
                GsonHelper.getAsInt(ct, "lines_per_page", 4),
                GsonHelper.getAsInt(ct, "max_width", 200),
                Colors.text(ct, "color"),
                GsonHelper.getAsBoolean(ct, "page_indicator", true),
                GsonHelper.getAsFloat(ct, "auto_page_seconds", 0f),
                GsonHelper.getAsBoolean(ct, "ctrl_scroll_flips", true));

        Map<String, ValueDefinition> values = new LinkedHashMap<>();
        Map<String, TooltipElement> elements = new LinkedHashMap<>();
        for (Map.Entry<String, JsonElement> e : GsonHelper.getAsJsonObject(json, "values", new JsonObject()).entrySet()) {
            ValueDefinition def = ValueDefinition.parse(e.getKey(), e.getValue().getAsJsonObject());
            values.put(def.id(), def);
            elements.put(def.id(), new ValueElement(def));
        }
        this.values = Collections.unmodifiableMap(values);
        this.valueElements = Collections.unmodifiableMap(elements);
        this.styledTag = TagKey.create(Registries.ITEM, SVOCore.id("tooltip/styled"));
    }

    public static TooltipStyle parse(JsonObject json) {
        JsonObject merged = JsonParser.parseString(DEFAULT_JSON).getAsJsonObject();
        for (Map.Entry<String, JsonElement> e : json.entrySet()) {
            if (e.getKey().equals("values") && e.getValue().isJsonObject()) {
                JsonObject mergedValues = merged.getAsJsonObject("values");
                for (Map.Entry<String, JsonElement> v : e.getValue().getAsJsonObject().entrySet()) {
                    if (v.getValue().isJsonNull()) mergedValues.remove(v.getKey()); // "eu": null removes a default type
                    else mergedValues.add(v.getKey(), v.getValue());
                }
            } else {
                merged.add(e.getKey(), e.getValue());
            }
        }
        return new TooltipStyle(merged);
    }

    public static TooltipStyle current() {
        TooltipStyle s = current;
        if (s == null) {
            s = parse(new JsonObject());
            current = s;
        }
        return s;
    }

    static void set(TooltipStyle style) {
        current = style;
    }

    /** Whether this system should touch the given stack at all. */
    public boolean appliesTo(ItemStack stack) {
        if (!modItemsOnly) return true;
        ResourceLocation key = ForgeRegistries.ITEMS.getKey(stack.getItem());
        return (key != null && key.getNamespace().equals(SVOCore.MOD_ID)) || stack.is(styledTag);
    }

    public List<String> order() { return order; }
    public Placement placement() { return placement; }
    public String separatorText() { return separatorText; }
    @Nullable public TextColor separatorColor() { return separatorColor; }
    public String detailIndent() { return detailIndent; }
    @Nullable public TextColor detailColor() { return detailColor; }
    public CustomTextSettings customText() { return customText; }
    public Map<String, ValueDefinition> values() { return values; }

    @Nullable
    public TooltipElement valueElement(String id) {
        return valueElements.get(id);
    }
}
