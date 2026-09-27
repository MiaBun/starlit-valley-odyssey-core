package com.CuteNekoDragon.Core.common.datagen.lang.advancements;

import com.CuteNekoDragon.Core.utils.lang.AdvancementLang;

import static com.CuteNekoDragon.Core.utils.lang.LangUtil.entry;

public final class AdvancementLangProvider {

    // Lang Keys

    public static void init() {

    }

    private static AdvancementLang chapter1(String id, String title, String desc) {
        String path = id.isEmpty() ? "chapter1" : "chapter1." + id;
        return new AdvancementLang(
                entry("advancement", path + ".title", title),
                entry("advancement", path + ".description", desc));
    }
}
