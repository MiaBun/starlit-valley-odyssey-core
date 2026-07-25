package com.CuteNekoDragon.Core.utils;

import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.OptionalInt;
import java.util.function.Supplier;

public class PriceUtil {

    public enum PriceType {
        GEMOLOGIST
    }

    private record PriceEntry(int price, PriceType type) {}

    private static final Map<Item, PriceEntry> PRICES = new HashMap<>();

    private PriceUtil() {}

    public static void setPrice(Item item, int price) {
        setPrice(item, price, PriceType.GEMOLOGIST);
    }

    public static void setPrice(Item item, int price, PriceType type) {
        PRICES.put(item, new PriceEntry(price, type));
    }

    public static void setPrice(Supplier<? extends Item> itemSupplier, int price) {
        setPrice(itemSupplier.get(), price);
    }

    public static void setPrice(Supplier<? extends Item> itemSupplier, int price, PriceType type) {
        setPrice(itemSupplier.get(), price, type);
    }

    public static int getPrice(Item item) {
        PriceEntry entry = PRICES.get(item);
        return entry == null ? 0 : entry.price();
    }

    public static int getPrice(Supplier<? extends Item> itemSupplier) {
        return getPrice(itemSupplier.get());
    }

    public static OptionalInt getPriceIfPresent(Item item) {
        PriceEntry entry = PRICES.get(item);
        return entry == null ? OptionalInt.empty() : OptionalInt.of(entry.price());
    }

    public static PriceType getPriceType(Item item) {
        PriceEntry entry = PRICES.get(item);
        return entry == null ? null : entry.type();
    }

    public static PriceType getPriceType(Supplier<? extends Item> itemSupplier) {
        return getPriceType(itemSupplier.get());
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
