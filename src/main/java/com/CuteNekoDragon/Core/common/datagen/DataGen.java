package com.CuteNekoDragon.Core.common.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.CuteNekoDragon.Core.SVOCore;

import static com.CuteNekoDragon.Core.SVOCore.REGISTRATE;

@Mod.EventBusSubscriber(modid = SVOCore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGen {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        REGISTRATE.addLang("creative_tab", ResourceLocation.fromNamespaceAndPath(SVOCore.MOD_ID, "svo"),
                "Starlit Valley: Odyssey");

        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
    }
}
