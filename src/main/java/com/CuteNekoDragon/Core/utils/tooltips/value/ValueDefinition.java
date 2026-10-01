package com.CuteNekoDragon.Core.utils.tooltips.value;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public record ValueDefinition(
        String id,
        TagKey<Item> tag,
        boolean requireTag,
        String labelKey,
        String labelFallback,
        String icon
) {
}
