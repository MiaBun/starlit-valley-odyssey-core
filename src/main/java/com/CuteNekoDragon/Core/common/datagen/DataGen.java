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
        REGISTRATE.addRawLang("tooltip.svo_core.aquamarine", "A shimmery blue-green gem.");
        REGISTRATE.addRawLang("tooltip.svo_core.ruby",
                "A precious stone that is sought after for its rich color and beautiful luster.");
        REGISTRATE.addRawLang("tooltip.svo_core.amethyst", "A purple variant of quartz.");
        REGISTRATE.addRawLang("tooltip.svo_core.topaz", "Fairly common but still prized for its beauty.");
        REGISTRATE.addRawLang("tooltip.svo_core.jade", "A pale green ornamental stone.");
        REGISTRATE.addRawLang("tooltip.svo_core.diamond", "A rare and valuable gem.");
        REGISTRATE.addRawLang("tooltip.svo_core.prismatic_shard",
                "A very rare and powerful substance with unknown origins.");
        REGISTRATE.addRawLang("tooltip.svo_core.tigerseye",
                "A stripe of shimmering gold gives this gem a warm luster.");
        REGISTRATE.addRawLang("tooltip.svo_core.opal",
                "Its internal structure causes it to reflect a rainbow of light.");
        REGISTRATE.addRawLang("tooltip.svo_core.fire_opal", "A rare variety of opal, named for its red spots.");
        REGISTRATE.addRawLang("tooltip.svo_core.alamite",
                "Its distinctive fluorescence makes it a favorite among rock collectors.");
        REGISTRATE.addRawLang("tooltip.svo_core.bixite",
                "A dark metallic Mineral sought after for its cubic structure.");
        REGISTRATE.addRawLang("tooltip.svo_core.baryte", "The best specimens resemble a desert rose.");
        REGISTRATE.addRawLang("tooltip.svo_core.aerinite", "These crystals are curiously light.");
        REGISTRATE.addRawLang("tooltip.svo_core.calcite", "This yellow crystal is speckled with shimmering nodules.");
        REGISTRATE.addRawLang("tooltip.svo_core.dolomite",
                "It can occur in coral reefs, often near an underwater volcano.");
        REGISTRATE.addRawLang("tooltip.svo_core.esperite", "The crystals glow bright green when stimulated.");
        REGISTRATE.addRawLang("tooltip.svo_core.fluorapatite", "Small amounts are found in human teeth.");
        REGISTRATE.addRawLang("tooltip.svo_core.geminite", "Occurs in brilliant clusters.");
        REGISTRATE.addRawLang("tooltip.svo_core.helvite", "It grows in a triangular column.");
        REGISTRATE.addRawLang("tooltip.svo_core.jamborite", "The crystals are so tightly packed it almost looks fuzzy.");
        REGISTRATE.addRawLang("tooltip.svo_core.jagoite", "A high volume of tiny crystals makes it very glittery.");
        REGISTRATE.addRawLang("tooltip.svo_core.kyanite", "The geometric faces are as smooth as glass.");
        REGISTRATE.addRawLang("tooltip.svo_core.lunarite", "The cratered white orbs form a tight cluster.");
        REGISTRATE.addRawLang("tooltip.svo_core.malachite", "A popular ornamental stone, used in sculpture and to make green paint.");
        REGISTRATE.addRawLang("tooltip.svo_core.neptunite", "A jet-black crystal that is unusually reflective.");
        REGISTRATE.addRawLang("tooltip.svo_core.lemon_stone", "Some claim the powdered crystal is a dwarvish delicacy.");

        REGISTRATE.addRawLang("tooltip.svo_core.coins", "%s");
        REGISTRATE.addRawLang("tooltip.svo_core.mineral_product", "Mineral Product");
        REGISTRATE.addRawLang("tooltip.svo_core.gemstone_product", "Gemstone Product");
    }
}
