package com.CuteNekoDragon.Core.common.item;

import com.CuteNekoDragon.Core.SVOCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

@SuppressWarnings("removal")
public class SVOTiers {

    public static final Tier COPPER_TIER = TierSortingRegistry.registerTier(
            new ForgeTier(
                    1,
                    190,
                    6.0f,
                    1.5f,
                    14,
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    () -> Ingredient.of(Items.COPPER_INGOT)
            ),
            new ResourceLocation(SVOCore.MOD_ID, "copper"),
            List.of(Tiers.STONE),
            List.of(Tiers.IRON)
    );
}
