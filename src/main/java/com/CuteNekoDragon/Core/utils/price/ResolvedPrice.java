package com.CuteNekoDragon.Core.utils.price;

import net.minecraft.resources.ResourceLocation;

import java.util.OptionalDouble;
import java.util.Set;

public record ResolvedPrice(OptionalDouble basePrice, Set<ResourceLocation> categories) {
}
