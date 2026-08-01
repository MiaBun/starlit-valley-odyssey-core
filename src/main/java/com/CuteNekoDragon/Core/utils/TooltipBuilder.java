package com.CuteNekoDragon.Core.utils;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.text.NumberFormat;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings("removal")
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class TooltipBuilder {

    private static final Map<Item, List<Function<ItemStack, Component>>> REGISTRY = new HashMap<>();

    private final Item item;

    private static final ResourceLocation ICON_FONT = new ResourceLocation("svo_core", "icons");

    public enum SVOTypes {

        MINERAL("\uE001", "tooltip.svo_core.mineral_product"),
        GEMSTONE("\uE002", "tooltip.svo_core.gemstone_product"),
        SPECIAL_ITEM("\uE003", "tooltip.svo_core.special_item"),
        BLACKSMITH_ITEM("\uE004", "tooltip.svo_core.blacksmith_item");

        private final String glyph;
        private final String product_type;

        SVOTypes(String glyph, String product_type) {
            this.glyph = glyph;
            this.product_type = product_type;
        }

        public String getGlyph() {
            return glyph;
        }

        public String getProduct_type() {
            return product_type;
        }
    }

    private TooltipBuilder(Item item) {
        this.item = item;
        REGISTRY.putIfAbsent(item, new ArrayList<>());
    }

    public TooltipBuilder addIcon(String glyph, Component label) {
        return addLine(stack -> Component.empty()
                .append(Component.literal(glyph).withStyle(style -> style.withFont(ICON_FONT)))
                .append(Component.literal(" "))
                .append(label));
    }

    public static TooltipBuilder addTooltip(Item item) {
        return new TooltipBuilder(item);
    }

    public static TooltipBuilder addTooltip(Supplier<? extends Item> itemSupplier) {
        return addTooltip(itemSupplier.get());
    }

    public TooltipBuilder addCoins() {
        return addIcon("\uE000",
                Component
                        .translatable("tooltip.svo_core.coins",
                                NumberFormat.getIntegerInstance(Locale.US).format(PriceUtil.getPrice(item)))
                        .withStyle(ChatFormatting.WHITE));
    }

    public TooltipBuilder addDamage(double value) {
        return addLine(stack -> Component.translatable("tooltip.svo_core.damage", value)
                .withStyle(ChatFormatting.RED));
    }

    public TooltipBuilder addInfo(String text) {
        return addLine(stack -> Component.translatable(text)
                .withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
    }

    public TooltipBuilder addGlyph(SVOTypes type) {
        return addLine(stack -> Component.empty()
                .append(Component.literal(type.getGlyph()).withStyle(style -> style.withFont(ICON_FONT)))
                .append(Component.literal(" ")
                        .append(Component.translatable(type.getProduct_type()).withStyle(ChatFormatting.GOLD))));
    }

    public TooltipBuilder addInfo(Component component) {
        return addLine(stack -> component);
    }

    /** Escape hatch for anything not covered above, including stack-dependent text. */
    public TooltipBuilder addLine(Function<ItemStack, Component> lineProvider) {
        REGISTRY.get(item).add(lineProvider);
        return this;
    }

    public TooltipBuilder addLine(Component staticLine) {
        return addLine(stack -> staticLine);
    }

    // ---- Event hookup ----

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        List<Function<ItemStack, Component>> providers = REGISTRY.get(event.getItemStack().getItem());
        if (providers == null || providers.isEmpty()) return;

        int insertIndex = 1;
        List<Component> tooltip = event.getToolTip();

        for (Function<ItemStack, Component> provider : providers) {
            tooltip.add(insertIndex++, provider.apply(event.getItemStack()));
        }
    }
}
