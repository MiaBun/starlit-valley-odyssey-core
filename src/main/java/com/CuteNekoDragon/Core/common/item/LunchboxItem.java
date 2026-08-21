package com.CuteNekoDragon.Core.common.item;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

import com.CuteNekoDragon.Core.common.component.LunchboxTooltip;
import com.CuteNekoDragon.Core.common.container.LunchboxContainer;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.Objects;
import java.util.Optional;

public class LunchboxItem extends Item implements ICurioItem {

    private final int slots;
    private final int CooldownLength;
    public static final String TAG_NextCheck = "NextCheckTick";
    public static final String TAG_StorageSize = "StorageSize";
    public static final String TAG_CooldownLength = "CooldownLength";
    public static final String TAG_Items = "Items";

    public LunchboxItem(Properties properties, int slots, int CooldownLength) {
        super(properties);
        this.slots = slots;
        this.CooldownLength = CooldownLength;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (!itemStack.hasTag() || !itemStack.getTag().contains(TAG_StorageSize)) {
            itemStack.getOrCreateTag().putInt(TAG_StorageSize, slots);
        }
        if (!itemStack.hasTag() || !itemStack.getTag().contains(TAG_CooldownLength)) {
            itemStack.getOrCreateTag().putInt(TAG_CooldownLength, CooldownLength);
        }

        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            NetworkHooks.openScreen(serverPlayer,
                    new SimpleMenuProvider(
                            (windowId, playerInventory, playerEntity) -> new LunchboxContainer(windowId,
                                    playerInventory, itemStack),
                            Component.translatable("item.svo_core." + Objects
                                    .requireNonNull(ForgeRegistries.ITEMS.getKey(itemStack.getItem())).getPath())),
                    buffer -> buffer.writeItem(itemStack));
            return InteractionResultHolder.success(itemStack);
        }
        return InteractionResultHolder.pass(itemStack);
    }

    public static int getStorageSize(ItemStack stack) {
        if (stack.hasTag() && stack.getTag().contains(TAG_StorageSize)) {
            return Math.min(9, Math.max(3, stack.getTag().getInt(TAG_StorageSize)));
        }
        return 9;
    }

    public static int getCooldownLength(ItemStack stack) {
        if (stack.hasTag() && stack.getTag().contains(TAG_CooldownLength)) {
            return stack.getTag().getInt(TAG_CooldownLength);
        }
        return 20;
    }

    public boolean hasStoredItems(ItemStack stack) {
        if (!stack.hasTag() || !stack.getTag().contains(TAG_Items, Tag.TAG_LIST)) {
            return false;
        }
        NonNullList<ItemStack> items = NonNullList.withSize(getStorageSize(stack), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(stack.getTag(), items);

        for (ItemStack contained : items) {
            if (!contained.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean isSelected) {
        if (level.isClientSide()) return;

        if (!stack.hasTag() || !stack.getTag().contains(TAG_StorageSize)) return;

        if (!stack.hasTag() || !stack.getTag().contains(TAG_CooldownLength)) return;

        if (!stack.hasTag() || !stack.getTag().contains(TAG_Items)) return;

        if ((entity instanceof Player player)) {
            if (hasStoredItems(stack)) {
                CheckPlayerFeed(stack, level, player);
            }
        }
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return slotContext.identifier().equals("lunchbox");
    }

    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        return true;
    }

    private static void CheckPlayerFeed(ItemStack stack, Level level, Player player) {
        long gameTime = level.getGameTime();
        CompoundTag tag = stack.getOrCreateTag();

        long nextCheck = tag.getLong(TAG_NextCheck);

        if (gameTime < nextCheck) return;

        int intervalTicks;

        if (player.getFoodData().needsFood()) {
            FeedPlayer(stack, level, player);
            intervalTicks = getCooldownLength(stack);
        } else {
            intervalTicks = 100;
        }

        tag.putLong(TAG_NextCheck, gameTime + intervalTicks);
    }

    private static void FeedPlayer(ItemStack stack, Level level, Player player) {
        LunchboxContainer openContainer = LunchboxContainer.getOpenContainerFor(player);
        if (openContainer != null && openContainer.isShowing(stack)) {
            openContainer.feedMostFillingItem(player, level);
            return;
        }

        CompoundTag tag = stack.getOrCreateTag();
        if (!tag.contains(TAG_Items, Tag.TAG_LIST)) return;

        int storageSize = getStorageSize(stack);
        NonNullList<ItemStack> items = NonNullList.withSize(storageSize, ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, items);

        ItemStack bestFood = ItemStack.EMPTY;
        int bestNutrition = -1;

        for (ItemStack candidate : items) {
            if (candidate.isEmpty() || !candidate.isEdible()) continue;

            FoodProperties props = candidate.getFoodProperties(player);
            if (props == null) continue;

            if (props.getNutrition() > bestNutrition) {
                bestNutrition = props.getNutrition();
                bestFood = candidate;
            }
        }
        if (!bestFood.isEmpty()) {
            player.eat(level, bestFood);
        }

        ContainerHelper.saveAllItems(tag, items);
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        if (!hasStoredItems(stack)) {
            return Optional.empty();
        }

        int storageSize = getStorageSize(stack);
        NonNullList<ItemStack> items = NonNullList.withSize(storageSize, ItemStack.EMPTY);
        ContainerHelper.loadAllItems(stack.getTag(), items);

        return Optional.of(new LunchboxTooltip(items));
    }
}
