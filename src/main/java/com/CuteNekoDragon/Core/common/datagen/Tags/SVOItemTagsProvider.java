package com.CuteNekoDragon.Core.common.datagen.Tags;

import com.CuteNekoDragon.Core.common.datagen.GlobalDatagenArrays;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import com.CuteNekoDragon.Core.common.data.SVOTags;
import com.tterrag.registrate.providers.ProviderType;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;

import static com.CuteNekoDragon.Core.SVOCore.REGISTRATE;

public class SVOItemTagsProvider {

    public static void gatherData() {
        REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, prov -> {
            prov.addTag(SVOTags.Items.HIDDEN_FROM_RECIPE_VIEWERS)
                    .add(Items.NETHERITE_AXE)
                    .add(Items.NETHERITE_BOOTS)
                    .add(Items.NETHERITE_HOE)
                    .add(Items.NETHERITE_HELMET)
                    .add(Items.NETHERITE_CHESTPLATE)
                    .add(Items.NETHERITE_LEGGINGS)
                    .add(Items.NETHERITE_PICKAXE)
                    .add(Items.NETHERITE_SHOVEL)
                    .add(Items.NETHERITE_SWORD);

        });

        for (Item item : GlobalDatagenArrays.RemovedItems) {
            REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, prov -> {
                prov.addTag(SVOTags.Items.HIDDEN_FROM_RECIPE_VIEWERS)
                        .add(item);
            });
        }

        // for (Item item : ForgeRegistries.ITEMS) {

        // REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, prov -> {
        // prov.addTag(SVOTags.Items.GARBAGE).add(item);
        // });
        // }
    }
}
