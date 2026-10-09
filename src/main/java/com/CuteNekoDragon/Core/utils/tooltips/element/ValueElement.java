package com.CuteNekoDragon.Core.utils.tooltips.element;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.ItemStack;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.utils.tooltips.TooltipContext;
import com.CuteNekoDragon.Core.utils.tooltips.glyph.Glyph;
import com.CuteNekoDragon.Core.utils.tooltips.value.TooltipValue;
import com.CuteNekoDragon.Core.utils.tooltips.value.TooltipValues;
import com.CuteNekoDragon.Core.utils.tooltips.value.ValueDefinition;

import java.util.List;

import javax.annotation.Nullable;

public final class ValueElement implements TooltipElement {

    private final ValueDefinition def;

    public ValueElement(ValueDefinition def) {
        this.def = def;
    }

    public ValueDefinition definition() {
        return def;
    }

    @Nullable
    private TooltipValue value(TooltipContext ctx) {
        return ctx.cached("value:" + def.id(), () -> TooltipValues.resolve(def.id(), ctx.stack()));
    }

    @Override
    public boolean isVisible(TooltipContext ctx) {
        if (def.requireTag() && !ctx.stack().is(def.tag())) return false;
        TooltipValue v = value(ctx);
        return v != null && !(def.hideZero() && v.amount() == 0);
    }

    @Override
    public void appendLines(TooltipContext ctx, List<Component> out) {
        TooltipValue v = value(ctx);
        if (v == null) return;
        MutableComponent line = Component.empty();
        Glyph glyph = def.glyph() == null ? null : def.glyph().resolve();
        if (glyph != null && !def.glyphAtEnd()) {
            line.append(glyph.toComponent(def.iconColor())).append(" ");
        } else if (glyph == null && !def.icon().isEmpty()) {
            line.append(colored(Component.literal(def.icon() + " "), def.iconColor()));
        }
        line.append(colored(Component.translatableWithFallback(def.labelKey(), def.labelFallback()), def.labelColor()));
        line.append(colored(Component.literal(": "), def.labelColor()));
        line.append(colored(Component.literal(def.format(v.amount())), def.valueColor()));
        if (glyph != null && def.glyphAtEnd()) line.append(" ").append(glyph.toComponent(def.iconColor()));
        out.add(line);
    }

    @Override
    public boolean hasDetails(TooltipContext ctx) {
        TooltipValue v = value(ctx);
        return v != null && (!v.details().isEmpty() || showStackTotal(ctx.stack()));
    }

    @Override
    public void appendDetails(TooltipContext ctx, List<Component> out) {
        TooltipValue v = value(ctx);
        if (v == null) return;
        for (TooltipValue.DetailLine d : v.details()) {
            if (d.value() == null) {
                out.add(d.text());
            } else {
                out.add(Component.empty().append(d.text()).append(": ")
                        .append(colored(Component.literal(def.format(d.value())), def.valueColor())));
            }
        }
        ItemStack stack = ctx.stack();
        if (showStackTotal(stack)) {
            out.add(Component.translatable(SVOCore.key("stack_total"), stack.getCount(),
                    colored(Component.literal(def.format(v.amount() * stack.getCount())), def.valueColor())));
        }
    }

    private boolean showStackTotal(ItemStack stack) {
        return def.showStackTotal() && stack.getCount() > 1;
    }

    private static MutableComponent colored(MutableComponent c, @Nullable TextColor color) {
        return color == null ? c : c.withStyle(s -> s.withColor(color));
    }
}
