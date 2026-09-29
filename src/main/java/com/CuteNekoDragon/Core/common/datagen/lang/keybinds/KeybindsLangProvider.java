package com.CuteNekoDragon.Core.common.datagen.lang.keybinds;

import com.CuteNekoDragon.Core.utils.lang.LangEntry;

import static com.CuteNekoDragon.Core.utils.lang.LangUtil.entry;

public final class KeybindsLangProvider {

    public static LangEntry CATEGORY = keybind("category", "Starlit Valley: Odyssey");
    public static LangEntry SKILLTREE_OPEN = keybind("skilltree.open", "Opens the Skills Menu");

    public static void init() {

    }

    private static LangEntry keybind(String id, String text) {
        return entry("keybind", id, text);
    }
}
