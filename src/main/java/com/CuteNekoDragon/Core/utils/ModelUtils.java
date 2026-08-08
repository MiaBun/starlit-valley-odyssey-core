package com.CuteNekoDragon.Core.utils;

import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.registry.registrate.MachineBuilder;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;

import static com.gregtechceu.gtceu.api.machine.property.GTMachineModelProperties.RECIPE_LOGIC_STATUS;

public class ModelUtils {

    public static MachineBuilder.ModelInitializer createBasicMachineModel(ResourceLocation baseModel) {
        return (ctx, prov, builder) -> {
            ModelFile.ExistingModelFile model = prov.models().getExistingFile(baseModel);
            ModelFile.ExistingModelFile modelWorking = prov.models()
                    .getExistingFile(ResourceLocation.parse(baseModel + "_working"));
            builder.partialState()
                    .with(RECIPE_LOGIC_STATUS, RecipeLogic.Status.WORKING)
                    .setModels(ConfiguredModel.builder().modelFile(modelWorking).build());
            builder.partialState()
                    .with(RECIPE_LOGIC_STATUS, RecipeLogic.Status.IDLE)
                    .setModels(ConfiguredModel.builder().modelFile(model).build());
            builder.partialState()
                    .with(RECIPE_LOGIC_STATUS, RecipeLogic.Status.SUSPEND)
                    .setModels(ConfiguredModel.builder().modelFile(model).build());
            builder.partialState()
                    .with(RECIPE_LOGIC_STATUS, RecipeLogic.Status.WAITING)
                    .setModels(ConfiguredModel.builder().modelFile(model).build());
        };
    }
}
