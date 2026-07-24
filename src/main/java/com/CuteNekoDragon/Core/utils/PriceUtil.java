package com.CuteNekoDragon.Core.utils;

import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.OptionalInt;
import java.util.function.Supplier;

public class PriceUtil {

    private static final Map<Item, Integer> PRICES = new HashMap<>();

    private PriceUtil() {}

    public static void setPrice(Item item, int price) {
        PRICES.put(item, price);
    }

    public static void setPrice(Supplier<? extends Item> itemSupplier, int price) {
        setPrice(itemSupplier.get(), price);
    }

    public static int getPrice(Item item) {
        return PRICES.getOrDefault(item, 0);
    }

    public static int getPrice(Supplier<? extends Item> itemSupplier) {
        return getPrice(itemSupplier.get());
    }

    public static OptionalInt getPriceIfPresent(Item item) {
        Integer price = PRICES.get(item);
        return price == null ? OptionalInt.empty() : OptionalInt.of(price);
    }

    public static boolean hasPrice(Item item) {
        return PRICES.containsKey(item);
    }

    public static boolean hasPrice(Supplier<? extends Item> itemSupplier) {
        return hasPrice(itemSupplier.get());
    }

    public static void removalPrice(Item item) {
        PRICES.remove(item);
    }
}
