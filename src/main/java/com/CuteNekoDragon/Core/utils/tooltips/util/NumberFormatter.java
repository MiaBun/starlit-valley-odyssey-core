package com.CuteNekoDragon.Core.utils.tooltips.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public final class NumberFormatter {

    private static final String[] SUFFIXES = {"", "k", "M", "G", "T", "P", "E"};

    private NumberFormatter() {}

    public enum Style {
        /** 1,234,567.89 */
        FULL,
        /** 1.2M */
        COMPACT,
        /** 1234567.89 */
        PLAIN;

        public static Style parse(String s) {
            return switch (s.toLowerCase(Locale.ROOT)) {
                case "compact", "short" -> COMPACT;
                case "plain", "raw" -> PLAIN;
                default -> FULL;
            };
        }
    }

    public static String format(double value, Style style, int decimals) {
        return switch (style) {
            case FULL -> pattern(true, decimals).format(value);
            case PLAIN -> pattern(false, decimals).format(value);
            case COMPACT -> compact(value, decimals);
        };
    }

    private static String compact(double value, int decimals) {
        double abs = Math.abs(value);
        if (abs < 1000) return pattern(false, decimals).format(value);
        int tier = Math.min((int) (Math.log10(abs) / 3), SUFFIXES.length - 1);
        double scaled = value / Math.pow(1000, tier);
        // 999.95k would round to "1000k"; bump to the next tier instead.
        if (Math.abs(scaled) >= 999.95 && tier < SUFFIXES.length - 1) {
            tier++;
            scaled = value / Math.pow(1000, tier);
        }
        return pattern(false, Math.max(decimals, 1)).format(scaled) + SUFFIXES[tier];
    }

    private static DecimalFormat pattern(boolean grouping, int decimals) {
        StringBuilder p = new StringBuilder(grouping ? "#,##0" : "0");
        if (decimals > 0) p.append('.').append("#".repeat(decimals));
        DecimalFormat f = new DecimalFormat(p.toString(), DecimalFormatSymbols.getInstance(Locale.ROOT));
        f.setRoundingMode(RoundingMode.HALF_UP);
        return f;
    }
}
