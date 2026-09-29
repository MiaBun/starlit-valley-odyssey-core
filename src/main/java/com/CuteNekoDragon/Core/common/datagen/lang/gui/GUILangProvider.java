package com.CuteNekoDragon.Core.common.datagen.lang.gui;

import com.CuteNekoDragon.Core.utils.lang.LangEntry;

import static com.CuteNekoDragon.Core.utils.lang.LangUtil.entry;

public final class GUILangProvider {

    public static LangEntry MAILBOX_NEW_MAIL = mailbox("new_mail", "You've got new mail!");
    public static LangEntry MAILBOX_NOTIFICATIONS = mailbox("toast_setting", "Notifications:");
    public static LangEntry MAILBOX_NOTIFICATIONS_OFF = mailbox("toast_off", "Disabled");
    public static LangEntry MAILBOX_NOTIFICATIONS_ON = mailbox("toast_on", "Enabled");
    public static LangEntry MAILBOX_SELECT_A_LETTER = mailbox("select_a_letter", "Select a Letter");
    public static LangEntry MAILBOX_FROM = mailbox("from", "From: %s");
    public static LangEntry MAILBOX_TITLE = mailbox("title", "Mailbox");
    public static LangEntry MAILBOX_NO_LETTERS = mailbox("no_letters", "No Letters");
    public static LangEntry MAILBOX_INDICATORS = mailbox("indicator_setting", "Indicators:");
    public static LangEntry MAILBOX_INDICATORS_OFF = mailbox("indicator_off", "Disabled");
    public static LangEntry MAILBOX_INDICATORS_ON = mailbox("indicator_on", "Enabled");

    public static LangEntry TOOLBELT_RADIAL = mailbox("toolbelt_radial", "Toolbelt Radial");

    public static void init() {}

    private static LangEntry mailbox(String id, String text) {
        return entry("gui", "mailbox." + id, text);
    }

    private static LangEntry toolbelt(String id, String text) {
        return entry("gui", "toolbelt." + id, text);
    }

    private static LangEntry gui(String id, String text) {
        return entry("gui", id, text);
    }
}
