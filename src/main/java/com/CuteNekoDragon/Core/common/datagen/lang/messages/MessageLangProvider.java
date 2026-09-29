package com.CuteNekoDragon.Core.common.datagen.lang.messages;

import com.CuteNekoDragon.Core.utils.lang.LangEntry;

import static com.CuteNekoDragon.Core.utils.lang.LangUtil.entry;

public final class MessageLangProvider {

    public static LangEntry TOOLBELT_INVALID_ITEM = message("toolbelt_invalid_item",
            "Toolbelt Doesn't accept non-durable items.");

    public static void init() {}

    private static LangEntry message(String id, String text) {
        return entry("message", id, text);
    }
}
