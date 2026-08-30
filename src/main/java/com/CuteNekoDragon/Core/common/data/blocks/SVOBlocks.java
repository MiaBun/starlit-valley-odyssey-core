package com.CuteNekoDragon.Core.common.data.blocks;

import com.CuteNekoDragon.Core.common.block.SleepingBagBlock;
import com.CuteNekoDragon.Core.common.data.SVOTags;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Direction;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackBlock;

import com.CuteNekoDragon.Core.SVOCore;
import com.tterrag.registrate.util.entry.BlockEntry;

import java.util.EnumMap;
import java.util.Map;

import static com.CuteNekoDragon.Core.SVOCore.REGISTRATE;

public final class SVOBlocks {

    public static void init() {
    }

    public static final BlockEntry<BackpackBlock> IRIDIUM_BACKPACK = SVOCore.REGISTRATE
            .block("iridium_backpack", p -> new BackpackBlock())
            .blockstate((ctx, prov) -> prov.simpleBlock(ctx.getEntry(),
                    prov.models().getExistingFile(SVOCore.id("block/iridium_backpack"))))
            .register();

    public static final Map<DyeColor, BlockEntry<SleepingBagBlock>> SLEEPING_BAGS = new EnumMap<>(DyeColor.class);

    static {
        for (DyeColor color : DyeColor.values()) {
            String name = color.getSerializedName() + "_sleeping_bag";
            Block woolBlock = getWoolBlock(color);
            Item woolItem = woolBlock.asItem();

            BlockEntry<SleepingBagBlock> entry = REGISTRATE
                    .block(name, (properties) -> new SleepingBagBlock(properties, color))
                    .tag(SVOTags.Blocks.Sleeping_Bags)
                    .initialProperties(() -> woolBlock)
                    .properties(p -> p.noOcclusion().strength(0.1F).sound(SoundType.WOOL))
                    .blockstate((ctx, prov) -> {
                        ResourceLocation headModel = prov.modLoc("block/" + name + "_head");
                        ResourceLocation footModel = prov.modLoc("block/" + name + "_foot");
                        prov.getVariantBuilder(ctx.getEntry()).forAllStates(state -> {
                            Direction facing = state.getValue(BedBlock.FACING);
                            BedPart part = state.getValue(BedBlock.PART);
                            int rotY = (int) facing.toYRot();
                            return ConfiguredModel.builder()
                                    .modelFile(prov.models().getExistingFile(part == BedPart.HEAD ? headModel : footModel))
                                    .rotationY(rotY)
                                    .build();
                        });
                    })
                    .loot((lt, block) -> lt.add(block,
                            LootTable.lootTable().withPool(
                                    LootPool.lootPool()
                                            .setRolls(ConstantValue.exactly(1))
                                            .add(LootItem.lootTableItem(block)
                                                    .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                            .setProperties(StatePropertiesPredicate.Builder.properties()
                                                                    .hasProperty(BedBlock.PART, BedPart.HEAD))))
                                            .when(ExplosionCondition.survivesExplosion())
                            )
                    ))
                    .item()
                    .model((ctx, prov) -> prov.withExistingParent(ctx.getName(), prov.modLoc("block/" + name + "_foot")))
                    .recipe((ctx, provider) -> {
                        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ctx.get())
                                .pattern(" A ")
                                .pattern(" A ")
                                .define('A', woolItem)
                                .unlockedBy("has_wool", InventoryChangeTrigger.TriggerInstance.hasItems(woolItem))
                                .save(provider, SVOCore.id("shaped/" + name));
                    })
                    .properties(p -> p.stacksTo(1))
                    .tag(SVOTags.Items.Sleeping_Bags)
                    .build()
                    .register();

            SLEEPING_BAGS.put(color, entry);
        }
    }

    private static Block getWoolBlock(DyeColor color) {
        return switch (color) {
            case WHITE -> Blocks.WHITE_WOOL;
            case ORANGE -> Blocks.ORANGE_WOOL;
            case MAGENTA -> Blocks.MAGENTA_WOOL;
            case LIGHT_BLUE -> Blocks.LIGHT_BLUE_WOOL;
            case YELLOW -> Blocks.YELLOW_WOOL;
            case LIME -> Blocks.LIME_WOOL;
            case PINK -> Blocks.PINK_WOOL;
            case GRAY -> Blocks.GRAY_WOOL;
            case LIGHT_GRAY -> Blocks.LIGHT_GRAY_WOOL;
            case CYAN -> Blocks.CYAN_WOOL;
            case PURPLE -> Blocks.PURPLE_WOOL;
            case BLUE -> Blocks.BLUE_WOOL;
            case BROWN -> Blocks.BROWN_WOOL;
            case GREEN -> Blocks.GREEN_WOOL;
            case RED -> Blocks.RED_WOOL;
            case BLACK -> Blocks.BLACK_WOOL;
        };
    }
}