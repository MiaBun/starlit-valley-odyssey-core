package com.CuteNekoDragon.Core.utils.tooltips.element;

import com.CuteNekoDragon.Core.utils.tooltips.TooltipStyle;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Nullable;

public final class TooltipElements {

    private static final Map<String, TooltipElement> ELEMENTS = new ConcurrentHashMap<>();

    static {
        register("rarity", new RarityElement());
        register("custom_text", new CustomTextElement());
    }

    private TooltipElements() {}

    public static void register(String id, TooltipElement element) {
        ELEMENTS.put(id, element);
    }

    @Nullable
    public static TooltipElement get(String id, TooltipStyle style) {
        TooltipElement element = ELEMENTS.get(id);
        return element != null ? element : style.valueElement(id);
    }
}
