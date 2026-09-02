package com.CuteNekoDragon.Core.common.component;

import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;

public record ToolbeltTooltip(NonNullList<ItemStack> items) implements TooltipComponent {
}
