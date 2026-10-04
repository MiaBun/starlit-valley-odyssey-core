package com.CuteNekoDragon.Core.utils.tooltips.rarity;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

public final class TooltipRarity {
    private final ResourceLocation id;
    private final Component name;
    @Nullable
    private final TextColor color;
    private final boolean recolorName;
    private final boolean showLabel;
    private final int priority;

    public TooltipRarity(ResourceLocation id, Component name, @Nullable TextColor color, boolean recolorName, boolean showLabel, int priority) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.recolorName = recolorName;
        this.showLabel = showLabel;
        this.priority = priority;
    }
}
