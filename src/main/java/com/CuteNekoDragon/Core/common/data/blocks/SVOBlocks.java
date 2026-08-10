package com.CuteNekoDragon.Core.common.data.blocks;

import com.CuteNekoDragon.Core.SVOCore;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.resources.ResourceLocation;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackBlock;

public final class SVOBlocks {

    public static void init() {

    }

    public static final BlockEntry<BackpackBlock> IRIDIUM_BACKPACK = SVOCore.REGISTRATE.block("iridium_backpack", p -> new BackpackBlock())
            .blockstate((ctx, prov) -> prov.simpleBlock(ctx.getEntry(),
                    prov.models().getExistingFile(SVOCore.id("block/iridium_backpack"))))
            .register();

}
