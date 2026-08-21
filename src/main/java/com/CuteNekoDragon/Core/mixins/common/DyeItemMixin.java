package com.CuteNekoDragon.Core.mixins.common;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Map;

@Mixin(DyeItem.class)
public abstract class DyeItemMixin {

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"))
    private Object svo$firstRegisteredWins(Map<DyeColor, DyeItem> map, Object color, Object item) {
        return map.putIfAbsent((DyeColor) color, (DyeItem) item);
    }
}
