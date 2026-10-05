package com.CuteNekoDragon.Core.utils.tooltips.rarity;

import com.CuteNekoDragon.Core.utils.tooltips.rarity.border.BorderRenderer;
import com.CuteNekoDragon.Core.utils.tooltips.util.TextUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Supplier;

public final class TooltipRarity {
    private final ResourceLocation id;
    private final Component name;
    @Nullable
    private final TextColor color;
    private final boolean recolorName;
    private final boolean showLabel;
    private final int priority;
    private final Supplier<BorderRenderer> border;
    private final TagKey<Item> tag;

    private TooltipRarity(Builder b) {
        this.id = b.id;
        this.name = b.name != null ? b.name
                : Component.translatableWithFallback("tooltip." + b.id.getNamespace() + ".rarity." + b.id.getPath(), TextUtil.titleCase(b.id.getPath()));
        this.color = b.color;
        this.recolorName = b.recolorName;
        this.showLabel = b.showLabel;
        this.priority = b.priority;
        this.border = b.border;
        this.tag = TagKey.create(Registries.ITEM, new ResourceLocation(b.id.getNamespace(), "tooltip/rarity/" + b.id.getPath()));
    }

    public static final class Builder {
        private final ResourceLocation id;
        private Component name;
        private TextColor color;
        private boolean recolorName = true;
        private boolean showLabel = true;
        private int priority;
        private Supplier<BorderRenderer> border;

        private Builder(ResourceLocation id) {
            this.id = Objects.requireNonNull(id);
        }

        public Builder name(Component name) { this.name = name; return this; }
        public Builder name(String keyOrText) { this.name = TextUtil.keyOrLiteral(keyOrText); return this; }
        public Builder color(@Nullable TextColor color) { this.color = color; return this; }
        public Builder color(int rgb) { this.color = TextColor.fromRgb(rgb); return this; }
        public Builder color(ChatFormatting formatting) { this.color = TextColor.fromLegacyFormat(formatting); return this; }
        public Builder recolorName(boolean v) { this.recolorName = v; return this; }
        public Builder showLabel(boolean v) { this.showLabel = v; return this; }
        public Builder priority(int priority) { this.priority = priority; return this; }
        public Builder border(@Nullable BorderRenderer border) { this.border = border == null ? null : () -> border; return this; }
        public Builder lazyBorder(Supplier<BorderRenderer> border) { this.border = border; return this; }

        public TooltipRarity build() {
            return new TooltipRarity(this);
        }
    }
}
