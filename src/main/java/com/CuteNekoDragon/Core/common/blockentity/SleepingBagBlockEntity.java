package com.CuteNekoDragon.Core.common.blockentity;

import com.CuteNekoDragon.Core.common.data.SVOBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BedBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class SleepingBagBlockEntity extends BedBlockEntity {
    public SleepingBagBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state, DyeColor.WHITE);
    }

    @Override
    public BlockEntityType<?> getType() {
        return SVOBlockEntities.SLEEPING_BAG_ENTITY.get();
    }
}