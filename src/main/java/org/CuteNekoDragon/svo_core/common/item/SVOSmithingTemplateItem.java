package org.CuteNekoDragon.svo_core.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class SVOSmithingTemplateItem  extends Item {

    private final String tier;

    public SVOSmithingTemplateItem(String tier, Properties properties) {
        super(properties);
        this.tier = tier;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.svo." + tier + "_upgrade_smithing_template.upgrade")
                .withStyle(ChatFormatting.GRAY));

        tooltip.add(Component.literal(" "));

        tooltip.add(Component.translatable("item.minecraft.smithing_template.applies_to")
                .withStyle(ChatFormatting.GRAY));

        tooltip.add(Component.literal(" ").append(Component.translatable("item.svo." + tier + "_upgrade_smithing_template.applies_to")
                .withStyle(ChatFormatting.BLUE)));

        tooltip.add(Component.translatable("item.minecraft.smithing_template.ingredients")
                .withStyle(ChatFormatting.GRAY));

        tooltip.add(Component.literal(" ").append(Component.translatable("item.svo." + tier + "_upgrade_smithing_template.ingredients")
                .withStyle(ChatFormatting.BLUE)));
    }
}
