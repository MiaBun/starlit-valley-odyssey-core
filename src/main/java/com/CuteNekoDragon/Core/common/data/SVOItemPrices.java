package com.CuteNekoDragon.Core.common.data;

import com.CuteNekoDragon.Core.utils.PriceUtil;

public class SVOItemPrices {

    public static void ProvidePrices() {

        //Foraged Minerals
        PriceUtil.setPrice(SVOItems.QUARTZ, 25, PriceUtil.PriceType.GEMOLOGIST);
        PriceUtil.setPrice(SVOItems.EARTH_CRYSTAL, 50, PriceUtil.PriceType.GEMOLOGIST);
        PriceUtil.setPrice(SVOItems.FROZEN_TEAR, 75, PriceUtil.PriceType.GEMOLOGIST);
        PriceUtil.setPrice(SVOItems.FIRE_QUARTZ, 100, PriceUtil.PriceType.GEMOLOGIST);

        //Gemstones
        PriceUtil.setPrice(SVOItems.EMERALD, 250, PriceUtil.PriceType.GEMOLOGIST);
    }
}
