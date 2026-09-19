package com.CuteNekoDragon.Core.common.data.svogt;

import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.shapes.Shapes;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.common.data.SVOTags;
import com.CuteNekoDragon.Core.common.svogt.machine.singleblock.artisan.CharkoalKiln;
import com.CuteNekoDragon.Core.common.svogt.machine.singleblock.artisan.MailBox;
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
            .blockBuilder(p -> p.tag(SVOTags.Blocks.ARTISAN_MACHINES))
            .model(ModelUtils.createBasicMachineModel(SVOCore.id("block/machine/charkoal_kiln")))
            .itemBuilder(ib -> ib
                    .tag(SVOTags.Items.ARTISAN_MACHINES)
                    .model((ctx, prov) -> prov.generated(ctx::getEntry, SVOCore.id(("item/machine/charcoal_kiln")))))
            .register();

    public static final MachineDefinition OAK_MAIL_BOX = REGISTRATE
            .machine("oak_mail_box", MailBox::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .langValue("Oak Mailbox")
            .shape(Shapes.box(0, 0, 0, 1, 2, 1))
            .blockProp(BlockBehaviour.Properties::noOcclusion)
            .model(createBasicMachineModel(SVOCore.id("block/machine/mailbox/oak_mail_box_model")))
            .itemBuilder(p -> p.tag(SVOTags.Items.ARTISAN_MACHINES).tag(SVOTags.Items.MAIL_BOX))
            .blockBuilder(p -> p.tag(SVOTags.Blocks.ARTISAN_MACHINES).tag(SVOTags.Blocks.MAIL_BOX))
            .register();

    public static final MachineDefinition SPRUCE_MAIL_BOX = REGISTRATE
            .machine("spruce_mail_box", MailBox::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .langValue("Spruce Mailbox")
            .shape(Shapes.box(0, 0, 0, 1, 2, 1))
            .blockProp(BlockBehaviour.Properties::noOcclusion)
            .model(createBasicMachineModel(SVOCore.id("block/machine/mailbox/spruce_mail_box_model")))
            .itemBuilder(p -> p.tag(SVOTags.Items.ARTISAN_MACHINES).tag(SVOTags.Items.MAIL_BOX))
            .blockBuilder(p -> p.tag(SVOTags.Blocks.ARTISAN_MACHINES).tag(SVOTags.Blocks.MAIL_BOX))
            .register();

    public static final MachineDefinition BIRCH_MAIL_BOX = REGISTRATE
            .machine("birch_mail_box", MailBox::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .langValue("Birch Mailbox")
            .shape(Shapes.box(0, 0, 0, 1, 2, 1))
            .blockProp(BlockBehaviour.Properties::noOcclusion)
            .model(createBasicMachineModel(SVOCore.id("block/machine/mailbox/birch_mail_box_model")))
            .itemBuilder(p -> p.tag(SVOTags.Items.ARTISAN_MACHINES).tag(SVOTags.Items.MAIL_BOX))
            .blockBuilder(p -> p.tag(SVOTags.Blocks.ARTISAN_MACHINES).tag(SVOTags.Blocks.MAIL_BOX))
            .register();

    public static final MachineDefinition JUNGLE_MAIL_BOX = REGISTRATE
            .machine("jungle_mail_box", MailBox::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .langValue("Jungle Mailbox")
            .shape(Shapes.box(0, 0, 0, 1, 2, 1))
            .blockProp(BlockBehaviour.Properties::noOcclusion)
            .model(createBasicMachineModel(SVOCore.id("block/machine/mailbox/jungle_mail_box_model")))
            .itemBuilder(p -> p.tag(SVOTags.Items.ARTISAN_MACHINES).tag(SVOTags.Items.MAIL_BOX))
            .blockBuilder(p -> p.tag(SVOTags.Blocks.ARTISAN_MACHINES).tag(SVOTags.Blocks.MAIL_BOX))
            .register();

    public static final MachineDefinition ACACIA_MAIL_BOX = REGISTRATE
            .machine("acacia_mail_box", MailBox::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .langValue("Acacia Mail Box")
            .shape(Shapes.box(0, 0, 0, 1, 2, 1))
            .blockProp(BlockBehaviour.Properties::noOcclusion)
            .model(createBasicMachineModel(SVOCore.id("block/machine/mailbox/acacia_mail_box_model")))
            .itemBuilder(p -> p.tag(SVOTags.Items.ARTISAN_MACHINES).tag(SVOTags.Items.MAIL_BOX))
            .blockBuilder(p -> p.tag(SVOTags.Blocks.ARTISAN_MACHINES).tag(SVOTags.Blocks.MAIL_BOX))
            .register();

    public static final MachineDefinition DARK_OAK_MAIL_BOX = REGISTRATE
            .machine("dark_oak_mail_box", MailBox::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .langValue("Dark Oak Mailbox")
            .shape(Shapes.box(0, 0, 0, 1, 2, 1))
            .blockProp(BlockBehaviour.Properties::noOcclusion)
            .model(createBasicMachineModel(SVOCore.id("block/machine/mailbox/dark_oak_mail_box_model")))
            .itemBuilder(p -> p.tag(SVOTags.Items.ARTISAN_MACHINES).tag(SVOTags.Items.MAIL_BOX))
            .blockBuilder(p -> p.tag(SVOTags.Blocks.ARTISAN_MACHINES).tag(SVOTags.Blocks.MAIL_BOX))
            .register();

    public static final MachineDefinition MANGROVE_MAIL_BOX = REGISTRATE
            .machine("mangrove_mail_box", MailBox::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .langValue("Mangrove Mailbox")
            .shape(Shapes.box(0, 0, 0, 1, 2, 1))
            .blockProp(BlockBehaviour.Properties::noOcclusion)
            .model(createBasicMachineModel(SVOCore.id("block/machine/mailbox/mangrove_mail_box_model")))
            .itemBuilder(p -> p.tag(SVOTags.Items.ARTISAN_MACHINES).tag(SVOTags.Items.MAIL_BOX))
            .blockBuilder(p -> p.tag(SVOTags.Blocks.ARTISAN_MACHINES).tag(SVOTags.Blocks.MAIL_BOX))
            .register();

    public static final MachineDefinition CHERRY_MAIL_BOX = REGISTRATE
            .machine("cherry_mail_box", MailBox::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .langValue("Cherry Mailbox")
            .shape(Shapes.box(0, 0, 0, 1, 2, 1))
            .blockProp(BlockBehaviour.Properties::noOcclusion)
            .model(createBasicMachineModel(SVOCore.id("block/machine/mailbox/cherry_mail_box_model")))
            .itemBuilder(p -> p.tag(SVOTags.Items.ARTISAN_MACHINES).tag(SVOTags.Items.MAIL_BOX))
            .blockBuilder(p -> p.tag(SVOTags.Blocks.ARTISAN_MACHINES).tag(SVOTags.Blocks.MAIL_BOX))
            .register();

    public static final MachineDefinition BAMBOO_MAIL_BOX = REGISTRATE
            .machine("bamboo_mail_box", MailBox::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .langValue("Bamboo Mailbox")
            .shape(Shapes.box(0, 0, 0, 1, 2, 1))
            .blockProp(BlockBehaviour.Properties::noOcclusion)
            .model(createBasicMachineModel(SVOCore.id("block/machine/mailbox/bamboo_mail_box_model")))
            .itemBuilder(p -> p.tag(SVOTags.Items.ARTISAN_MACHINES).tag(SVOTags.Items.MAIL_BOX))
            .blockBuilder(p -> p.tag(SVOTags.Blocks.ARTISAN_MACHINES).tag(SVOTags.Blocks.MAIL_BOX))
            .register();

    public static final MachineDefinition CRIMSON_MAIL_BOX = REGISTRATE
            .machine("crimson_mail_box", MailBox::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .langValue("Crimson Mailbox")
            .shape(Shapes.box(0, 0, 0, 1, 2, 1))
            .blockProp(BlockBehaviour.Properties::noOcclusion)
            .model(createBasicMachineModel(SVOCore.id("block/machine/mailbox/crimson_mail_box_model")))
            .itemBuilder(p -> p.tag(SVOTags.Items.ARTISAN_MACHINES).tag(SVOTags.Items.MAIL_BOX))
            .blockBuilder(p -> p.tag(SVOTags.Blocks.ARTISAN_MACHINES).tag(SVOTags.Blocks.MAIL_BOX))
            .register();

    public static final MachineDefinition WARPED_MAIL_BOX = REGISTRATE
            .machine("warped_mail_box", MailBox::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .langValue("Warped Mailbox")
            .shape(Shapes.box(0, 0, 0, 1, 2, 1))
            .blockProp(BlockBehaviour.Properties::noOcclusion)
            .model(createBasicMachineModel(SVOCore.id("block/machine/mailbox/warped_mail_box_model")))
            .itemBuilder(p -> p.tag(SVOTags.Items.ARTISAN_MACHINES).tag(SVOTags.Items.MAIL_BOX))
            .blockBuilder(p -> p.tag(SVOTags.Blocks.ARTISAN_MACHINES).tag(SVOTags.Blocks.MAIL_BOX))
            .register();

    public static void init() {}
}
