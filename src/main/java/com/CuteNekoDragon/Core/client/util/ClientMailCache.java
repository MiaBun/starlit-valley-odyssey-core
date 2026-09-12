package com.CuteNekoDragon.Core.client.util;

import com.CuteNekoDragon.Core.utils.mail.Letter;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ClientMailCache {

    private static List<Letter> letters = new ArrayList<>();
    @Getter
    private static boolean toastEnabled = true;

    private ClientMailCache() {}

    public static void update(List<Letter> newLetters, boolean newToastEnabled) {
        letters = new ArrayList<>(newLetters);
        toastEnabled = newToastEnabled;
    }

    public static List<Letter> getLetters() {
        return Collections.unmodifiableList(letters);
    }

    public static void clear() {
        letters = new ArrayList<>();
        toastEnabled = true;
    }
}
