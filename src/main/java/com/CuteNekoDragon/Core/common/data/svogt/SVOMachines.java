package com.CuteNekoDragon.Core.common.data.svogt;

import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.shapes.Shapes;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.common.svogt.machine.singleblock.artisan.CharkoalKiln;
import com.CuteNekoDragon.Core.utils.ModelUtils;

import static com.CuteNekoDragon.Core.SVOCore.REGISTRATE;
import static com.gregtechceu.gtceu.common.data.models.GTMachineModels.createBasicMachineModel;

public class SVOMachines {

    public static final MachineDefinition CHARKOAL_KILN = REGISTRATE
            .machine("charcoal_kiln", CharkoalKiln::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(SVOGTRecipeTypes.CHARKOAL_KILN_RECIPES)
            .langValue("Charcoal Kiln")
            .shape(Shapes.block())
            .blockProp(BlockBehaviour.Properties::noOcclusion)
            .model(ModelUtils.createBasicMachineModel(SVOCore.id("block/machine/charkoal_kiln")))
            .itemBuilder(ib -> ib
                    .model((ctx, prov) -> prov.generated(ctx::getEntry, SVOCore.id(("item/machine/charcoal_kiln")))))
            .register();

    public static void init() {}
}
