package com.CuteNekoDragon.Core.common.data.blocks;

import com.CuteNekoDragon.Core.common.block.SleepingBagBlock;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
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
            .block("sleeping_bag", SleepingBagBlock::new)
            .initialProperties(() -> Blocks.WHITE_WOOL)
            .properties(p -> p.noOcclusion().strength(0.1F).sound(SoundType.WOOL))
            .blockstate((ctx, prv) -> prv.horizontalBlock(
                    ctx.getEntry(),
                    prv.models().getExistingFile(SVOCore.id("block/sleeping_bag"))
            ))
            .loot(RegistrateBlockLootTables::dropSelf)
            .item()
            .model((ctx, prov) -> prov.withExistingParent(ctx.getName(), SVOCore.id("block/sleeping_bag")))
            .build()
            .register();
}
