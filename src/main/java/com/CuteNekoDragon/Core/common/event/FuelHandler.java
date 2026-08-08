package com.CuteNekoDragon.Core.common.event;

import com.CuteNekoDragon.Core.SVOCore;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SVOCore.MOD_ID)
public class FuelHandler {

    @SubscribeEvent
    public static void onFurnaceFuelBurnTime(FurnaceFuelBurnTimeEvent e) {
        ItemStack stack = e.getItemStack();

        if(stack.is(ItemTags.LOGS) || stack.is(ItemTags.BOATS) || stack.is(ItemTags.SLABS) || stack.is(ItemTags.STAIRS) || stack.is(ItemTags.WOODEN_PRESSURE_PLATES) || stack.is(ItemTags.WOODEN_BUTTONS) || stack.is(ItemTags.WOODEN_FENCES) || stack.is(ItemTags.WOODEN_DOORS) || stack.is(ItemTags.WOODEN_TRAPDOORS) || stack.is(ItemTags.FENCE_GATES) || stack.is(ItemTags.CHEST_BOATS) || stack.is(Items.CRAFTING_TABLE) || stack.is(Items.CHEST) || stack.is(Items.BARREL) || stack.is(Items.LADDER) || stack.is(Items.STICK) || stack.is(ItemTags.SAPLINGS)) {
            e.setBurnTime(0);
        }
    }
}
