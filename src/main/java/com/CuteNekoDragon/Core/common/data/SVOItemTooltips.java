package com.CuteNekoDragon.Core.common.data;

import com.CuteNekoDragon.Core.common.data.items.SVOItems;
import com.CuteNekoDragon.Core.common.data.svogt.SVOMachines;
import com.CuteNekoDragon.Core.common.datagen.lang.tooltips.TooltipLangProvider;
import com.CuteNekoDragon.Core.utils.TooltipBuilder;

public class SVOItemTooltips {

    public static void ProvideTooltips() {
        // Foraged Minerals

        TooltipBuilder.addTooltip(SVOItems.EARTH_CRYSTAL).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.EARTH_CRYSTAL.key());
        TooltipBuilder.addTooltip(SVOItems.FROZEN_TEAR).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.FROZEN_TEAR.key());
        TooltipBuilder.addTooltip(SVOItems.FIRE_QUARTZ).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.FIRE_QUARTZ.key());

        // Gemstones
        TooltipBuilder.addTooltip(SVOItems.EMERALD).addCoins().addGlyph(TooltipBuilder.SVOTypes.GEMSTONE)
                .addInfo(TooltipLangProvider.EMERALD.key());
        TooltipBuilder.addTooltip(SVOItems.AQUAMARINE).addCoins().addGlyph(TooltipBuilder.SVOTypes.GEMSTONE)
                .addInfo(TooltipLangProvider.AQUAMARINE.key());
        TooltipBuilder.addTooltip(SVOItems.RUBY).addCoins().addGlyph(TooltipBuilder.SVOTypes.GEMSTONE)
                .addInfo(TooltipLangProvider.RUBY.key());
        TooltipBuilder.addTooltip(SVOItems.AMETHYST).addCoins().addGlyph(TooltipBuilder.SVOTypes.GEMSTONE)
                .addInfo(TooltipLangProvider.AMETHYST.key());
        TooltipBuilder.addTooltip(SVOItems.TOPAZ).addCoins().addGlyph(TooltipBuilder.SVOTypes.GEMSTONE)
                .addInfo(TooltipLangProvider.TOPAZ.key());
        TooltipBuilder.addTooltip(SVOItems.JADE).addCoins().addGlyph(TooltipBuilder.SVOTypes.GEMSTONE)
                .addInfo(TooltipLangProvider.JADE.key());
        TooltipBuilder.addTooltip(SVOItems.DIAMOND).addCoins().addGlyph(TooltipBuilder.SVOTypes.GEMSTONE)
                .addInfo(TooltipLangProvider.DIAMOND.key());
        TooltipBuilder.addTooltip(SVOItems.PRISMATIC_SHARD).addCoins().addGlyph(TooltipBuilder.SVOTypes.GEMSTONE)
                .addInfo(TooltipLangProvider.PRISMATIC_SHARD.key());

        // Geode Minerals
        TooltipBuilder.addTooltip(SVOItems.TIGERSEYE).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.TIGERSEYE.key());
        TooltipBuilder.addTooltip(SVOItems.OPAL).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.OPAL.key());
        TooltipBuilder.addTooltip(SVOItems.FIRE_OPAL).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.FIRE_OPAL.key());
        TooltipBuilder.addTooltip(SVOItems.ALAMITE).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.ALAMITE.key());
        TooltipBuilder.addTooltip(SVOItems.BIXITE).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.BIXITE.key());
        TooltipBuilder.addTooltip(SVOItems.BARYTE).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.BARYTE.key());
        TooltipBuilder.addTooltip(SVOItems.AERINITE).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.AERINITE.key());
        TooltipBuilder.addTooltip(SVOItems.CAlCITE).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.CALCITE.key());
        TooltipBuilder.addTooltip(SVOItems.DOLOMITE).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.DOLOMITE.key());
        TooltipBuilder.addTooltip(SVOItems.ESPERITE).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.ESPERITE.key());
        TooltipBuilder.addTooltip(SVOItems.FLUORAPATITE).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.FLUORAPATITE.key());
        TooltipBuilder.addTooltip(SVOItems.GEMINITE).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.GEMINITE.key());
        TooltipBuilder.addTooltip(SVOItems.HELVITE).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.HELVITE.key());
        TooltipBuilder.addTooltip(SVOItems.JAMBORITE).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.JAMBORITE.key());
        TooltipBuilder.addTooltip(SVOItems.JAGOITE).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.JAGOITE.key());
        TooltipBuilder.addTooltip(SVOItems.KYANITE).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.KYANITE.key());
        TooltipBuilder.addTooltip(SVOItems.LUNARITE).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.LUNARITE.key());
        TooltipBuilder.addTooltip(SVOItems.MALACHITE).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.MALACHITE.key());
        TooltipBuilder.addTooltip(SVOItems.NEPTUNITE).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.NEPTUNITE.key());
        TooltipBuilder.addTooltip(SVOItems.LEMON_STONE).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.LEMON_STONE.key());
        TooltipBuilder.addTooltip(SVOItems.NEKOITE).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.NEKOITE.key());
        TooltipBuilder.addTooltip(SVOItems.ORPIMENT).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.ORPIMENT.key());
        TooltipBuilder.addTooltip(SVOItems.PETRIFIED_SLIME).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.PETRIFIED_SLIME.key());
        TooltipBuilder.addTooltip(SVOItems.THUNDER_EGG).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.THUNDER_EGG.key());
        TooltipBuilder.addTooltip(SVOItems.PYRITE).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.PYRITE.key());
        TooltipBuilder.addTooltip(SVOItems.OCEAN_STONE).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.OCEAN_STONE.key());
        TooltipBuilder.addTooltip(SVOItems.GHOST_CRYSTAL).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.GHOST_CRYSTAL.key());
        TooltipBuilder.addTooltip(SVOItems.JASPER).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.JASPER.key());
        TooltipBuilder.addTooltip(SVOItems.CELESTINE).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.CELESTINE.key());
        TooltipBuilder.addTooltip(SVOItems.MARBLE).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.MARBLE.key());
        TooltipBuilder.addTooltip(SVOItems.SANDSTONE).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.SANDSTONE.key());
        TooltipBuilder.addTooltip(SVOItems.GRANITE).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.GRANITE.key());
        TooltipBuilder.addTooltip(SVOItems.BASALT).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.BASALT.key());
        TooltipBuilder.addTooltip(SVOItems.LIMESTONE).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.LIMESTONE.key());
        TooltipBuilder.addTooltip(SVOItems.SOAPSTONE).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.SOAPSTONE.key());
        TooltipBuilder.addTooltip(SVOItems.HERMATITE).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.HERMATITE.key());
        TooltipBuilder.addTooltip(SVOItems.MUDSTONE).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.MUDSTONE.key());
        TooltipBuilder.addTooltip(SVOItems.OBSIDIAN).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.OBSIDIAN.key());
        TooltipBuilder.addTooltip(SVOItems.SLATE).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.SLATE.key());
        TooltipBuilder.addTooltip(SVOItems.FAIRY_STONE).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.FAIRY_STONE.key());
        TooltipBuilder.addTooltip(SVOItems.STAR_SHARDS).addCoins().addGlyph(TooltipBuilder.SVOTypes.MINERAL)
                .addInfo(TooltipLangProvider.STAR_SHARDS.key());

        // Special Items
        TooltipBuilder.addTooltip(SVOItems.GALAXY_SOUL).addCoins().addGlyph(TooltipBuilder.SVOTypes.SPECIAL_ITEM)
                .addInfo(TooltipLangProvider.GALAXY_SOUL.key());
        TooltipBuilder.addTooltip(SVOItems.DRAGON_TOOTH).addCoins().addGlyph(TooltipBuilder.SVOTypes.SPECIAL_ITEM)
                .addInfo(TooltipLangProvider.DRAGON_TOOTH.key());
        TooltipBuilder.addTooltip(SVOItems.CINDER_SHARD).addCoins().addGlyph(TooltipBuilder.SVOTypes.SPECIAL_ITEM)
                .addInfo(TooltipLangProvider.CINDER_SHARD.key());

        // Ingots and Blacksmith Items
        TooltipBuilder.addTooltip(SVOItems.IRIDIUM_INGOT).addCoins().addGlyph(TooltipBuilder.SVOTypes.BLACKSMITH_ITEM)
                .addInfo(TooltipLangProvider.IRIDIUM_INGOT.key());
        TooltipBuilder.addTooltip(SVOItems.REFINED_QUARTZ).addCoins().addGlyph(TooltipBuilder.SVOTypes.BLACKSMITH_ITEM)
                .addInfo(TooltipLangProvider.REFINED_QUARTZ.key());
        TooltipBuilder.addTooltip(SVOItems.RADIOACTIVE_INGOT).addCoins()
                .addGlyph(TooltipBuilder.SVOTypes.BLACKSMITH_ITEM)
                .addInfo(TooltipLangProvider.RADIOACTIVE_INGOT.key());

        // Artisan Machines
        TooltipBuilder.addTooltip(SVOMachines.CHARKOAL_KILN.getItem())
                .addInfo(TooltipLangProvider.CHARCOAL_KILN.key());
    }
}
