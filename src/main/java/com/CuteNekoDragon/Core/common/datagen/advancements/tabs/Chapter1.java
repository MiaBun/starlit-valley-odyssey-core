package com.CuteNekoDragon.Core.common.datagen.advancements.tabs;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.common.data.items.SVOItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.function.Consumer;

@SuppressWarnings("removal")
public class Chapter1 implements ForgeAdvancementProvider.AdvancementGenerator {

    @Override
    public void generate(HolderLookup.Provider provider, Consumer<Advancement> consumer, ExistingFileHelper existingFileHelper) {
        Advancement root = Advancement.Builder.advancement()
                .display(
                        new ItemStack(Items.STICK),
                        Component.translatable("advancement.svo_core.chapter1.title"),
                        Component.translatable("advancement.svo_core.chapter1.description"),
                        new ResourceLocation("minecraft", "textures/gui/advancements/backgrounds/stone.png"),
                        FrameType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
                .save(consumer, SVOCore.id("chapter1"), existingFileHelper);

        Advancement.Builder.advancement()
                .parent(root)
                .display(
                        new ItemStack(Items.CRAFTING_TABLE),
                        Component.translatable("advancement.svo_core.chapter1.advancement1.title"),
                        Component.translatable("advancement.svo_core.chapter1.advancement1.description"),
                        null,
                        FrameType.TASK,
                        true, true, false
                )
                .addCriterion("has_other_item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CRAFTING_TABLE))
                .save(consumer, SVOCore.id("crafting_table_advancement"), existingFileHelper);
    }
}
