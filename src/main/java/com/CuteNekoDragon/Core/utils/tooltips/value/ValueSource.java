package com.CuteNekoDragon.Core.utils.tooltips.value;

import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

@FunctionalInterface
public interface ValueSource {
    @Nullable
    TooltipValue get(ItemStack stack);

    /** Use {@code other} when this source has nothing. */
    default ValueSource orElse(ValueSource other) {
        return stack -> {
            TooltipValue v = get(stack);
            return v != null ? v : other.get(stack);
        };
    }

    static ValueSource constant(double amount) {
        TooltipValue v = TooltipValue.of(amount);
        return stack -> v;
    }

    static ValueSource ofAmount(java.util.function.ToDoubleFunction<ItemStack> fn) {
        return stack -> {
            double d = fn.applyAsDouble(stack);
            return Double.isNaN(d) ? null : TooltipValue.of(d);
        };
    }
}
