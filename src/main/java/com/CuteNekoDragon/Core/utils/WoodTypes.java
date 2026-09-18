package com.CuteNekoDragon.Core.utils;

import com.CuteNekoDragon.Core.common.data.svogt.SVOMachines;
import lombok.Getter;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public enum WoodTypes {

    OAK(Items.OAK_LOG, Items.OAK_PLANKS, Items.OAK_SIGN, Items.OAK_FENCE_GATE, Items.OAK_FENCE, Items.OAK_BUTTON, Items.OAK_DOOR, Items.OAK_PRESSURE_PLATE, Items.OAK_STAIRS, Items.OAK_TRAPDOOR, Items.OAK_HANGING_SIGN, Items.OAK_LEAVES, SVOMachines.OAK_MAIL_BOX.getItem()),
    SPRUCE(Items.SPRUCE_LOG, Items.SPRUCE_PLANKS, Items.SPRUCE_SIGN, Items.SPRUCE_FENCE_GATE, Items.SPRUCE_FENCE, Items.SPRUCE_BUTTON, Items.SPRUCE_DOOR, Items.SPRUCE_PRESSURE_PLATE, Items.SPRUCE_STAIRS, Items.SPRUCE_TRAPDOOR, Items.SPRUCE_HANGING_SIGN, Items.SPRUCE_LEAVES, SVOMachines.SPRUCE_MAIL_BOX.getItem());

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

    WoodTypes(Item log, Item planks, Item sign, Item fenceGate, Item fence, Item button, Item door, Item preassurePlate, Item stairs, Item trapdoor, Item hangingSign, Item leaves, Item mailBox) {
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
