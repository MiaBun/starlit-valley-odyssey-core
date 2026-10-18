package com.CuteNekoDragon.Core.utils.price;

import com.CuteNekoDragon.Core.SVOCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.Set;

@Mod.EventBusSubscriber(modid = SVOCore.MOD_ID)
public final class PriceSystem {

    public static final PriceManager MANAGER = new PriceManager();

    private PriceSystem() {}

    @SubscribeEvent
    public static void onAddReloadListeners(AddReloadListenerEvent event) {
        event.addListener(MANAGER);
    }

    public static boolean hasPrice(ItemStack stack) {
        return !stack.isEmpty() && MANAGER.resolve(stack.getItem()).basePrice().isPresent();
    }

    public static double getBasePrice(ItemStack stack) {
        return stack.isEmpty() ? 0 : MANAGER.resolve(stack.getItem()).basePrice().orElse(0);
    }

    public static double getPrice(ItemStack stack) {
        return getPrice(stack, null);
    }

    public static double getPrice(ItemStack stack, @Nullable Player player) {
        if (!hasPrice(stack)) return 0;
        ResolvedPrice resolved = MANAGER.resolve(stack.getItem());
        double base = resolved.basePrice().getAsDouble();
        PriceContext ctx = new PriceContext(stack, player, base, resolved.categories());

        double flat = 0, percent = 0;
        for (PriceModifier m : PriceModifiers.all()) {
            PriceModifier.Adjustment a = m.get(ctx);
            flat += a.flat();
            percent += a.percent();
        }
        return Math.max(0, (base + flat) * (1 + percent));
    }

    public static double getStackPrice(ItemStack stack, @Nullable Player player) {
        return getPrice(stack, player) * stack.getCount();
    }

    public static Set<ResourceLocation> getCategories(ItemStack stack) {
        return stack.isEmpty() ? Set.of() : MANAGER.resolve(stack.getItem()).categories();
    }

    public static boolean isInCategory(ItemStack stack, ResourceLocation category) {
        return getCategories(stack).contains(category);
    }
}
