package com.CuteNekoDragon.Core.utils.tooltips.element;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.utils.tooltips.TooltipContext;
import com.CuteNekoDragon.Core.utils.tooltips.TooltipKeys;
import com.CuteNekoDragon.Core.utils.tooltips.TooltipStyle;
import com.CuteNekoDragon.Core.utils.tooltips.text.CustomTexts;
import com.CuteNekoDragon.Core.utils.tooltips.text.TooltipPager;

import java.util.List;

public final class CustomTextElement implements TooltipElement {

    private List<List<Component>> pages(TooltipContext ctx) {
        List<List<Component>> pages = ctx.cached("custom_text:pages", () -> {
            TooltipStyle.CustomTextSettings s = ctx.style().customText();
            return CustomTexts.paginate(CustomTexts.lines(ctx.stack(), false), s);
        });
        return pages == null ? List.of() : pages;
    }

    private List<Component> detailLines(TooltipContext ctx) {
        List<Component> lines = ctx.cached("custom_text:details", () -> {
            TooltipStyle.CustomTextSettings s = ctx.style().customText();
            return CustomTexts.paginate(CustomTexts.lines(ctx.stack(), true), s).stream().flatMap(List::stream)
                    .toList();
        });
        return lines == null ? List.of() : lines;
    }

    @Override
    public boolean isVisible(TooltipContext ctx) {
        return !pages(ctx).isEmpty() || !detailLines(ctx).isEmpty();
    }

    @Override
    public void appendLines(TooltipContext ctx, List<Component> out) {
        List<List<Component>> pages = pages(ctx);
        if (pages.isEmpty()) return;
        TooltipStyle.CustomTextSettings s = ctx.style().customText();
        int page = TooltipPager.resolvePage(ctx.stack(), pages.size(), s.autoPageSeconds());
        out.addAll(pages.get(page));
        if (pages.size() > 1 && s.pageIndicator()) out.add(pageIndicator(page, pages.size()));
    }

    @Override
    public boolean hasDetails(TooltipContext ctx) {
        return !detailLines(ctx).isEmpty();
    }

    @Override
    public void appendDetails(TooltipContext ctx, List<Component> out) {
        out.addAll(detailLines(ctx));
    }

    private static Component pageIndicator(int page, int count) {
        Component prev = TooltipKeys.PREV_PAGE != null ? TooltipKeys.PREV_PAGE.getTranslatedKeyMessage() :
                Component.literal("[");
        Component next = TooltipKeys.NEXT_PAGE != null ? TooltipKeys.NEXT_PAGE.getTranslatedKeyMessage() :
                Component.literal("]");
        return Component.translatable(SVOCore.key("page"), page + 1, count,
                prev.copy().withStyle(ChatFormatting.GRAY), next.copy().withStyle(ChatFormatting.GRAY))
                .withStyle(ChatFormatting.DARK_GRAY);
    }
}
