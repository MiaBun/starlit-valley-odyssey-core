package com.CuteNekoDragon.Core.common.event;

import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.common.recipe.serializers.AbstractSackUpgradeRecipe;

@Mod.EventBusSubscriber(modid = SVOCore.MOD_ID)
public class SackUpgradeOverflowEvent {

    @SubscribeEvent
    public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
        ItemStack crafted = event.getCrafting();
        if (crafted.isEmpty() || !crafted.hasTag() ||
                !crafted.getTag().contains(AbstractSackUpgradeRecipe.TAG_OVERFLOW, Tag.TAG_LIST)) {
            return;
        }

        Player player = event.getEntity();
        if (player.level().isClientSide()) {
            return;
        }

        ListTag overflow = crafted.getTag().getList(AbstractSackUpgradeRecipe.TAG_OVERFLOW, Tag.TAG_COMPOUND);
        for (int i = 0; i < overflow.size(); i++) {
            ItemStack stack = ItemStack.of(overflow.getCompound(i));
            if (stack.isEmpty()) {
                continue;
            }
            if (!player.getInventory().add(stack)) {
                player.drop(stack, false);
            }
        }

        crafted.getTag().remove(AbstractSackUpgradeRecipe.TAG_OVERFLOW);
        if (crafted.getTag().isEmpty()) {
            crafted.setTag(null);
        }
    }
}
