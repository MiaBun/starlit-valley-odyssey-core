package com.CuteNekoDragon.Core.utils.price;

import net.minecraft.resources.ResourceLocation;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Set;

public record PriceEntry(OptionalDouble price, List<ResourceLocation> categories) {

    public static PriceEntry merge(PriceEntry old, PriceEntry neu) {
        OptionalDouble price = neu.price().isPresent() ? neu.price() : old.price();
        Set<ResourceLocation> cats = new LinkedHashSet<>(old.categories());
        cats.addAll(neu.categories());
        return new PriceEntry(price, List.copyOf(cats));
    }
}
