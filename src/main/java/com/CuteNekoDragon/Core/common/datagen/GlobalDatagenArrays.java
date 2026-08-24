package com.CuteNekoDragon.Core.common.datagen;

import net.minecraft.world.item.Item;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class GlobalDatagenArrays {

    public static List<Item> RemovedItems = new ArrayList<>(List.of(
            ModItems.STACK_UPGRADE_STARTER_TIER_TO_TIER_1_CONVERSION.get(),
            ModItems.STACK_UPGRADE_STARTER_TIER_TO_TIER_2_CONVERSION.get(),
            ModItems.STACK_UPGRADE_STARTER_TIER_TO_TIER_3_CONVERSION.get(),
            ModItems.STACK_UPGRADE_STARTER_TIER_TO_TIER_4_CONVERSION.get(),
            ModItems.STACK_UPGRADE_TIER_1_TO_TIER_2_CONVERSION.get(),
            ModItems.STACK_UPGRADE_TIER_1_TO_TIER_3_CONVERSION.get(),
            ModItems.STACK_UPGRADE_TIER_1_TO_TIER_4_CONVERSION.get(),
            ModItems.STACK_UPGRADE_TIER_2_TO_TIER_3_CONVERSION.get(),
            ModItems.STACK_UPGRADE_TIER_2_TO_TIER_4_CONVERSION.get(),
            ModItems.STACK_UPGRADE_TIER_3_TO_TIER_4_CONVERSION.get(),
            ModItems.NETHERITE_BACKPACK.get(),
            ModItems.FEEDING_UPGRADE.get(),
            ModItems.ADVANCED_FEEDING_UPGRADE.get(),
            ModItems.TOOL_SWAPPER_UPGRADE.get(),
            ModItems.ADVANCED_TOOL_SWAPPER_UPGRADE.get(),
            net.p3pp3rf1y.sophisticatedstorage.init.ModItems.FEEDING_UPGRADE.get(),
            net.p3pp3rf1y.sophisticatedstorage.init.ModItems.ADVANCED_FEEDING_UPGRADE.get(),
            net.p3pp3rf1y.sophisticatedstorage.init.ModItems.STACK_UPGRADE_TIER_1_PLUS.get()
    ));
}
