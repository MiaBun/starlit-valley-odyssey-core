package org.CuteNekoDragon.svo_core.common.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.CuteNekoDragon.svo_core.Main;
import org.CuteNekoDragon.svo_core.common.data.SvoItems;

public class SvoItemModelProvider extends ItemModelProvider {

    public SvoItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Main.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // For each registered tier, generate:
        // { "parent": "minecraft:item/generated", "textures": { "layer0": "starlit:item/smithing/<tier>" } }
        SvoItems.UPGRADE_TEMPLATES.forEach((tier, itemRegistryObject) -> {
            String path = itemRegistryObject.getId().getPath(); // e.g. "iron_upgrade_smithing_template"

            withExistingParent(path, "minecraft:item/generated")
                    .texture("layer0", modLoc("item/smithing/" + tier));
        });
    }
}