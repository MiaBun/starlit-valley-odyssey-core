package com.CuteNekoDragon.Core.common.data;

import net.minecraft.world.item.DyeColor;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackBlockEntity;

import com.CuteNekoDragon.Core.common.blockentity.SleepingBagBlockEntity;
import com.CuteNekoDragon.Core.common.data.blocks.SVOBlocks;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

import java.util.EnumMap;
import java.util.Map;

import static com.CuteNekoDragon.Core.SVOCore.REGISTRATE;

public class SVOBlockEntities {

    public static void init() {}

    public static final BlockEntityEntry<BackpackBlockEntity> IRIDIUM_BACKPACK = REGISTRATE
            .<BackpackBlockEntity>blockEntity("iridium_backpack",
                    (type, pos, state) -> new BackpackBlockEntity(pos, state))
            .validBlock(SVOBlocks.IRIDIUM_BACKPACK)
            .register();

    public static final Map<DyeColor, BlockEntityEntry<SleepingBagBlockEntity>> SLEEPING_BAG_ENTITIES = new EnumMap<>(
            DyeColor.class);

    static {
        for (DyeColor color : DyeColor.values()) {
            String name = color.getSerializedName() + "_sleeping_bag";

            BlockEntityEntry<SleepingBagBlockEntity> entry = REGISTRATE
                    .<SleepingBagBlockEntity>blockEntity(name,
                            (type, pos, state) -> new SleepingBagBlockEntity(pos, state, type, color))
                    .validBlock(SVOBlocks.SLEEPING_BAGS.get(color))
                    .register();

            SLEEPING_BAG_ENTITIES.put(color, entry);
        }
    }
}
