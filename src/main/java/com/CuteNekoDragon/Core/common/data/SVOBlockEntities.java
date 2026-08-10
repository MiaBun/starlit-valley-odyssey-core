package com.CuteNekoDragon.Core.common.data;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.common.data.blocks.SVOBlocks;
import com.tterrag.registrate.builders.BlockEntityBuilder;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackBlockEntity;

public class SVOBlockEntities {

    public static void init() {

    }

    public static final BlockEntityEntry<BackpackBlockEntity> IRIDIUM_BACKPACK = SVOCore.REGISTRATE.<BackpackBlockEntity>blockEntity("iridium_backpack", (type, pos, state) -> new BackpackBlockEntity(pos, state))
            .validBlock(SVOBlocks.IRIDIUM_BACKPACK)
            .register();
}
