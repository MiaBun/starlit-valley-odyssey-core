package com.CuteNekoDragon.Core.utils.mail;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class MailMarkdown {

    private static final Pattern INLINE_TOKEN = Pattern.compile("(\\*\\*.+?\\*\\*|\\*.+?\\*)");

    private MailMarkdown() {}

    public static Component parseLine(String rawLine) {
        if (rawLine.isBlank()) {
            return Component.empty();
        }
        if (rawLine.startsWith("### ")) {
            return Component.literal(rawLine.substring(4)).withStyle(ChatFormatting.BOLD);
        }
        if (rawLine.startsWith("## ")) {
            return Component.literal(rawLine.substring(3)).withStyle(ChatFormatting.BOLD, ChatFormatting.UNDERLINE);
        }
        if (rawLine.startsWith("# ")) {
            return Component.literal(rawLine.substring(2))
                    .withStyle(ChatFormatting.BOLD, ChatFormatting.UNDERLINE, ChatFormatting.GOLD);
        }
        if (rawLine.startsWith("> ")) {
            return Component.literal(rawLine.substring(2)).withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY);
        }
        if (rawLine.startsWith("- ") || rawLine.startsWith("* ")) {
            return Component.literal("\u2022 ").append(parseInline(rawLine.substring(2)));
        }

        return parseInline(rawLine);
    }

    private static MutableComponent parseInline(String text) {
        MutableComponent result = Component.empty();
        Matcher matcher = INLINE_TOKEN.matcher(text);
        int lastEnd = 0;

        while (matcher.find()) {
            if (matcher.start() > lastEnd) {
                result.append(Component.literal(text.substring(lastEnd, matcher.start())));
            }
            String token = matcher.group(1);
            if (token.startsWith("**")) {
                result.append(Component.literal(token.substring(2, token.length() - 2))
                        .withStyle(ChatFormatting.BOLD));
            } else {
                result.append(Component.literal(token.substring(1, token.length() - 1))
                        .withStyle(ChatFormatting.ITALIC));
            }
            lastEnd = matcher.end();
        }
        if (lastEnd < text.length()) {
            result.append(Component.literal(text.substring(lastEnd)));
        }
        return result;
    }
}
