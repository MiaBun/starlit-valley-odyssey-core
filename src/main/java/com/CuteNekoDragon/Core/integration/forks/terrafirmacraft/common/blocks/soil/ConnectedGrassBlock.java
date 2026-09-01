package com.CuteNekoDragon.Core.integration.forks.terrafirmacraft.common.blocks.soil;

import com.CuteNekoDragon.Core.integration.forks.terrafirmacraft.common.blocks.TFCBlocks;
import com.CuteNekoDragon.Core.integration.forks.terrafirmacraft.util.registry.RegistrySoilVariant;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Properties;
import java.util.function.Supplier;

public class ConnectedGrassBlock extends Block implements IGrassBlock {

    //TODO: TFC

    // Used to determine connected textures
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;

    public static final BooleanProperty SNOWY = BlockStateProperties.SNOWY;

    private static final Map<Direction, BooleanProperty> PROPERTIES = ImmutableMap.of(Direction.NORTH, NORTH, Direction.EAST, EAST, Direction.WEST, WEST, Direction.SOUTH, SOUTH);

    private final Supplier<? extends Block> dirt;
    @Nullable
    private final Supplier<? extends Block> path;
    @Nullable
    private final Supplier<? extends Block> farmland;

    public ConnectedGrassBlock(Properties properties, Supplier<? extends Block> dirt, @Nullable Supplier<? extends Block> path, @Nullable Supplier<? extends Block> farmland) {
        super(properties.hasPostProcess(TFCBlocks::always));

        this.dirt = dirt;
        this.path = path;
        this.farmland = farmland;

        registerDefaultState(stateDefinition.any().setValue(SOUTH, false).setValue(EAST, false).setValue(NORTH, false).setValue(WEST, false).setValue(SNOWY, false));
    }

    ConnectedGrassBlock(Properties properties, SoilBlockType dirtType, RegistrySoilVariant variant) {
        this(properties, variant.getBlock(dirtType), variant.getBlock(SoilBlockType.GRASS_PATH), variant.getBlock(SoilBlockType.FARMLAND));
    }
}
