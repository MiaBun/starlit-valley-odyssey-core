package com.CuteNekoDragon.Core.utils.tooltips.util;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class TextUtil {

    private TextUtil() {}

    public static Component keyOrLiteral(String s) {
        return Component.translatableWithFallback(s, s);
    }

    public static List<String> splitLinesWithFormatting(String text) {
        List<String> out = new ArrayList<>();
        String carry = "";
        for (String part : text.split("\n", -1)) {
            String line = carry + part;
            out.add(line);
            carry = activeFormatting(line);
        }
        return out;
    }

    private static String activeFormatting(String s) {
        String color = "";
        StringBuilder formats = new StringBuilder();
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) != '§') continue;
            ChatFormatting f = ChatFormatting.getByCode(s.charAt(i + 1));
            if (f == null) continue;
            if (f == ChatFormatting.RESET) {
                color = "";
                formats.setLength(0);
            } else if (f.isColor()) {
                color = "§" + f.getChar();
                formats.setLength(0); // a colour code resets formats in vanilla
            } else {
                formats.append('§').append(f.getChar());
            }
            i++;
        }
        return color + formats;
    }

    public static MutableComponent parseLegacy(String s) {
        MutableComponent out = Component.empty();
        Style style = Style.EMPTY;
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '§' && i + 1 < s.length()) {
                ChatFormatting f = ChatFormatting.getByCode(s.charAt(i + 1));
                if (f != null) {
                    if (!buf.isEmpty()) {
                        out.append(Component.literal(buf.toString()).setStyle(style));
                        buf.setLength(0);
                    }
                    style = f == ChatFormatting.RESET ? Style.EMPTY : style.applyLegacyFormat(f);
                    i++;
                    continue;
                }
            }
            buf.append(c);
        }
        if (!buf.isEmpty()) out.append(Component.literal(buf.toString()).setStyle(style));
        return out;
    }

    public static List<Component> wrap(Component line, int maxWidth) {
        if (maxWidth <= 0) return List.of(line);
        Minecraft mc = Minecraft.getInstance();
        if (mc.font == null) return List.of(line);
        List<FormattedText> parts = mc.font.getSplitter().splitLines(line, maxWidth, Style.EMPTY);
        if (parts.isEmpty()) return List.of(Component.empty());
        List<Component> out = new ArrayList<>(parts.size());
        for (FormattedText part : parts) out.add(toComponent(part));
        return out;
    }

    public static Component toComponent(FormattedText text) {
        if (text instanceof Component c) return c;
        MutableComponent out = Component.empty();
        text.visit((style, content) -> {
            out.append(Component.literal(content).setStyle(style));
            return Optional.empty();
        }, Style.EMPTY);
        return out;
    }

    public static boolean hasTranslation(String key) {
        return Language.getInstance().has(key);
    }

    public static String translate(String key) {
        return Language.getInstance().getOrDefault(key);
    }

    public static String titleCase(String id) {
        StringBuilder sb = new StringBuilder();
        for (String word : id.split("[_\\-]")) {
            if (word.isEmpty()) continue;
            if (!sb.isEmpty()) sb.append(' ');
            sb.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1));
        }
        return sb.toString();
    }
}
