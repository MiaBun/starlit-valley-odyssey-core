package com.CuteNekoDragon.Core.common.component;

import com.CuteNekoDragon.Core.common.item.ToolbeltItem;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.Optional;

public class ToolbeltLogic {

    private ToolbeltLogic() {

    }

    private static Optional<SlotResult> findRequippedToolbelt(ServerPlayer player) {
        return CuriosApi.getCuriosHelper()
                .findFirstCurio(player, stack -> stack.getItem() instanceof ToolbeltItem);
    }

    public static void handleOpen(ServerPlayer player) {
        findRequippedToolbelt(player).ifPresent(result -> {
            ItemStack stack = result.stack();

            if(!(stack.getItem() instanceof  ToolbeltItem toolbeltItem)) {
                return;
            }

            if (!stack.hasTag() || !stack.getTag().contains(ToolbeltItem.TAG_StorageSize))  {
                stack.getOrCreateTag().putInt(ToolbeltItem.TAG_StorageSize, toolbeltItem.getBaseStorageSize());
            }

            if (!stack.hasTag() || !stack.getTag().contains(ToolbeltItem.TAG_Slot)) {
                stack.getOrCreateTag().putInt(ToolbeltItem.TAG_Slot, 1);
            }

            ToolbeltItem.openContainer(player, stack);
        });
    }

    public static void handleRadialSelect(ServerPlayer player, int index) {
        findRequippedToolbelt(player).ifPresent(result -> {
            ItemStack toolbeltStack = result.stack();

            if (!(toolbeltStack.getItem() instanceof ToolbeltItem)) {
                return;
            }

            int storageSize = ToolbeltItem.getStorageSize(toolbeltStack);
            if (index < 0 || index >= storageSize) {
                return;
            }
            NonNullList<ItemStack> items = NonNullList.withSize(storageSize, ItemStack.EMPTY);
            if (toolbeltStack.hasTag() && toolbeltStack.getTag().contains(ToolbeltItem.TAG_Items)) {
                ContainerHelper.loadAllItems(toolbeltStack.getTag(), items);
            }

            ItemStack storedItem = items.get(index);

            int hotbarSlot = player.getInventory().selected;
            ItemStack handItem = player.getInventory().getItem(hotbarSlot);
            if (storedItem.isEmpty() && handItem.isEmpty()) {
                return;
            }
            if (handItem.isEmpty()) {
                player.getInventory().setItem(hotbarSlot, storedItem);
                items.set(index, ItemStack.EMPTY);
            } else if (!handItem.isDamageableItem()) {
                ItemStack toRelocate = handItem.copy();
                player.getInventory().setItem(hotbarSlot, ItemStack.EMPTY);

                boolean added = player.getInventory().add(toRelocate);
                if (!added) {
                    player.getInventory().setItem(hotbarSlot, handItem);
                    player.displayClientMessage(
                            Component.translatable("message.svo_core.toolbelt_inventory_full"), true);
                    return;
                }

                player.getInventory().setItem(hotbarSlot, storedItem);
                items.set(index, ItemStack.EMPTY);
            } else {
                player.getInventory().setItem(hotbarSlot, storedItem);
                items.set(index, handItem);
            }
            ContainerHelper.saveAllItems(toolbeltStack.getOrCreateTag(), items);
            player.getInventory().setChanged();
        });
    }
}
