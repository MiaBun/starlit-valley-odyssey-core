package com.CuteNekoDragon.Core.utils.tooltips.rarity.border;

import com.CuteNekoDragon.Core.utils.tooltips.render.TooltipBounds;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public record LayeredBorder(List<BorderRenderer> layers) implements BorderRenderer {
    public static LayeredBorder fromJson(JsonObject json) {
        List<BorderRenderer> layers = new ArrayList<>();
        for (JsonElement el : GsonHelper.getAsJsonArray(json, "layers")) {
            layers.add(BorderTypes.parse(el.getAsJsonObject()));
        }
        return new LayeredBorder(List.copyOf(layers));
    }

    @Override
    public void render(GuiGraphics g, TooltipBounds b, ItemStack stack, float time) {
        for (BorderRenderer layer : layers) layer.render(g, b, stack, time);
    }

    @Override
    public BorderRenderer then(BorderRenderer next) {
        List<BorderRenderer> list = new ArrayList<>(layers);
        list.add(next);
        return new LayeredBorder(List.copyOf(list));
    }
}
