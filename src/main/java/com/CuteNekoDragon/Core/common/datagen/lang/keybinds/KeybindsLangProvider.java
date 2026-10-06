package com.CuteNekoDragon.Core.common.datagen.lang.keybinds;

import com.CuteNekoDragon.Core.utils.lang.LangEntry;

import static com.CuteNekoDragon.Core.utils.lang.LangUtil.entry;

public final class KeybindsLangProvider {

    public static LangEntry CATEGORY = keybind("category", "Starlit Valley: Odyssey");
    public static LangEntry SKILLTREE_OPEN = keybind("skilltree.open", "Opens the Skills Menu");

    public static LangEntry TOOLTIP_NEXT_PAGE = keybind("tooltip.next_page", "Tooltip: Next Page");
    public static LangEntry TOOLTIP_PREVIOUS_PAGE = keybind("tooltip.prev_page", "Tooltip: Previous Page");

    public static void init() {}

    private static LangEntry keybind(String id, String text) {
        return entry("keybind", id, text);
    }
}
