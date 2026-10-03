package com.CuteNekoDragon.Core.utils.tooltips;

import lombok.Getter;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public final class TooltipContext {

    private final ItemStack stack;
    private final TooltipFlag flag;
    @Nullable private final Player player;
    private final TooltipStyle style;
    @Getter
    private final boolean shiftDown;
    private final Map<Object, Optional<?>> cache = new HashMap<>();

    public TooltipContext(ItemStack stack, TooltipFlag flag, @Nullable Player player, TooltipStyle style, boolean shiftDown) {
        this.stack = stack;
        this.flag = flag;
        this.player = player;
        this.style = style;
        this.shiftDown = shiftDown;
    }

    public ItemStack stack() { return stack; }
    public TooltipFlag flag() { return flag; }
    @Nullable public Player player() { return player; }
    public TooltipStyle style() { return style; }

    /** Computes a value once per tooltip build (so isVisible/appendLines/hasDetails don't repeat work). */
    @Nullable
    @SuppressWarnings("unchecked")
    public <T> T cached(Object key, Supplier<T> supplier) {
        return (T) cache.computeIfAbsent(key, k -> Optional.ofNullable(supplier.get())).orElse(null);
    }
}
