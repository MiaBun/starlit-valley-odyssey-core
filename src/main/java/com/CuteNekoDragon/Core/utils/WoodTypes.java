package com.CuteNekoDragon.Core.utils;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import com.CuteNekoDragon.Core.common.data.svogt.SVOMachines;
import lombok.Getter;

public enum WoodTypes {

    OAK(Items.OAK_LOG, Items.OAK_PLANKS, Items.OAK_SIGN, Items.OAK_FENCE_GATE, Items.OAK_FENCE, Items.OAK_BUTTON,
            Items.OAK_DOOR, Items.OAK_PRESSURE_PLATE, Items.OAK_STAIRS, Items.OAK_TRAPDOOR, Items.OAK_HANGING_SIGN,
            Items.OAK_LEAVES, SVOMachines.OAK_MAIL_BOX.getItem()),

    SPRUCE(Items.SPRUCE_LOG, Items.SPRUCE_PLANKS, Items.SPRUCE_SIGN, Items.SPRUCE_FENCE_GATE, Items.SPRUCE_FENCE,
            Items.SPRUCE_BUTTON, Items.SPRUCE_DOOR, Items.SPRUCE_PRESSURE_PLATE, Items.SPRUCE_STAIRS,
            Items.SPRUCE_TRAPDOOR, Items.SPRUCE_HANGING_SIGN, Items.SPRUCE_LEAVES,
            SVOMachines.SPRUCE_MAIL_BOX.getItem()),

    BIRCH(Items.BIRCH_LOG, Items.BIRCH_PLANKS, Items.BIRCH_SIGN, Items.BIRCH_FENCE_GATE, Items.BIRCH_FENCE,
            Items.BIRCH_BUTTON, Items.BIRCH_DOOR, Items.BIRCH_PRESSURE_PLATE, Items.BIRCH_STAIRS, Items.BIRCH_TRAPDOOR,
            Items.BIRCH_HANGING_SIGN, Items.BIRCH_LEAVES, SVOMachines.BIRCH_MAIL_BOX.getItem()),

    JUNGLE(Items.JUNGLE_LOG, Items.JUNGLE_PLANKS, Items.JUNGLE_SIGN, Items.JUNGLE_FENCE_GATE, Items.JUNGLE_FENCE,
            Items.JUNGLE_BUTTON, Items.JUNGLE_DOOR, Items.JUNGLE_PRESSURE_PLATE, Items.JUNGLE_STAIRS,
            Items.JUNGLE_TRAPDOOR, Items.JUNGLE_HANGING_SIGN, Items.JUNGLE_LEAVES,
            SVOMachines.JUNGLE_MAIL_BOX.getItem()),

    ACACIA(Items.ACACIA_LOG, Items.ACACIA_PLANKS, Items.ACACIA_SIGN, Items.ACACIA_FENCE_GATE, Items.ACACIA_FENCE,
            Items.ACACIA_BUTTON, Items.ACACIA_DOOR, Items.ACACIA_PRESSURE_PLATE, Items.ACACIA_STAIRS,
            Items.ACACIA_TRAPDOOR, Items.ACACIA_HANGING_SIGN, Items.ACACIA_LEAVES,
            SVOMachines.ACACIA_MAIL_BOX.getItem()),

    DARK_OAK(Items.DARK_OAK_LOG, Items.DARK_OAK_PLANKS, Items.DARK_OAK_SIGN, Items.DARK_OAK_FENCE_GATE,
            Items.DARK_OAK_FENCE, Items.DARK_OAK_BUTTON, Items.DARK_OAK_DOOR, Items.DARK_OAK_PRESSURE_PLATE,
            Items.DARK_OAK_STAIRS, Items.DARK_OAK_TRAPDOOR, Items.DARK_OAK_HANGING_SIGN, Items.DARK_OAK_LEAVES,
            SVOMachines.DARK_OAK_MAIL_BOX.getItem()),

    MANGROVE(Items.MANGROVE_LOG, Items.MANGROVE_PLANKS, Items.MANGROVE_SIGN, Items.MANGROVE_FENCE_GATE,
            Items.MANGROVE_FENCE, Items.MANGROVE_BUTTON, Items.MANGROVE_DOOR, Items.MANGROVE_PRESSURE_PLATE,
            Items.MANGROVE_STAIRS, Items.MANGROVE_TRAPDOOR, Items.MANGROVE_HANGING_SIGN, Items.MANGROVE_LEAVES,
            SVOMachines.MANGROVE_MAIL_BOX.getItem()),

    CHERRY(Items.CHERRY_LOG, Items.CHERRY_PLANKS, Items.CHERRY_SIGN, Items.CHERRY_FENCE_GATE, Items.CHERRY_FENCE,
            Items.CHERRY_BUTTON, Items.CHERRY_DOOR, Items.CHERRY_PRESSURE_PLATE, Items.CHERRY_STAIRS,
            Items.CHERRY_TRAPDOOR, Items.CHERRY_HANGING_SIGN, Items.CHERRY_LEAVES,
            SVOMachines.CHERRY_MAIL_BOX.getItem()),

    BAMBOO(Items.BAMBOO_BLOCK, Items.BAMBOO_PLANKS, Items.BAMBOO_SIGN, Items.BAMBOO_FENCE_GATE, Items.BAMBOO_FENCE,
            Items.BAMBOO_BUTTON, Items.BAMBOO_DOOR, Items.BAMBOO_PRESSURE_PLATE, Items.BAMBOO_STAIRS,
            Items.BAMBOO_TRAPDOOR, Items.BAMBOO_HANGING_SIGN, Items.BAMBOO, SVOMachines.BAMBOO_MAIL_BOX.getItem()),

    CRIMSON(Items.CRIMSON_STEM, Items.CRIMSON_PLANKS, Items.CRIMSON_SIGN, Items.CRIMSON_FENCE_GATE, Items.CRIMSON_FENCE,
            Items.CRIMSON_BUTTON, Items.CRIMSON_DOOR, Items.CRIMSON_PRESSURE_PLATE, Items.CRIMSON_STAIRS,
            Items.CRIMSON_TRAPDOOR, Items.CRIMSON_HANGING_SIGN, Items.CRIMSON_ROOTS,
            SVOMachines.CRIMSON_MAIL_BOX.getItem()),

    WARPED(Items.WARPED_STEM, Items.WARPED_PLANKS, Items.WARPED_SIGN, Items.WARPED_FENCE_GATE, Items.WARPED_FENCE,
            Items.WARPED_BUTTON, Items.WARPED_DOOR, Items.WARPED_PRESSURE_PLATE, Items.WARPED_STAIRS,
            Items.WARPED_TRAPDOOR, Items.WARPED_HANGING_SIGN, Items.WARPED_ROOTS,
            SVOMachines.WARPED_MAIL_BOX.getItem());

    @Getter
    private final Item log;
    @Getter
    private final Item planks;
    @Getter
    private final Item sign;
    @Getter
    private final Item fence_gate;
    @Getter
    private final Item fence;
    @Getter
    private final Item button;
    @Getter
    private final Item door;
    @Getter
    private final Item preassure_plate;
    @Getter
    private final Item stairs;
    @Getter
    private final Item trapdoor;
    @Getter
    private final Item hanging_sign;
    @Getter
    private final Item leaves;
    @Getter
    private final Item mail_box;

    WoodTypes(Item log, Item planks, Item sign, Item fenceGate, Item fence, Item button, Item door, Item preassurePlate,
              Item stairs, Item trapdoor, Item hangingSign, Item leaves, Item mailBox) {
        this.log = log;
        this.planks = planks;
        this.sign = sign;
        fence_gate = fenceGate;
        this.fence = fence;
        this.button = button;
        this.door = door;
        preassure_plate = preassurePlate;
        this.stairs = stairs;
        this.trapdoor = trapdoor;
        hanging_sign = hangingSign;
        this.leaves = leaves;
        mail_box = mailBox;
    }
}
