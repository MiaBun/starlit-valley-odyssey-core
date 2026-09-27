package com.CuteNekoDragon.Core.utils.lang;

import com.CuteNekoDragon.Core.SVOCore;

public class LangUtil {

    public static LangEntry entry(String category, String path, String text) {
        return raw(category + "." + SVOCore.MOD_ID + "." + path, text);
    }

    public static LangEntry raw(String key, String text) {
        SVOCore.REGISTRATE.addRawLang(key, text);
        return new LangEntry(key);
    }
}
