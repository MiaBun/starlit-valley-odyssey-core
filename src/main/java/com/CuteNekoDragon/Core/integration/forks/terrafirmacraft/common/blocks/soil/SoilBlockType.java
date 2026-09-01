package com.CuteNekoDragon.Core.integration.forks.terrafirmacraft.common.blocks.soil;

public enum SoilBlockType {

    //TODO: TFC
    DIRT,
    GRASS,
    GRASS_PATH,
    CLAY,
    CLAY_GRASS,
    FARMLAND,
    ROOTED_DIRT,
    MUD,
    MUD_BRICKS,
    DRYING_BRICKS,
    MUDDY_ROOTS;

    public static final SoilBlockType[] VALUES = values();

    public static SoilBlockType valueOf(int i) { return i >= 0 && i < VALUES.length ? VALUES[i] : DIRT;}
}
