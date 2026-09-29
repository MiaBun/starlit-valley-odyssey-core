package com.CuteNekoDragon.Core.common.datagen.advancements.tabs;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.p3pp3rf1y.sophisticatedstorage.init.ModBlocks;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.common.block.SleepingBagBlock;
import com.CuteNekoDragon.Core.common.data.SVOTags;
import com.CuteNekoDragon.Core.common.data.blocks.SVOBlocks;
import com.CuteNekoDragon.Core.common.data.items.SVOItems;
import com.CuteNekoDragon.Core.common.data.svogt.SVOMachines;
import com.CuteNekoDragon.Core.common.datagen.lang.advancements.AdvancementLangProvider;
import com.tterrag.registrate.util.entry.BlockEntry;

import java.util.function.Consumer;

@SuppressWarnings("removal")
public class Chapter1 implements ForgeAdvancementProvider.AdvancementGenerator {

    @Override
    public void generate(HolderLookup.Provider provider, Consumer<Advancement> consumer,
                         ExistingFileHelper existingFileHelper) {
        Advancement root = Advancement.Builder.advancement()
                .display(
                        new ItemStack(Items.STICK),
                        AdvancementLangProvider.CHAPTER1.title().get(),
                        AdvancementLangProvider.CHAPTER1.description().get(),
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
                        AdvancementLangProvider.CRAFTING_TABLE.title().get(),
                        AdvancementLangProvider.CRAFTING_TABLE.description().get(),
                        null,
                        FrameType.TASK,
                        true, true, false)
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CRAFTING_TABLE))
                .save(consumer, SVOCore.id("chapter1/crafting_table"), existingFileHelper);

        Advancement sacks = Advancement.Builder.advancement()
                .parent(crafting_table)
                .display(
                        new ItemStack(SVOItems.SACK),
                        AdvancementLangProvider.SACK.title().get(),
                        AdvancementLangProvider.SACK.description().get(),
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
                        AdvancementLangProvider.LEATHER_ARMOR.title().get(),
                        AdvancementLangProvider.LEATHER_ARMOR.description().get(),
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
                        AdvancementLangProvider.SLEEPING_BAG.title().get(),
                        AdvancementLangProvider.SLEEPING_BAG.description().get(),
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
                        AdvancementLangProvider.BED.title().get(),
                        AdvancementLangProvider.BED.description().get(),
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
                        AdvancementLangProvider.CHESTS.title().get(),
                        AdvancementLangProvider.CHESTS.description().get(),
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
                        AdvancementLangProvider.CHEST_UPGRADED.title().get(),
                        AdvancementLangProvider.CHEST_UPGRADED.description().get(),
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
                        AdvancementLangProvider.MINE_STONE.title().get(),
                        AdvancementLangProvider.MINE_STONE.description().get(),
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
                        AdvancementLangProvider.GETTING_AN_UPGRADE.title().get(),
                        AdvancementLangProvider.GETTING_AN_UPGRADE.description().get(),
                        null,
                        FrameType.TASK,
                        true, true, false)
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STONE_PICKAXE))
                .save(consumer, SVOCore.id("chapter1/getting_an_upgrade"), existingFileHelper);

        Advancement chainmail_armor = Advancement.Builder.advancement()
                .parent(getting_an_upgrade)
                .display(
                        new ItemStack(Items.CHAINMAIL_CHESTPLATE),
                        AdvancementLangProvider.CHAINMAIL_ARMOR.title().get(),
                        AdvancementLangProvider.CHAINMAIL_ARMOR.description().get(),
                        null,
                        FrameType.TASK,
                        true, true, false)
                .addCriterion("has_item",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item()
                                .of(Items.CHAINMAIL_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.CHAINMAIL_LEGGINGS,
                                        Items.CHAINMAIL_BOOTS)
                                .build()))
                .save(consumer, SVOCore.id("chapter1/chainmail_armor"), existingFileHelper);

        Advancement toolbelt = Advancement.Builder.advancement()
                .parent(getting_an_upgrade)
                .display(
                        new ItemStack(SVOItems.TOOLBELT),
                        AdvancementLangProvider.TOOLBELT.title().get(),
                        AdvancementLangProvider.TOOLBELT.description().get(),
                        null,
                        FrameType.TASK,
                        true, true, false)
                .addCriterion("has_item",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item()
                                .of(SVOItems.TOOLBELT)
                                .build()))
                .save(consumer, SVOCore.id("chapter1/toolbelt"), existingFileHelper);

        Advancement finding_copper = Advancement.Builder.advancement()
                .parent(getting_an_upgrade)
                .display(
                        new ItemStack(Items.COPPER_INGOT),
                        AdvancementLangProvider.FINDING_COPPER.title().get(),
                        AdvancementLangProvider.FINDING_COPPER.description().get(),
                        null,
                        FrameType.TASK,
                        true, true, false)
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_COPPER))
                .save(consumer, SVOCore.id("chapter1/finding_copper"), existingFileHelper);

        Advancement furnace = Advancement.Builder.advancement()
                .parent(finding_copper)
                .display(
                        new ItemStack(Items.FURNACE),
                        AdvancementLangProvider.FURNACE.title().get(),
                        AdvancementLangProvider.FURNACE.description().get(),
                        null,
                        FrameType.TASK,
                        true, true, false)
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.FURNACE))
                .save(consumer, SVOCore.id("chapter1/furnace"), existingFileHelper);

        Advancement charcoal_kiln = Advancement.Builder.advancement()
                .parent(finding_copper)
                .display(
                        new ItemStack(SVOMachines.CHARKOAL_KILN.getItem()),
                        AdvancementLangProvider.CHARCOAL_KILN.title().get(),
                        AdvancementLangProvider.CHARCOAL_KILN.description().get(),
                        null,
                        FrameType.TASK,
                        true, true, false)
                .addCriterion("has_item",
                        InventoryChangeTrigger.TriggerInstance.hasItems(SVOMachines.CHARKOAL_KILN.getItem()))
                .save(consumer, SVOCore.id("chapter1/charcoal_kiln"), existingFileHelper);

        Advancement finding_iron = Advancement.Builder.advancement()
                .parent(finding_copper)
                .display(
                        new ItemStack(Items.IRON_INGOT),
                        AdvancementLangProvider.FINDING_IRON.title().get(),
                        AdvancementLangProvider.FINDING_IRON.description().get(),
                        null,
                        FrameType.TASK,
                        true, true, false)
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_IRON))
                .save(consumer, SVOCore.id("chapter1/finding_iron"), existingFileHelper);

        Advancement mailbox = Advancement.Builder.advancement()
                .parent(finding_iron)
                .display(
                        new ItemStack(SVOMachines.SPRUCE_MAIL_BOX.getItem()),
                        AdvancementLangProvider.MAILBOX.title().get(),
                        AdvancementLangProvider.MAILBOX.description().get(),
                        null,
                        FrameType.TASK,
                        true, true, false)
                .addCriterion("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item()
                        .of(SVOTags.Items.MAIL_BOX)
                        .build()))
                .save(consumer, SVOCore.id("chapter1/mailbox"), existingFileHelper);
    }
}
