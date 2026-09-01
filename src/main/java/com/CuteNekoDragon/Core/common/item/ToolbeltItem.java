package com.CuteNekoDragon.Core.common.item;

import com.CuteNekoDragon.Core.common.container.LunchboxContainer;
import com.CuteNekoDragon.Core.common.container.ToolbeltContainer;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.Objects;

public class ToolbeltItem extends Item implements ICurioItem {

    private final int StorageSize;
    public static final String TAG_StorageSize = "StorageSize";
    public static final String TAG_Items = "Items";
    public static final String TAG_Slot = "Slot";

    public ToolbeltItem(Properties properties, int slots) {
        super(properties);
        this.StorageSize = slots;
    }

    @Override
    public InteractionResultHolder<ItemStack> use (Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (!itemStack.hasTag() || !itemStack.getTag().contains(TAG_StorageSize)) {
            itemStack.getOrCreateTag().putInt(TAG_StorageSize, StorageSize);
        }

        if (!itemStack.hasTag() || !itemStack.getTag().contains(TAG_Slot)) {
            itemStack.getOrCreateTag().putInt(TAG_Slot, 1);
        }

        if(!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            NetworkHooks.openScreen(serverPlayer,
                    new SimpleMenuProvider(
                            (windowId, playerInventory, playerEntity) -> new ToolbeltContainer(windowId,
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

    public static int getSelectedSlot(ItemStack stack) {
        if (stack.hasTag() && stack.getTag().contains(TAG_Slot)) {
            return Math.min(9, Math.max(1, stack.getTag().getInt(TAG_Slot)));
        }
        return 1;
    }

    public boolean hasStoredItems(ItemStack stack) {
        if (!stack.hasTag() || !stack.getTag().contains(TAG_Items, Tag.TAG_LIST)) {
            return false;
        }
        NonNullList<ItemStack> items = NonNullList.withSize(getStorageSize(stack), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(stack.getTag(), items);

        for (ItemStack conatained : items) {
            if (!conatained.isEmpty()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return slotContext.identifier().equals("toolbelt");
    }

    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        return true;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {

        LivingEntity entity = slotContext.entity();
        Level level = entity.level();

        if (level.isClientSide) return;

        if (!stack.hasTag() || !stack.getTag().contains(TAG_StorageSize)) return;

        if (!stack.hasTag() || !stack.getTag().contains(TAG_Slot)) return;

        if (!stack.hasTag() || !stack.getTag().contains(TAG_Items)) return;
    }
}
