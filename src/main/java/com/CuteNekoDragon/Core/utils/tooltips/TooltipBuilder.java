package com.CuteNekoDragon.Core.utils.tooltips;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.utils.tooltips.element.TooltipElement;
import com.CuteNekoDragon.Core.utils.tooltips.element.TooltipElements;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class TooltipBuilder {

    public static final String SEPARATOR = "separator";
    public static final String SHIFT_HINT = "shift_hint";

    private static final Logger LOGGER = LogUtils.getLogger();
    private static final Set<String> WARNED = ConcurrentHashMap.newKeySet();

    private TooltipBuilder() {}

    public static List<Component> build(TooltipContext ctx) {
        TooltipStyle style = ctx.style();
        List<Object> blocks = new ArrayList<>();
        boolean anyDetails = false;

        for (String id : style.order()) {
            if (id.equals(SEPARATOR) || id.equals(SHIFT_HINT)) {
                blocks.add(id);
                continue;
            }
            TooltipElement element = TooltipElements.get(id, style);
            if (element == null) {
                if (WARNED.add(id)) LOGGER.warn("Tooltip style references unknown element '{}'", id);
                continue;
            }
            try {
                if (!element.isVisible(ctx)) continue;
                List<Component> lines = new ArrayList<>();
                element.appendLines(ctx, lines);
                boolean hasDetails = element.hasDetails(ctx);
                anyDetails |= hasDetails;
                if (hasDetails && ctx.isShiftDown()) {
                    List<Component> details = new ArrayList<>();
                    element.appendDetails(ctx, details);
                    for (Component d : details) lines.add(indent(d, style));
                }
                if (!lines.isEmpty()) blocks.add(lines);
            } catch (RuntimeException e) {
                // A broken element should never crash the game while hovering an item.
                if (WARNED.add("error:" + id)) LOGGER.error("Tooltip element '{}' threw", id, e);
            }

        }

        List<Component> out = new ArrayList<>();
        boolean lastWasSeparator = true; // suppresses leading separators
        for (Object block : blocks) {
            if (SEPARATOR.equals(block)) {
                if (!lastWasSeparator) {
                    out.add(separator(style));
                    lastWasSeparator = true;
                }
            } else if (SHIFT_HINT.equals(block)) {
                if (anyDetails && !ctx.isShiftDown()) {
                    out.add(shiftHint());
                    lastWasSeparator = false;
                }
            } else {
                @SuppressWarnings("unchecked")
                List<Component> lines = (List<Component>) block;
                out.addAll(lines);
                lastWasSeparator = false;
            }
        }
        if (!out.isEmpty() && lastWasSeparator) out.remove(out.size() - 1);
        return out;
    }

    private static Component indent(Component line, TooltipStyle style) {
        MutableComponent c = Component.literal(style.detailIndent()).append(line);
        return style.detailColor() == null ? c : c.withStyle(s -> s.withColor(style.detailColor()));
    }

    private static Component separator(TooltipStyle style) {
        if (style.separatorText().isEmpty()) return Component.empty();
        MutableComponent c = Component.literal(style.separatorText());
        return style.separatorColor() == null ? c : c.withStyle(s -> s.withColor(style.separatorColor()));
    }

    private static Component shiftHint() {
        return Component.translatable(SVOCore.key("hold_shift"),
                Component.translatable(SVOCore.key("shift")).withStyle(ChatFormatting.YELLOW))
                .withStyle(ChatFormatting.DARK_GRAY);
    }
}
