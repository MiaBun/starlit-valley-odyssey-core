package com.CuteNekoDragon.Core.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.client.components.ClientSackTooltip;
import com.CuteNekoDragon.Core.common.item.SackItem;
import com.CuteNekoDragon.Core.network.SVONetworkHandler;
import com.CuteNekoDragon.Core.network.packet.SelectSackItemPacket;

@Mod.EventBusSubscriber(modid = SVOCore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class SackScrollHandler {

    @SubscribeEvent
    public static void onScroll(ScreenEvent.MouseScrolled.Pre event) {
        if (ClientSackTooltip.isHoveredThisFrame()) {
            ItemStack sackStack = ClientSackTooltip.getTrackedStack();
            int amountOfShownItems = SackItem.getNumberOfItemsToShow(sackStack);

            if (amountOfShownItems > 0) {
                int slotIndex = findSlotIndex(sackStack);
                if (slotIndex != -1) {
                    int currentSelected = ClientSackTooltip.getSelectedIndex();
                    int newSelected = Mth.clamp(
                            currentSelected - (int) Math.signum(event.getScrollDelta()),
                            0, amountOfShownItems - 1);
                    toggleSelectedItem(sackStack, slotIndex, newSelected);
                }
            }

            ClientSackTooltip.scroll(event.getScrollDelta());
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            ClientSackTooltip.resetHoverFlag();
        }
    }

    private static void toggleSelectedItem(final ItemStack stack, final int slotIndex, final int selectedItem) {
        if (Minecraft.getInstance().getConnection() != null && selectedItem < SackItem.getNumberOfItemsToShow(stack)) {
            SackItem.toggleSelectedItem(stack, selectedItem);
            SVONetworkHandler.INSTANCE.sendToServer(new SelectSackItemPacket(slotIndex, selectedItem));
        }
    }

    private static int findSlotIndex(ItemStack sackStack) {
        if (Minecraft.getInstance().screen instanceof AbstractContainerScreen<?> containerScreen) {
            for (Slot slot : containerScreen.getMenu().slots) {
                if (slot.getItem() == sackStack) {
                    return slot.index;
                }
            }
        }
        return -1;
    }
}
