package com.CuteNekoDragon.Core.common.data;

import com.CuteNekoDragon.Core.utils.PriceUtil;

public class SVOItemPrices {

    public static void ProvidePrices() {
        // Foraged Minerals
        PriceUtil.setPrice(SVOItems.QUARTZ, 25, PriceUtil.PriceType.GEMOLOGIST);
        PriceUtil.setPrice(SVOItems.EARTH_CRYSTAL, 50, PriceUtil.PriceType.GEMOLOGIST);
        PriceUtil.setPrice(SVOItems.FROZEN_TEAR, 75, PriceUtil.PriceType.GEMOLOGIST);
        PriceUtil.setPrice(SVOItems.FIRE_QUARTZ, 100, PriceUtil.PriceType.GEMOLOGIST);

        // Gemstones
        PriceUtil.setPrice(SVOItems.EMERALD, 250, PriceUtil.PriceType.GEMOLOGIST);
        PriceUtil.setPrice(SVOItems.AQUAMARINE, 180, PriceUtil.PriceType.GEMOLOGIST);
        PriceUtil.setPrice(SVOItems.RUBY, 250, PriceUtil.PriceType.GEMOLOGIST);
        PriceUtil.setPrice(SVOItems.AMETHYST, 100, PriceUtil.PriceType.GEMOLOGIST);
        PriceUtil.setPrice(SVOItems.TOPAZ, 80, PriceUtil.PriceType.GEMOLOGIST);
        PriceUtil.setPrice(SVOItems.JADE, 200, PriceUtil.PriceType.GEMOLOGIST);
        PriceUtil.setPrice(SVOItems.DIAMOND, 750, PriceUtil.PriceType.GEMOLOGIST);
        PriceUtil.setPrice(SVOItems.PRISMATIC_SHARD, 2000, PriceUtil.PriceType.GEMOLOGIST);

        // Geode Minerals
        PriceUtil.setPrice(SVOItems.TIGERSEYE, 275, PriceUtil.PriceType.GEMOLOGIST);
        PriceUtil.setPrice(SVOItems.OPAL, 150, PriceUtil.PriceType.GEMOLOGIST);
        PriceUtil.setPrice(SVOItems.FIRE_OPAL, 350, PriceUtil.PriceType.GEMOLOGIST);
        PriceUtil.setPrice(SVOItems.ALAMITE, 150, PriceUtil.PriceType.GEMOLOGIST);
        PriceUtil.setPrice(SVOItems.BIXITE, 300, PriceUtil.PriceType.GEMOLOGIST);
    }
}
