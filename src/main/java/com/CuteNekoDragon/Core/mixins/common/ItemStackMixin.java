package com.CuteNekoDragon.Core.mixins.common;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(ItemStack.class)
public class ItemStackMixin {

    @Inject(method = "hurt", at = @At(value = "HEAD"), cancellable = true)
    public void starlit$cancelDurabilityUsage(int pAmount, RandomSource pRandom, @Nullable ServerPlayer pUser,
                                              CallbackInfoReturnable<Boolean> cir) {
        var self = (ItemStack) (Object) this;
        if (self.getDamageValue() < self.getMaxDamage()) {
            cir.setReturnValue(false);
        }
    }
}
