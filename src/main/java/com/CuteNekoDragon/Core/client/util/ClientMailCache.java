package com.CuteNekoDragon.Core.client.util;

import com.CuteNekoDragon.Core.utils.mail.Letter;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ClientMailCache {

    private static List<Letter> letters = new ArrayList<>();
    @Getter
    private static boolean toastEnabled = true;
    @Getter
    @Setter
    private static boolean indicatorEnabled = true;

    private ClientMailCache() {}

    public static void update(List<Letter> newLetters, boolean newToastEnabled, boolean newindicatorEnabled) {
        letters = new ArrayList<>(newLetters);
        toastEnabled = newToastEnabled;
        indicatorEnabled = newindicatorEnabled;
    }

    public static List<Letter> getLetters() {
        return Collections.unmodifiableList(letters);
    }

    public static void clear() {
        letters = new ArrayList<>();
        toastEnabled = true;
        indicatorEnabled = true;
    }
}
