package com.CuteNekoDragon.Core.utils;

import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class RotationUtil {

    public static VoxelShape rotateShape(Direction from, Direction to, VoxelShape shape) {
        VoxelShape[] buffer = new VoxelShape[]{shape, Shapes.empty()};

        int times = (to.get2DDataValue() - from.get2DDataValue() + 4) % 4;
        for (int i = 0; i < times; i++) {
            buffer[1] = Shapes.empty();
            buffer[0].forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) ->
                    buffer[1] = Shapes.or(buffer[1], Shapes.box(minZ, minY, 1 - maxX, maxZ, maxY, 1 - minX))
            );
            buffer[0] = buffer[1];
        }

        return buffer[0];
    }
}
