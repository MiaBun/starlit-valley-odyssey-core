package com.CuteNekoDragon.Core.common.item;

import net.minecraft.world.item.Item;

public class ToolbeltItem extends Item {

    private final int slots;

    public ToolbeltItem(Properties properties, int slots) {
        super(properties);
        this.slots = slots;
    }
}
