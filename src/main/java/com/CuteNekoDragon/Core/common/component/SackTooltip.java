package com.CuteNekoDragon.Core.common.component;

import lombok.Getter;
import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;

public class SackTooltip implements TooltipComponent {

    @Getter
    private final NonNullList<ItemStack> items;
    @Getter
    private final int weight;

    public SackTooltip(NonNullList<ItemStack> items, int weight) {
        this.items = items;
        this.weight = weight;
    }

}
