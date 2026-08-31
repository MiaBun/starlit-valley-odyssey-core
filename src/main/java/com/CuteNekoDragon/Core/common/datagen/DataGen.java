package com.CuteNekoDragon.Core.common.datagen;

import com.CuteNekoDragon.Core.integration.forks.terrafirmacraft.TFC;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.common.datagen.Tags.SVOItemTagsProvider;
import com.CuteNekoDragon.Core.common.datagen.advancements.SVOAdvancementsProvider;
import com.CuteNekoDragon.Core.common.datagen.lang.SVOLangProvider;
import com.CuteNekoDragon.Core.common.datagen.recipes.SVORecipeProvider;

import java.util.concurrent.CompletableFuture;

import static com.CuteNekoDragon.Core.SVOCore.REGISTRATE;

@Mod.EventBusSubscriber(modid = SVOCore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGen {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        REGISTRATE.addLang("creative_tab", ResourceLocation.fromNamespaceAndPath(SVOCore.MOD_ID, "svo"),
                "Starlit Valley: Odyssey");

        REGISTRATE.addLang("creative_tab", ResourceLocation.fromNamespaceAndPath(TFC.MOD_ID, "tfc"),
                "TerraFirmaCraft - Stardew Fork");

        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(),
                new SVOAdvancementsProvider(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new SVORecipeProvider(packOutput));

        SVOItemTagsProvider.gatherData();
        SVOLangProvider.gatherData();
    }
}
