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

        REGISTRATE.addRawLang("tooltip.svo_core.quartz", "A clear crystal commonly found in caves and mines.");
        REGISTRATE.addRawLang("tooltip.svo_core.earth_crystal", "A resinous substance found near the surface.");
        REGISTRATE.addRawLang("tooltip.svo_core.frozen_tear", "A crystal fabled to be the frozen tears of a yeti.");
        REGISTRATE.addRawLang("tooltip.svo_core.fire_quartz", "A glowing red crystal commonly found near hot lava.");
        REGISTRATE.addRawLang("tooltip.svo_core.emerald", "A precious stone with a brilliant green color.");

        REGISTRATE.addRawLang("tooltip.svo_core.coins", "%s");
        REGISTRATE.addRawLang("tooltip.svo_core.mineral_product", "Mineral Product");
        REGISTRATE.addRawLang("tooltip.svo_core.gemstone_product", "Gemstone Product");
    }
}
