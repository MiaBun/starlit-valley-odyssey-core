package com.CuteNekoDragon.Core.common.datagen.lang.gui;

import com.CuteNekoDragon.Core.utils.lang.LangEntry;

import static com.CuteNekoDragon.Core.utils.lang.LangUtil.entry;

public final class GUILangProvider {

    // Lang Keys

    public static void init() {

    }

    private static LangEntry gui(String id, String text) {
        return entry("gui", id, text);
    }
}
