package com.CuteNekoDragon.Core.common.data;

import com.CuteNekoDragon.Core.common.blockentity.SleepingBagBlockEntity;
import com.simibubi.create.content.decoration.slidingDoor.SlidingDoorBlockEntity;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackBlockEntity;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.common.data.blocks.SVOBlocks;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

import static com.CuteNekoDragon.Core.SVOCore.REGISTRATE;

public class SVOBlockEntities {

    public static void init() {}

    public static final BlockEntityEntry<BackpackBlockEntity> IRIDIUM_BACKPACK = REGISTRATE
            .<BackpackBlockEntity>blockEntity("iridium_backpack",
                    (type, pos, state) -> new BackpackBlockEntity(pos, state))
            .validBlock(SVOBlocks.IRIDIUM_BACKPACK)
            .register();

    public static final BlockEntityEntry<SleepingBagBlockEntity> SLEEPING_BAG_ENTITY = REGISTRATE
            .<SleepingBagBlockEntity>blockEntity("sleeping_bag",
                    (type, pos, state) -> new SleepingBagBlockEntity(pos, state))
            .validBlock(SVOBlocks.WHITE_SLEEPING_BAG)
            .register();
}
