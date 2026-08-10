package com.CuteNekoDragon.Core.common.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BundleItem extends Item {

    public static final int MAX_SHOWN_GRID_ITEMS_X = 4;
    public static final int MAX_SHOWN_GRID_ITEMS_Y = 3;
    public static final int MAX_SHOWN_GRID_ITEMS = 12;
    public static final int OVERFLOWING_MAX_SHOWN_GRID_ITEMS = 11;

    private static final int FULL_BAR_COLOR = 0xFFFF5555;
    private static final int BAR_COLOR = 0xFF7087DD;
    private static final int TICKS_AFTER_FIRST_THROW = 10;
    private static final int TICKS_BETWEEN_THROWS = 2;
    private static final int TICKS_MAX_THROW_DURATION = 200;

    public BundleItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean overrideStackedOnOther(ItemStack self, Slot slot, ClickAction clickAction, Player player) {
        if (self.getCount() != 1) return false;

        ItemStack other = slot.getItem();

        if (clickAction == ClickAction.PRIMARY && !other.isEmpty()) {
            int inserted = BundleContents.tryTransfer(self, slot, player);
            if (inserted > 0) {
                playInsertSound(player);
            } else {
                playInsertFailSound(player);
            }
            broadcastChanges(player);
            return true;
        } else if (clickAction == ClickAction.SECONDARY && other.isEmpty()) {
            ItemStack removed = BundleContents.removeOne(self);
            if (removed != null) {
                ItemStack remainder = slot.safeInsert(removed);
                if (remainder.getCount() > 0) {
                    BundleContents.tryInsert(self, remainder);
                } else {
                    playRemoveOneSound(player);
                }
            }
            broadcastChanges(player);
            return true;
        }

        return false;
    }

    private static void playRemoveOneSound(Entity entity) {
        entity.playSound(SoundEvents.BUNDLE_REMOVE_ONE, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }
    private static void playInsertSound(Entity entity) {
        entity.playSound(SoundEvents.BUNDLE_INSERT, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }
    private static void playInsertFailSound(Entity entity) {
        entity.playSound(SoundEvents.ITEM_BREAK, 0.8F, 0.8F);
    }
    private static void playDropContentsSound(Level level, Entity entity) {
        level.playSound(null, entity.blockPosition(), SoundEvents.BUNDLE_REMOVE_ONE, SoundSource.PLAYERS,
                0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }
    private void broadcastChanges(Player player) {
        AbstractContainerMenu menu = player.containerMenu;
        if (menu != null) {
            menu.slotsChanged(player.getInventory());
        }
    }
}
