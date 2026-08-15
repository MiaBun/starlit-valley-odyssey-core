package com.CuteNekoDragon.Core.common.item;

import com.CuteNekoDragon.Core.common.container.LunchboxContainer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.Interaction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class LunchboxItem extends Item {

    private final int slots;
    private final int CooldownLength;

    public LunchboxItem(Properties properties, int slots, int CooldownLength) {
        super(properties);
        this.slots = slots;
        this.CooldownLength = CooldownLength;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (!itemStack.hasTag() || !itemStack.getTag().contains("StorageSize")) {
            itemStack.getOrCreateTag().putInt("StorageSize", slots);
        }
        if (!itemStack.hasTag() || !itemStack.getTag().contains("CooldownLength")) {
            itemStack.getOrCreateTag().putInt("CooldownLength", CooldownLength);
        }

        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            NetworkHooks.openScreen(serverPlayer,
                    new SimpleMenuProvider(
                            (windowId, playerInventory, playerEntity) -> new LunchboxContainer(windowId, playerInventory, itemStack),
                            Component.translatable("item.svo_core." + Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(itemStack.getItem())).getPath())
                    ),
                    buffer -> buffer.writeItem(itemStack)
            );
            return InteractionResultHolder.success(itemStack);
        }
        return InteractionResultHolder.pass(itemStack);
    }

    public static int getStorageSize(ItemStack stack) {
        if(stack.hasTag() && stack.getTag().contains("StorageSize")) {
            return Math.min(9, Math.max(3, stack.getTag().getInt("StorageSize")));
        }
        return 9;
    }
}
