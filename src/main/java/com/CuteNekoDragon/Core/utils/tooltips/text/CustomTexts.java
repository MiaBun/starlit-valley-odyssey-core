package com.CuteNekoDragon.Core.utils.tooltips.text;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import com.CuteNekoDragon.Core.utils.tooltips.TooltipStyle;
import com.CuteNekoDragon.Core.utils.tooltips.util.TextUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;

public final class CustomTexts {

    public static final String PAGE_BREAK = "[page]";

    @FunctionalInterface
    public interface Provider {

        List<Component> lines(ItemStack stack);
    }

    private record Conditional(Predicate<ItemStack> test, Provider provider) {}

    private static final Map<Item, Provider> ITEM = new ConcurrentHashMap<>();
    private static final Map<Item, Provider> ITEM_DETAILS = new ConcurrentHashMap<>();
    private static final List<Conditional> CONDITIONAL = new CopyOnWriteArrayList<>();
    private static final List<Conditional> CONDITIONAL_DETAILS = new CopyOnWriteArrayList<>();

    private CustomTexts() {}

    public static void register(ItemLike item, Provider provider) {
        ITEM.put(item.asItem(), provider);
    }

    public static void register(Predicate<ItemStack> test, Provider provider) {
        CONDITIONAL.add(new Conditional(test, provider));
    }

    public static void registerDetails(ItemLike item, Provider provider) {
        ITEM_DETAILS.put(item.asItem(), provider);
    }

    public static Component pageBreak() {
        return Component.literal(PAGE_BREAK);
    }

    public static List<Component> lines(ItemStack stack, boolean details) {
        Provider p = (details ? ITEM_DETAILS : ITEM).get(stack.getItem());
        if (p != null) {
            List<Component> lines = p.lines(stack);
            if (!lines.isEmpty()) return lines;
        }
        for (Conditional c : details ? CONDITIONAL_DETAILS : CONDITIONAL) {
            if (!c.test().test(stack)) continue;
            List<Component> lines = c.provider().lines(stack);
            if (!lines.isEmpty()) return lines;
        }
        String key = stack.getDescriptionId() + (details ? ".tooltip.shift" : ".tooltip");
        if (!TextUtil.hasTranslation(key)) return List.of();
        List<Component> out = new ArrayList<>();
        for (String line : TextUtil.splitLinesWithFormatting(TextUtil.translate(key))) {
            out.add(TextUtil.parseLegacy(line));
        }
        return out;
    }

    public static List<List<Component>> paginate(List<Component> lines, TooltipStyle.CustomTextSettings settings) {
        List<List<Component>> pages = new ArrayList<>();
        List<Component> currentPage = new ArrayList<>();
        int perPage = settings.linesPerPage();
        for (Component line : lines) {
            if (line.getString().strip().equals(PAGE_BREAK)) {
                if (!currentPage.isEmpty()) {
                    pages.add(currentPage);
                    currentPage = new ArrayList<>();
                }
                continue;
            }
            for (Component wrapped : TextUtil.wrap(line, settings.maxWidth())) {
                if (perPage > 0 && currentPage.size() >= perPage) {
                    pages.add(currentPage);
                    currentPage = new ArrayList<>();
                }
                currentPage.add(tint(wrapped, settings.color()));
            }
        }
        if (!currentPage.isEmpty()) pages.add(currentPage);
        return pages;
    }

    private static Component tint(Component line, TextColor color) {
        if (color == null) return line;
        MutableComponent base = Component.empty().withStyle(s -> s.withColor(color));
        return base.append(line);
    }
}
