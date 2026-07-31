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
        REGISTRATE.addRawLang("tooltip.svo_core.nekoite", "The delicate shards form a tiny pink meadow.");
        REGISTRATE.addRawLang("tooltip.svo_core.orpiment", "Despite its high toxicity, this Mineral is widely used in manufacturing and folk medicine.");
        REGISTRATE.addRawLang("tooltip.svo_core.petrified_slime", "This little guy may be 100,000 years old.");
        REGISTRATE.addRawLang("tooltip.svo_core.thunder_egg", "According to legend, angry thunder spirits would throw these stones at one another.");
        REGISTRATE.addRawLang("tooltip.svo_core.pyrite", "Commonly known as \"Fool's Gold\".");
        REGISTRATE.addRawLang("tooltip.svo_core.ocean_stone", "An old legend claims these stones are the mosaics of ancient mermaids.");
        REGISTRATE.addRawLang("tooltip.svo_core.ghost_crystal", "There is an aura of coldness around this crystal.");
        REGISTRATE.addRawLang("tooltip.svo_core.jasper", "When polished, this stone becomes attractively luminous. Prized by ancient peoples for thousands of years.");
        REGISTRATE.addRawLang("tooltip.svo_core.celestine", "Some early life forms had bones made from this.");
        REGISTRATE.addRawLang("tooltip.svo_core.marble", "A very popular material for sculptures and construction.");
        REGISTRATE.addRawLang("tooltip.svo_core.sandstone", "A common type of stone with red and brown striations.");
        REGISTRATE.addRawLang("tooltip.svo_core.granite", "A speckled Mineral that is commonly used in construction.");
        REGISTRATE.addRawLang("tooltip.svo_core.basalt", "Forms near searing hot magma.");
        REGISTRATE.addRawLang("tooltip.svo_core.limestone", "A very common type of stone. It's not worth very much.");
        REGISTRATE.addRawLang("tooltip.svo_core.soapstone", "Because of its relatively soft consistency, this stone is very popular for carving.");
        REGISTRATE.addRawLang("tooltip.svo_core.hermatite", "An iron-based Mineral with interesting magnetic properties.");
        REGISTRATE.addRawLang("tooltip.svo_core.mudstone", "A fine-grained rock made from ancient clay or mud.");
        REGISTRATE.addRawLang("tooltip.svo_core.obsidian", "A volcanic glass that forms when lava cools rapidly.");
        REGISTRATE.addRawLang("tooltip.svo_core.slate", "It's extremely resistant to water, making it a good roofing material.");
        REGISTRATE.addRawLang("tooltip.svo_core.fairy_stone", "An old miner's song suggests these are made from the bones of ancient fairies.");
        REGISTRATE.addRawLang("tooltip.svo_core.star_shards", "No one knows how these form. Some scientists claim that the microscopic structure displays unnatural regularity.");

        REGISTRATE.addRawLang("tooltip.svo_core.coins", "%s");
        REGISTRATE.addRawLang("tooltip.svo_core.mineral_product", "Mineral Product");
        REGISTRATE.addRawLang("tooltip.svo_core.gemstone_product", "Gemstone Product");
    }
}
