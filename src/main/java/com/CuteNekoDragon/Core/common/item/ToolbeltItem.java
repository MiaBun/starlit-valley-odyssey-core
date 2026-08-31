package com.CuteNekoDragon.Core.common.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class ToolbeltItem extends Item implements ICurioItem {

    private final int slots;

    public ToolbeltItem(Properties properties, int slots) {
        super(properties);
        this.slots = slots;
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return slotContext.identifier().equals("toolbelt");
    }

    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        return true;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
    }
}
