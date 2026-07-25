package com.CuteNekoDragon.Core.common.data;

import com.CuteNekoDragon.Core.utils.PriceUtil;

public class SVOItemPrices {

    public static void ProvidePrices() {
        PriceUtil.setPrice(SVOItems.QUARTZ, 25);
        PriceUtil.setPrice(SVOItems.EARTH_CRYSTAL, 50);
        PriceUtil.setPrice(SVOItems.FROZEN_TEAR, 75);
        PriceUtil.setPrice(SVOItems.FIRE_QUARTZ, 100);
    }
}
