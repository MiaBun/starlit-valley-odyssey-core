package com.CuteNekoDragon.Core.utils.price;

import net.minecraft.resources.ResourceLocation;

import java.util.OptionalDouble;

public record PriceCategory(ResourceLocation id, OptionalDouble basePrice) {
}
