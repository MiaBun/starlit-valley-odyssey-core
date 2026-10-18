package com.CuteNekoDragon.Core.utils.price;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.Set;

public record PriceContext(ItemStack stack, @Nullable Player player, double basePrice, Set<ResourceLocation> categories) {

    public boolean inCategory(ResourceLocation category) {
        return categories.contains(category);
    }
}
