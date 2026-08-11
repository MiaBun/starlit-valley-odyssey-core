package com.CuteNekoDragon.Core.common.item;

import com.CuteNekoDragon.Core.common.component.SackTooltip;
import com.CuteNekoDragon.Core.common.data.items.SVOItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

@SuppressWarnings("deprecation")
public class SackItem extends Item {

    private static final String TAG_ITEMS = "Items";
    public static final int MAX_WEIGHT = 64;
    private static final int BUNDLE_IN_BUNDLE_WEIGHT = 4;
    private static final int BAR_COLOR = Mth.color(0.4F, 0.4F, 1.0F);

    public SackItem(Properties properties) {
        super(properties);
    }

    public static float getFullnessDisplay(ItemStack itemStack) {
        return getContentWeight(itemStack) / 64.0F;
    }


    @Override
    public boolean overrideStackedOnOther(ItemStack stack, Slot slot, ClickAction action, Player player) {
        if (action != ClickAction.SECONDARY) {
            return false;
        } else {
            ItemStack itemStack = slot.getItem();
            if (itemStack.isEmpty()) {
                this.playRemoveOneSound(player);
                removeOne(itemStack).ifPresent(stackItem -> add(stack, slot.safeInsert(stackItem)));
            } else if (itemStack.getItem().canFitInsideContainerItems()) {
                int i = (MAX_WEIGHT - getContentWeight(stack) / getWeight(itemStack));
                int j = add(stack, slot.safeTake(itemStack.getCount(), i, player));
                if (j > 0) {
                    this.playInsertSound(player);
                }
            }
            return true;
        }
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack itemStack, ItemStack itemStack1, Slot slot, ClickAction clickAction, Player player, SlotAccess slotAccess) {
        if (clickAction == ClickAction.SECONDARY && slot.allowModification(player)) {
            if(itemStack1.isEmpty()) {
                removeOne(itemStack).ifPresent(stack -> {
                    this.playRemoveOneSound(player);
                    slotAccess.set(stack);
                });
            } else {
                int i = add(itemStack, itemStack1);
                if (i > 0) {
                    this.playInsertSound(player);
                    itemStack1.shrink(i);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        if (dropContents(itemStack, player)) {
            this.playDropContentsSound(player);
            player.awardStat(Stats.ITEM_USED.get(this));
            return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
        } else {
            return InteractionResultHolder.fail(itemStack);
        }
    }

    @Override
    public boolean isBarVisible(ItemStack itemStack) {
        return getContentWeight(itemStack) > 0;
    }

    @Override
    public int getBarWidth(ItemStack itemStack) {
        return Math.min(1 + 12 * getContentWeight(itemStack) / MAX_WEIGHT, 13);
    }

    @Override
    public int getBarColor(ItemStack itemStack) {
        return BAR_COLOR;
    }

    private static int add(ItemStack itemStack, ItemStack itemStack1) {
        if (!itemStack1.isEmpty() && itemStack1.getItem().canFitInsideContainerItems()) {
            CompoundTag compoundTag = itemStack.getOrCreateTag();
            if (!compoundTag.contains(TAG_ITEMS)) {
                compoundTag.put(TAG_ITEMS, new ListTag());
            }

            int contentWeight = getContentWeight(itemStack);
            int weight = getWeight(itemStack1);
            int count = Math.min(itemStack1.getCount(), (MAX_WEIGHT - contentWeight) / weight);

            if (count == 0) {
                return 0;
            } else {
                ListTag listTag = compoundTag.getList(TAG_ITEMS, 10);
                Optional<CompoundTag> optional = getMatchingItem(itemStack1, listTag);
                if (optional.isPresent()) {
                    CompoundTag compoundTag1 = (CompoundTag) optional.get();
                    ItemStack itemStack2 = ItemStack.of(compoundTag1);
                    itemStack2.grow(count);
                    itemStack2.save(compoundTag1);
                    listTag.remove(compoundTag1);
                    listTag.add(0, compoundTag1);
                } else {
                    ItemStack itemStack2 = itemStack1.copyWithCount(count);
                    CompoundTag compoundTag1 = new CompoundTag();
                    itemStack2.save(compoundTag1);
                    listTag.add(0, compoundTag1);
                }
                return count;
            }
        } else {
            return 0;
        }
    }

    private static Optional<CompoundTag> getMatchingItem(ItemStack itemStack, ListTag listTag) {
        return itemStack.is(SVOItems.SACK.get())
                ? Optional.empty()
                : listTag.stream()
                .filter(CompoundTag.class::isInstance)
                .map(CompoundTag.class::cast)
                .filter(compoundTag -> ItemStack.isSameItemSameTags(ItemStack.of(compoundTag), itemStack))
                .findFirst();
    }

    private static int getWeight(ItemStack itemStack) {
        if (itemStack.is(SVOItems.SACK.get())) {
            return BUNDLE_IN_BUNDLE_WEIGHT + getContentWeight(itemStack);
        } else {
            if ((itemStack.is(Items.BEEHIVE) || itemStack.is(Items.BEE_NEST)) && itemStack.hasTag()) {
                CompoundTag compoundTag = BlockItem.getBlockEntityData(itemStack);
                if (compoundTag != null && !compoundTag.getList("Bees", 10).isEmpty()) {
                    return MAX_WEIGHT;
                }
            }
            return MAX_WEIGHT / itemStack.getMaxStackSize();
        }
    }

    private static int getContentWeight(ItemStack itemStack) {
        return getContent(itemStack).mapToInt(stack -> getWeight(stack) * stack.getCount()).sum();
    }

    private static Optional<ItemStack> removeOne(ItemStack itemStack) {
        CompoundTag compoundTag = itemStack.getOrCreateTag();
        if (!compoundTag.contains(TAG_ITEMS)) {
            return Optional.empty();
        } else {
            ListTag listTag = compoundTag.getList(TAG_ITEMS, 10);
            if(listTag.isEmpty()) {
                return Optional.empty();
            } else {
                int index = 0;
                CompoundTag compoundTag1 = listTag.getCompound(index);
                ItemStack itemStack1 = ItemStack.of(compoundTag1);
                listTag.remove(index);
                if (listTag.isEmpty()) {
                    itemStack.removeTagKey(TAG_ITEMS);
                }

                return Optional.of(itemStack1);
            }
        }
    }

    private static boolean dropContents(ItemStack itemStack, Player player) {
        CompoundTag compoundTag = itemStack.getOrCreateTag();
        if (!compoundTag.contains(TAG_ITEMS)) {
            return false;
        } else {
            if (player instanceof ServerPlayer) {
                ListTag listTag = compoundTag.getList(TAG_ITEMS, 10);

                for (int i = 0; i < listTag.size(); i++) {
                    CompoundTag compoundTag1 = listTag.getCompound(i);
                    ItemStack itemStack1 = ItemStack.of(compoundTag1);
                    player.drop(itemStack1, true);
                }
            }
            itemStack.removeTagKey(TAG_ITEMS);
            return true;
        }
    }

    private static Stream<ItemStack> getContent(ItemStack itemStack) {
        CompoundTag compoundTag = itemStack.getTag();
        if (compoundTag == null) {
            return Stream.empty();
        } else {
            ListTag listTag = compoundTag.getList(TAG_ITEMS, 10);
            return listTag.stream().map(CompoundTag.class::cast).map(ItemStack::of);
        }
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack itemStack) {
        NonNullList<ItemStack> nonNullList = NonNullList.create();
        getContent(itemStack).forEach(nonNullList::add);
        return Optional.of(new SackTooltip(nonNullList, getContentWeight(itemStack)));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        list.add(Component.translatable("item.minecraft.bundle.fullness", getContentWeight(itemStack), MAX_WEIGHT).withStyle(ChatFormatting.GRAY));
    }

    @Override
    public void onDestroyed(ItemEntity itemEntity) {
        ItemUtils.onContainerDestroyed(itemEntity, getContent(itemEntity.getItem()));
    }

    private void playRemoveOneSound(Entity entity) {
        entity.playSound(SoundEvents.BUNDLE_REMOVE_ONE, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }

    private void playInsertSound(Entity entity) {
        entity.playSound(SoundEvents.BUNDLE_INSERT, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }

    private void playDropContentsSound(Entity entity) {
        entity.playSound(SoundEvents.BUNDLE_DROP_CONTENTS, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }
}
