package com.CuteNekoDragon.Core.common.datagen.lang.tooltips;

import com.CuteNekoDragon.Core.utils.lang.LangEntry;

import static com.CuteNekoDragon.Core.utils.lang.LangUtil.entry;

public final class TooltipLangProvider {

    // Lang Keys

    public static void init() {

    }

    private static LangEntry tip(String id, String text) {
        return entry("tooltip", id, text);
    }
}
