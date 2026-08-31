package com.CuteNekoDragon.Core.common.item;

import net.minecraft.world.item.Item;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class ToolbeltItem extends Item implements ICurioItem {

    private final int slots;

    public ToolbeltItem(Properties properties, int slots) {
        super(properties);
        this.slots = slots;
    }
}
