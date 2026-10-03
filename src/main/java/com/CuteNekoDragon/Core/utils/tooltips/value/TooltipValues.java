package com.CuteNekoDragon.Core.utils.tooltips.value;

import com.CuteNekoDragon.Core.SVOCore;
import com.mojang.logging.LogUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;

public final class TooltipValues {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final Set<String> WARNED = ConcurrentHashMap.newKeySet();

    private static final Map<String, ValueSource> DEFAULTS = new ConcurrentHashMap<>();
    private static final Map<String, Map<Item, ValueSource>> ITEM = new ConcurrentHashMap<>();
    private static final Map<String, List<Conditional>> CONDITIONAL = new ConcurrentHashMap<>();

    private record Conditional(Predicate<ItemStack> test, ValueSource source) {}

    private TooltipValues() {}

    public static void setDefault(String type, ValueSource source) {
        DEFAULTS.put(type, source);
    }

    public static void override(String type, ItemLike item, ValueSource source) {
        ITEM.computeIfAbsent(type, k -> new ConcurrentHashMap<>()).put(item.asItem(), source);
    }

    public static void override(String type, TagKey<Item> tag, ValueSource source) {
        override(type, stack -> stack.is(tag), source);
    }

    public static void override(String type, Predicate<ItemStack> test, ValueSource source) {
        CONDITIONAL.computeIfAbsent(type, k -> new CopyOnWriteArrayList<>()).add(new Conditional(test, source));
    }

    public static ValueSource builtin(String type) {
        return stack -> {
            CompoundTag tag = stack.getTag();
            if (tag != null && tag.contains(SVOCore.nbtKey(), Tag.TAG_COMPOUND)) {
                CompoundTag values = tag.getCompound(SVOCore.nbtKey()).getCompound("values");
                if (values.contains(type, Tag.TAG_ANY_NUMERIC)) return TooltipValue.of(values.getDouble(type));
            }
            return DataValues.lookup(type, stack);
        };
    }

    @Nullable
    public static TooltipValue resolve(String type, ItemStack stack) {
        Map<Item, ValueSource> items = ITEM.get(type);
        if (items != null) {
            TooltipValue v = safeGet(type, items.get(stack.getItem()), stack);
            if (v != null) return v;
        }
        List<Conditional> conditionals = CONDITIONAL.get(type);
        if (conditionals != null) {
            for (Conditional c : conditionals) {
                if (!c.test().test(stack)) continue;
                TooltipValue v = safeGet(type, c.source(), stack);
                if (v != null) return v;
            }
        }
        ValueSource def = DEFAULTS.get(type);
        return safeGet(type, def != null ? def : builtin(type), stack);
    }

    @Nullable
    private static TooltipValue safeGet(String type, @Nullable ValueSource source, ItemStack stack) {
        if (source == null) return null;
        try {
            return source.get(stack);
        } catch (RuntimeException e) {
            if (WARNED.add(type)) LOGGER.error("Tooltip value source for '{}' threw", type, e);
            return null;
        }
    }
}
