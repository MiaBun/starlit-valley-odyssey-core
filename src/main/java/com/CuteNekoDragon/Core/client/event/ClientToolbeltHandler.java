package com.CuteNekoDragon.Core.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.CuteNekoDragon.Core.client.screen.ToolbeltRadialScreen;
import com.CuteNekoDragon.Core.common.item.ToolbeltItem;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.Optional;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class ClientToolbeltHandler {

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent e) {
        if (e.phase != TickEvent.Phase.END) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) {
            return;
        }

        if (mc.screen != null) {
            return;
        }

        if (!Screen.hasAltDown()) {
            return;
        }
        @SuppressWarnings({ "removal", "deprecation" })
        Optional<SlotResult> curio = CuriosApi.getCuriosHelper().findFirstCurio(mc.player,
                stack -> stack.getItem() instanceof ToolbeltItem);

        if (curio.isEmpty()) {
            return;
        }

        ItemStack stack = curio.get().stack();
        if (!hasRequiredTags(stack)) {
            return;
        }

        mc.setScreen(new ToolbeltRadialScreen(stack));
    }

    private static boolean hasRequiredTags(ItemStack stack) {
        return stack.hasTag() && stack.getTag().contains(ToolbeltItem.TAG_StorageSize) &&
                stack.getTag().contains(ToolbeltItem.TAG_Slot);
    }
}
