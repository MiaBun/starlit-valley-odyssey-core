package com.CuteNekoDragon.Core.utils.tooltips;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.utils.tooltips.rarity.TooltipRarities;
import com.CuteNekoDragon.Core.utils.tooltips.rarity.TooltipRarity;
import com.CuteNekoDragon.Core.utils.tooltips.render.TooltipRenderer;
import com.CuteNekoDragon.Core.utils.tooltips.text.TooltipPager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = SVOCore.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class TooltipEvents {

    private TooltipEvents() {}

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack.isEmpty()) return;
        TooltipStyle style = TooltipStyle.current();
        if(!style.appliesTo(stack)) return;

        List<Component> tooltip = event.getToolTip();

        TooltipRarity rarity = TooltipRarities.get(stack);
        if (rarity != null && rarity.recolorName() && rarity.color() != null && !tooltip.isEmpty()) {
            TextColor color = rarity.color();
            tooltip.set(0, tooltip.get(0).copy().withStyle(s -> s.withColor(color)));
        }

        boolean shift = RenderSystem.isOnRenderThread() && Screen.hasShiftDown();
        TooltipContext ctx = new TooltipContext(stack, event.getFlags(), event.getEntity(), style, shift);
        List<Component> lines = TooltipBuilder.build(ctx);
        if (lines.isEmpty()) return;

        int at = style.placement() == TooltipStyle.Placement.AFTER_NAME ? Math.min(1, tooltip.size()) : tooltip.size();
        tooltip.addAll(at, lines);
    }
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onRenderTooltipPre(RenderTooltipEvent.Pre event) {
        ItemStack stack = event.getItemStack();
        if(stack.isEmpty() || !TooltipStyle.current().appliesTo(stack)) return;
        TooltipRarity rarity = TooltipRarities.get(stack);
        if(rarity == null || rarity.border() == null) return;

        event.setCanceled(true);
        TooltipRenderer.render(event.getGraphics(), event.getFont(), event.getComponents(),
                event.getX(), event.getY(), event.getScreenWidth(), event.getScreenHeight(),
                stack, rarity.border());
    }

    @SubscribeEvent
    public static void onKeyPressed(ScreenEvent.KeyPressed.Pre event) {
        if (!TooltipPager.isActive() || TooltipKeys.NEXT_PAGE == null) return;

        if (event.getScreen().getFocused() instanceof EditBox box && box.isFocused()) return;

        if (TooltipKeys.NEXT_PAGE.matches(event.getKeyCode(), event.getScanCode())) {
            TooltipPager.flip(1);
            event.setCanceled(true);
        } else if (TooltipKeys.PREV_PAGE.matches(event.getKeyCode(), event.getScanCode())) {
            TooltipPager.flip(-1);
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onMouseScrolled(ScreenEvent.MouseScrolled.Pre event) {
        if (!TooltipPager.isActive() || !Screen.hasControlDown()) return;
        if (!TooltipStyle.current().customText().ctrlScrollFlips()) return;
        TooltipPager.flip(event.getScrollDelta() > 0 ? -1 : 1);
        event.setCanceled(true);
    }
}
