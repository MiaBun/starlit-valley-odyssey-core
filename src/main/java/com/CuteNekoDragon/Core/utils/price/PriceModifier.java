package com.CuteNekoDragon.Core.utils.price;

@FunctionalInterface
public interface PriceModifier {

    Adjustment get(PriceContext ctx);

    record Adjustment(double flat, double percent) {
        public static final Adjustment NONE = new Adjustment(0, 0);

        public static Adjustment flat(double amount) { return new Adjustment(amount, 0); }
        public static Adjustment percent(double fraction) { return new Adjustment(0, fraction); }
    }
}
