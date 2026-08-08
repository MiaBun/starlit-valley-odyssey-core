package com.CuteNekoDragon.Core.utils;

import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.ArrayList;
import java.util.List;

public class DurabilityUtil {

    public static void damageItems(List<ItemStack> items, ServerPlayer serverPlayer) {
        for (ItemStack itemStack : items) {
            if (itemStack.isDamageableItem()) {
                int i = itemStack.getEnchantmentLevel(Enchantments.UNBREAKING) + 1;
                int damageAmount = (int) (itemStack.getMaxDamage() * 0.15 + 15);
                damageAmount /= i;
                itemStack.setDamageValue(itemStack.getDamageValue() + damageAmount);
                if (itemStack.getDisplayName() instanceof MutableComponent itemName) {
                    itemStack.hurtAndBreak(0, serverPlayer, (player) -> {
                        if (itemStack.getItem() instanceof ArmorItem armorItem) {
                            player.broadcastBreakEvent(armorItem.getEquipmentSlot());
                        } else {
                            player.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                        }
                    });
                }
            }
        }
    }

    public static List<ItemStack> getHotbarItems(Inventory inventory) {
        List<ItemStack> hotbarItems = new ArrayList<>();
        for (int i = 0; i < inventory.getContainerSize() && i < 9; i++) {
            var item = inventory.getItem(i);
            if(!item.isEmpty()) {
                hotbarItems.add(item);
            }
        }
        if (!inventory.offhand.get(0).isEmpty()) {
            hotbarItems.add(inventory.offhand.get(0));
        }
        return hotbarItems;
    }

    public static List<ItemStack> getArmorItems (Inventory inventory) {
        List<ItemStack> armorItems = new ArrayList<>();
        for (int i = 0; i < inventory.armor.size(); i++) {
            var item = inventory.armor.get(i);
            if(!item.isEmpty())
                armorItems.add(item);
        }
        return  armorItems;
    }
}
