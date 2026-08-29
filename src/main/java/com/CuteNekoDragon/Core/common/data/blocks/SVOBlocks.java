package com.CuteNekoDragon.Core.common.data.blocks;

import com.CuteNekoDragon.Core.common.block.SleepingBagBlock;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Direction;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.BedBlock;
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

import static com.CuteNekoDragon.Core.SVOCore.REGISTRATE;

public final class SVOBlocks {

    public static void init() {}

    public static final BlockEntry<BackpackBlock> IRIDIUM_BACKPACK = SVOCore.REGISTRATE
            .block("iridium_backpack", p -> new BackpackBlock())
            .blockstate((ctx, prov) -> prov.simpleBlock(ctx.getEntry(),
                    prov.models().getExistingFile(SVOCore.id("block/iridium_backpack"))))
            .register();

    public static BlockEntry<SleepingBagBlock> WHITE_SLEEPING_BAG = REGISTRATE
            .block("sleeping_bag", (properties) -> new SleepingBagBlock(properties, DyeColor.WHITE))
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.noOcclusion().strength(0.1F).sound(SoundType.WOOL))
            .blockstate((ctx, prov) -> {
                ResourceLocation headModel = prov.modLoc("block/sleeping_bag_head");
                ResourceLocation footModel = prov.modLoc("block/sleeping_bag_foot");
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
            .model((ctx, prov) -> prov.withExistingParent(ctx.getName(), prov.modLoc("block/sleeping_bag_foot")))
            .recipe((ctx, provider) -> {
                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ctx.get())
                        .pattern(" A ")
                        .pattern(" A ")
                        .define('A', Items.WHITE_WOOL)
                        .unlockedBy("has_wool", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WHITE_WOOL))
                        .save(provider, SVOCore.id("shaped/white_sleeping_bag"));
            })
            .build()
            .register();
}
