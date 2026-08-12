package com.CuteNekoDragon.Core.common.component;

import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;

import lombok.Getter;

public class SackTooltip implements TooltipComponent {

    @Getter
    private final ItemStack containerStack;
    @Getter
    private final NonNullList<ItemStack> items;
    @Getter
    private final int weight;

    public SackTooltip(ItemStack containerStack, NonNullList<ItemStack> items, int weight) {
        this.containerStack = containerStack;
        this.items = items;
        this.weight = weight;
    }
}
