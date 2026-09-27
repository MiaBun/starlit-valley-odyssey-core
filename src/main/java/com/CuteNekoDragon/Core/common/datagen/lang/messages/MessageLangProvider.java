package com.CuteNekoDragon.Core.common.datagen.lang.messages;

import com.CuteNekoDragon.Core.utils.lang.LangEntry;

import static com.CuteNekoDragon.Core.utils.lang.LangUtil.entry;

public final class MessageLangProvider {

    // Lang Keys

    public static void init() {

    }

    private static LangEntry message(String id, String text) {
        return entry("message", id, text);
    }
}
