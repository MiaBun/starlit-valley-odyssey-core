package com.CuteNekoDragon.Core.utils.tooltips.element;

import com.CuteNekoDragon.Core.utils.tooltips.TooltipContext;
import net.minecraft.network.chat.Component;

import java.util.List;

public interface TooltipElement {

    boolean isVisible(TooltipContext ctx);

    void appendLines(TooltipContext ctx, List<Component> out);

    default boolean hasDetails(TooltipContext ctx) {
        return false;
    }

    default void appendDetails(TooltipContext ctx, List<Component> out) {}
}
