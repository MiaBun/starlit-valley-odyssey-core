package com.CuteNekoDragon.Core.utils.tooltips.value;

import com.CuteNekoDragon.Core.utils.tooltips.util.TextUtil;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public record TooltipValue(double amount, List<DetailLine> details) {

    public record DetailLine(Component text, @Nullable Double value) {}

    public static TooltipValue of(double amount) {
        return new TooltipValue(amount, List.of());
    }

    public static Builder builder(double amount) {
        return new Builder(amount);
    }

    public static final class Builder {
        private final double amount;
        private final List<DetailLine> details = new ArrayList<>();

        private Builder(double amount) {
            this.amount = amount;
        }

        public Builder line(Component label, double value) {
            details.add(new DetailLine(label, value));
            return this;
        }

        public Builder line(String label, double value) {
            return line(TextUtil.keyOrLiteral(label), value);
        }

        public Builder note(Component text) {
            details.add(new DetailLine(text, null));
            return this;
        }

        public Builder note(String keyOrText) {
            return note(TextUtil.keyOrLiteral(keyOrText));
        }

        public TooltipValue build() {
            return new TooltipValue(amount, List.copyOf(details));
        }
    }
}
