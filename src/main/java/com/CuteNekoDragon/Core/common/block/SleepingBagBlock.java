package com.CuteNekoDragon.Core.common.block;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.EnumMap;
import java.util.Map;

import static com.CuteNekoDragon.Core.utils.RotationUtil.rotateShape;

@SuppressWarnings("deprecation")
public class SleepingBagBlock extends BedBlock {

    private static final VoxelShape SHAPE_NORTH = Block.box(0, 0, 0, 16, 2, 16);

    private static final Map<Direction, VoxelShape> SHAPES = Util.make(new EnumMap<>(Direction.class), map -> {
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            map.put(dir, rotateShape(Direction.NORTH, dir, SHAPE_NORTH));
        }
    });

    public SleepingBagBlock(Properties properties, DyeColor color) {
        super(color, properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES.get(state.getValue(FACING));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES.get(state.getValue(FACING));
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
                                 BlockHitResult hit) {
        if (level.isClientSide || !(player instanceof ServerPlayer serverPlayer)) {
            return super.use(state, level, pos, player, hand, hit);
        }

        // snapshot current respawn data
        ResourceKey<Level> spawnDim = serverPlayer.getRespawnDimension();
        BlockPos spawnPos = serverPlayer.getRespawnPosition();
        float spawnAngle = serverPlayer.getRespawnAngle();
        boolean spawnForced = serverPlayer.isRespawnForced();

        BlockPos headPos = state.getValue(PART) == BedPart.HEAD ? pos : pos.relative(state.getValue(FACING));

        serverPlayer.setRespawnPosition(level.dimension(), headPos, serverPlayer.getYRot(), false, false);

        InteractionResult result = super.use(state, level, pos, player, hand, hit);

        serverPlayer.setRespawnPosition(spawnDim, spawnPos, spawnAngle, spawnForced, false);

        return result;
    }
}
