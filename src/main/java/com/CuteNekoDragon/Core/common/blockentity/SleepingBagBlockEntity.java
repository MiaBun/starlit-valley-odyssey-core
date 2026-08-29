package com.CuteNekoDragon.Core.common.blockentity;

import com.CuteNekoDragon.Core.common.data.SVOBlockEntities;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BedBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class SleepingBagBlockEntity extends BedBlockEntity {

    private final BlockEntityType blockEntityEntry;

    public SleepingBagBlockEntity(BlockPos pos, BlockState state, BlockEntityType blockEntityEntry, DyeColor color) {
        super(pos, state, color);
        this.blockEntityEntry = blockEntityEntry;
    }

    @Override
    public BlockEntityType<?> getType() {
        return blockEntityEntry;
    }
}