package com.CuteNekoDragon.Core.common.data;

import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackBlockEntity;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.common.data.blocks.SVOBlocks;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

public class SVOBlockEntities {

    public static void init() {}

    public static final BlockEntityEntry<BackpackBlockEntity> IRIDIUM_BACKPACK = SVOCore.REGISTRATE
            .<BackpackBlockEntity>blockEntity("iridium_backpack",
                    (type, pos, state) -> new BackpackBlockEntity(pos, state))
            .validBlock(SVOBlocks.IRIDIUM_BACKPACK)
            .register();
}
