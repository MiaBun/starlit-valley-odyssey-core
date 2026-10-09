package com.CuteNekoDragon.Core.utils.tooltips.text;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;

import com.mojang.blaze3d.systems.RenderSystem;

public final class TooltipPager {

    private static final long RESET_AFTER_MS = 250;
    private static final long ACTIVE_WINDOW_MS = 150;

    private static ItemStack current = ItemStack.EMPTY;
    private static long lastSeen;
    private static long shownSince;
    private static int page;
    private static int pageCount;
    private static boolean flippedManually;

    private TooltipPager() {}

    public static int resolvePage(ItemStack stack, int count, float autoPageSeconds) {
        if (count <= 1) return 0;
        if (!RenderSystem.isOnRenderThread() || Minecraft.getInstance().screen == null) return 0;

        long now = Util.getMillis();
        if (now - lastSeen > RESET_AFTER_MS || !ItemStack.isSameItemSameTags(current, stack)) {
            current = stack.copy();
            page = 0;
            flippedManually = false;
            shownSince = now;
        }
        lastSeen = now;
        pageCount = count;

        if (!flippedManually && autoPageSeconds > 0) {
            long period = Math.max(1L, (long) (autoPageSeconds * 1000));
            page = (int) (((now - shownSince) / period) % count);
        }
        page = Math.floorMod(page, count);
        return page;
    }

    public static boolean isActive() {
        return pageCount > 1 && Util.getMillis() - lastSeen < ACTIVE_WINDOW_MS;
    }

    public static void flip(int delta) {
        if (pageCount <= 1) return;
        flippedManually = true;
        page = Math.floorMod(page + delta, pageCount);
    }
}
