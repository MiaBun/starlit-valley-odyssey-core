package com.CuteNekoDragon.Core.common.data.blocks;

import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackBlock;

import com.CuteNekoDragon.Core.SVOCore;
import com.tterrag.registrate.util.entry.BlockEntry;

public final class SVOBlocks {

    public static void init() {}

    public static final BlockEntry<BackpackBlock> IRIDIUM_BACKPACK = SVOCore.REGISTRATE
            .block("iridium_backpack", p -> new BackpackBlock())
            .blockstate((ctx, prov) -> prov.simpleBlock(ctx.getEntry(),
                    prov.models().getExistingFile(SVOCore.id("block/iridium_backpack"))))
            .register();
}
