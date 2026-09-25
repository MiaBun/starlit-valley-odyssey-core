package com.CuteNekoDragon.Core.client.util;

import net.minecraft.core.BlockPos;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ClientMailboxTracker {

    private static final Set<BlockPos> POSITIONS = ConcurrentHashMap.newKeySet();

    public static void register(BlockPos pos) {
        POSITIONS.add(pos);
    }

    public static void unregister(BlockPos pos) {
        POSITIONS.remove(pos);
    }

    public static Set<BlockPos> getPositions() {
        return POSITIONS;
    }
}
