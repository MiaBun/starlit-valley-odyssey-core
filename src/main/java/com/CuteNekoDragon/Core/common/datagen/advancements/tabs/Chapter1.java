package com.CuteNekoDragon.Core.common.datagen.advancements.tabs;

import com.CuteNekoDragon.Core.common.block.SleepingBagBlock;
import com.CuteNekoDragon.Core.common.data.blocks.SVOBlocks;
import com.CuteNekoDragon.Core.common.item.SVOSmithingTemplate;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.common.data.SVOTags;
import com.CuteNekoDragon.Core.common.data.items.SVOItems;
import com.CuteNekoDragon.Core.common.data.svogt.SVOMachines;
import net.p3pp3rf1y.sophisticatedstorage.init.ModBlocks;

import java.util.function.Consumer;

import static com.CuteNekoDragon.Core.common.data.items.SVOItems.UPGRADE_TEMPLATES;

@SuppressWarnings("removal")
public class Chapter1 implements ForgeAdvancementProvider.AdvancementGenerator {

    @Override
    public void generate(HolderLookup.Provider provider, Consumer<Advancement> consumer,
                         ExistingFileHelper existingFileHelper) {
        Advancement root = Advancement.Builder.advancement()
                .display(
                        new ItemStack(Items.STICK),
                        Component.translatable("advancement.svo_core.chapter1.title"),
                        Component.translatable("advancement.svo_core.chapter1.description"),
                        new ResourceLocation("minecraft", "textures/gui/advancements/backgrounds/stone.png"),
                        FrameType.TASK,
                        true,
                        true,
                        false)
                .addCriterion("has_item",
                        InventoryChangeTrigger.TriggerInstance
                                .hasItems(ItemPredicate.Builder.item().of(ItemTags.LOGS).build()))
                .save(consumer, SVOCore.id("chapter1/root"), existingFileHelper);

        Advancement crafting_table = Advancement.Builder.advancement()
                .parent(root)
                .display(
                        new ItemStack(Items.CRAFTING_TABLE),
                        Component.translatable("advancement.svo_core.chapter1.crafting_table.title"),
                        Component.translatable("advancement.svo_core.chapter1.crafting_table.description"),
                        null,
                        FrameType.TASK,
                        true, true, false)
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CRAFTING_TABLE))
                .save(consumer, SVOCore.id("chapter1/crafting_table"), existingFileHelper);

        Advancement sacks = Advancement.Builder.advancement()
                .parent(crafting_table)
                .display(
                        new ItemStack(SVOItems.SACK),
                        Component.translatable("advancement.svo_core.chapter1.sack.title"),
                        Component.translatable("advancement.svo_core.chapter1.sack.description"),
                        null,
                        FrameType.TASK,
                        true, true, false)
                .addCriterion("has_item",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item()
                                .of(SVOTags.Items.SACK)
                                .build()))
                .save(consumer, SVOCore.id("chapter1/sacks"), existingFileHelper);

        Advancement leather_armor = Advancement.Builder.advancement()
                .parent(crafting_table)
                .display(
                        new ItemStack(Items.LEATHER_CHESTPLATE),
                        Component.translatable("advancement.svo_core.chapter1.leather_armor.title"),
                        Component.translatable("advancement.svo_core.chapter1.leather_armor.description"),
                        null,
                        FrameType.TASK,
                        true, true, false)
                .addCriterion("has_item",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item()
                                .of(Items.LEATHER_HELMET, Items.LEATHER_CHESTPLATE, Items.LEATHER_LEGGINGS,
                                        Items.LEATHER_BOOTS)
                                .build()))
                .save(consumer, SVOCore.id("chapter1/leather_armor"), existingFileHelper);

        BlockEntry<SleepingBagBlock> WHITE_SLEEPING_BAG = SVOBlocks.SLEEPING_BAGS.get(DyeColor.WHITE);

        Advancement sleeping_bags = Advancement.Builder.advancement()
                .parent(crafting_table)
                .display(
                        new ItemStack(WHITE_SLEEPING_BAG.asItem()),
                        Component.translatable("advancement.svo_core.chapter1.sleeping_bag.title"),
                        Component.translatable("advancement.svo_core.chapter1.sleeping_bag.description"),
                        null,
                        FrameType.TASK,
                        true, true, false)
                .addCriterion("has_item",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item()
                                .of(SVOTags.Items.Sleeping_Bags)
                                .build()))
                .save(consumer, SVOCore.id("chapter1/sleeping_bags"), existingFileHelper);

        Advancement beds = Advancement.Builder.advancement()
                .parent(sleeping_bags)
                .display(
                        new ItemStack(Items.WHITE_BED),
                        Component.translatable("advancement.svo_core.chapter1.bed.title"),
                        Component.translatable("advancement.svo_core.chapter1.bed.description"),
                        null,
                        FrameType.TASK,
                        true, true, false)
                .addCriterion("has_item",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item()
                                .of(ItemTags.BEDS)
                                .build()))
                .save(consumer, SVOCore.id("chapter1/beds"), existingFileHelper);

        Advancement chests = Advancement.Builder.advancement()
                .parent(crafting_table)
                .display(
                        new ItemStack(Items.CHEST),
                        Component.translatable("advancement.svo_core.chapter1.chests.title"),
                        Component.translatable("advancement.svo_core.chapter1.chests.description"),
                        null,
                        FrameType.TASK,
                        true, true, false)
                .addCriterion("has_item",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item()
                                .of(Items.CHEST)
                                .build()))
                .save(consumer, SVOCore.id("chapter1/chests"), existingFileHelper);

        Advancement chest_upgrades = Advancement.Builder.advancement()
                .parent(chests)
                .display(
                        new ItemStack(ModBlocks.CHEST_ITEM.get()),
                        Component.translatable("advancement.svo_core.chapter1.chest_upgraded.title"),
                        Component.translatable("advancement.svo_core.chapter1.chest_upgraded.description"),
                        null,
                        FrameType.TASK,
                        true, true, false)
                .addCriterion("has_item",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item()
                                .of(ModBlocks.CHEST_ITEM.get())
                                .build()))
                .save(consumer, SVOCore.id("chapter1/chest_upgrades"), existingFileHelper);

        Advancement mine_stone = Advancement.Builder.advancement()
                .parent(crafting_table)
                .display(
                        new ItemStack(Items.WOODEN_PICKAXE),
                        Component.translatable("advancement.svo_core.chapter1.mine_stone.title"),
                        Component.translatable("advancement.svo_core.chapter1.mine_stone.description"),
                        null,
                        FrameType.TASK,
                        true, true, false)
                .addCriterion("has_item",
                        InventoryChangeTrigger.TriggerInstance
                                .hasItems(ItemPredicate.Builder.item().of(ItemTags.STONE_TOOL_MATERIALS).build()))
                .save(consumer, SVOCore.id("chapter1/mine_stone"), existingFileHelper);

        Advancement getting_an_upgrade = Advancement.Builder.advancement()
                .parent(mine_stone)
                .display(
                        new ItemStack(Items.STONE_PICKAXE),
                        Component.translatable("advancement.svo_core.chapter1.getting_an_upgrade.title"),
                        Component.translatable("advancement.svo_core.chapter1.getting_an_upgrade.description"),
                        null,
                        FrameType.TASK,
                        true, true, false)
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STONE_PICKAXE))
                .save(consumer, SVOCore.id("chapter1/getting_an_upgrade"), existingFileHelper);

        Advancement chainmail_armor = Advancement.Builder.advancement()
                .parent(getting_an_upgrade)
                .display(
                        new ItemStack(Items.CHAINMAIL_CHESTPLATE),
                        Component.translatable("advancement.svo_core.chapter1.chainmail_armor.title"),
                        Component.translatable("advancement.svo_core.chapter1.chainmail_armor.description"),
                        null,
                        FrameType.TASK,
                        true, true, false)
                .addCriterion("has_item",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item()
                                .of(Items.CHAINMAIL_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.CHAINMAIL_LEGGINGS,
                                        Items.CHAINMAIL_BOOTS)
                                .build()))
                .save(consumer, SVOCore.id("chapter1/chainmail_armor"), existingFileHelper);

        Advancement finding_copper = Advancement.Builder.advancement()
                .parent(getting_an_upgrade)
                .display(
                        new ItemStack(Items.COPPER_INGOT),
                        Component.translatable("advancement.svo_core.chapter1.finding_copper.title"),
                        Component.translatable("advancement.svo_core.chapter1.finding_copper.description"),
                        null,
                        FrameType.TASK,
                        true, true, false)
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_COPPER))
                .save(consumer, SVOCore.id("chapter1/finding_copper"), existingFileHelper);

        Advancement furnace = Advancement.Builder.advancement()
                .parent(finding_copper)
                .display(
                        new ItemStack(Items.FURNACE),
                        Component.translatable("advancement.svo_core.chapter1.furnace.title"),
                        Component.translatable("advancement.svo_core.chapter1.furnace.description"),
                        null,
                        FrameType.TASK,
                        true, true, false)
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.FURNACE))
                .save(consumer, SVOCore.id("chapter1/furnace"), existingFileHelper);

        Advancement charcoal_kiln = Advancement.Builder.advancement()
                .parent(finding_copper)
                .display(
                        new ItemStack(SVOMachines.CHARKOAL_KILN.getItem()),
                        Component.translatable("advancement.svo_core.chapter1.charcoal_kiln.title"),
                        Component.translatable("advancement.svo_core.chapter1.charcoal_kiln.description"),
                        null,
                        FrameType.TASK,
                        true, true, false)
                .addCriterion("has_item",
                        InventoryChangeTrigger.TriggerInstance.hasItems(SVOMachines.CHARKOAL_KILN.getItem()))
                .save(consumer, SVOCore.id("chapter1/charcoal_kiln"), existingFileHelper);
    }
}
