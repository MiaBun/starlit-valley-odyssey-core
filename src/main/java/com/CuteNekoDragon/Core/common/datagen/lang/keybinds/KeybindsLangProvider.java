package com.CuteNekoDragon.Core.common.datagen.lang.keybinds;

import com.CuteNekoDragon.Core.utils.lang.LangEntry;

import static com.CuteNekoDragon.Core.utils.lang.LangUtil.entry;

public final class KeybindsLangProvider {

    // Lang Keys

    public static void init() {

    }

    private static LangEntry keybind(String id, String text) {
        return entry("keybind", id, text);
    }
}
