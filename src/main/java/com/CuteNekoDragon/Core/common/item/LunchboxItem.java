package com.CuteNekoDragon.Core.common.item;

import com.CuteNekoDragon.Core.common.container.LunchboxContainer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Interaction;
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

public class LunchboxItem extends Item implements ICurioItem {

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

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean isSelected) {

        if (level.isClientSide()) return;

        if (!stack.hasTag() || !stack.getTag().contains("StorageSize")) return;

        if (!stack.hasTag() || !stack.getTag().contains("CooldownLength")) return;

        if (!stack.hasTag() || !stack.getTag().contains("Items")) return;

        CheckPlayerFeed(stack, level, entity);
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return slotContext.identifier().equals("lunchbox");
    }

    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        return true;
    }

    private static void CheckPlayerFeed(ItemStack stack, Level level, Entity entity) {
        System.out.println("test");
    }

    //    LivingEntity wearer = slotContext.entity();
    //    Level level = wearer.level();
    //   if (level.isClientSide()) return;

    //     long gameTime = level.getGameTime();
    //     CompoundTag tag = stack.getOrCreateTag();

    //     long nextCheck = tag.getLong(TAG_NEXT_CHECK); // defaults to 0 if absent

    //      if (gameTime < nextCheck) return; // not time yet

    //      doPeriodicEffect(wearer, stack);

    //     int intervalTicks;
    //     if (wearer instanceof Player player) {
    //         boolean hungry = player.getFoodData().getFoodLevel() <= 6; // tweak threshold as you like
    //         intervalTicks = hungry ? 200 : 2000; // 10s vs 100s (20 ticks/sec)
    //     } else {
    //         intervalTicks = 2000; // non-player fallback
    //     }

    //     tag.putLong(TAG_NEXT_CHECK, gameTime + intervalTicks);

}
