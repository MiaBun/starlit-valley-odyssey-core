package com.CuteNekoDragon.Core.integration.forks.terrafirmacraft.util.registry;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import com.CuteNekoDragon.Core.integration.forks.terrafirmacraft.common.blocks.soil.SoilBlockType;

import java.util.function.Supplier;

/**
 * Interface for use in {@link SoilBlockType} registration calls.
 * <br>
 * For the methods that return {@link Supplier}s, it is <strong>not required</strong> to implement all possible inputs -
 * only the ones that are personally needed, as {@link RegistrySoilVariant} should never leave your own mod/addon's
 * control.
 */
public interface RegistrySoilVariant {

    /**
     * @return A block of this soil variant, of the provided type.
     */
    Supplier<? extends Block> getBlock(SoilBlockType type);

    /**
     * @return A dried mud brick item of this soil variant.
     */
    Supplier<? extends Item> getDriedMudBrick();
}
