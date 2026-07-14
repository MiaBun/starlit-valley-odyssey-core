package org.CuteNekoDragon.svo_core.common.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import org.CuteNekoDragon.svo_core.Main;
import org.CuteNekoDragon.svo_core.common.data.SvoItems;

import java.util.Locale;
import java.util.Map;

public class SvoLanguageProvider extends LanguageProvider {

    private static final Map<String, String> TIER_LABELS = Map.of(
            "iron", "Iron",
            "gold", "Gold",
            "diamond", "Diamond"
    );

    public SvoLanguageProvider (PackOutput output) {
        super(output, Main.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {

        add("itemGroup." + Main.MOD_ID, "Svo Core");

        SvoItems.UPGRADE_TEMPLATES.forEach((tier, itemRegistryObject) -> {
            String label = TIER_LABELS.getOrDefault(tier, capitalize(tier));
            String ingredientWord = tier.equals("diamond") ? label + "s" : label + " Ingots";

            add(itemRegistryObject.get(), "Smithing Template");
            add("item.svo." + tier + "_upgrade_smithing_template.upgrade", label + " Upgrade");
            add("item.svo." + tier + "_upgrade_smithing_template.applies_to", label + " Equipment");
            add("item.svo." + tier + "_upgrade_smithing_template.ingredients", ingredientWord);
        });
    }

    private static String capitalize(String s) {
        return s.substring(0, 1).toUpperCase(Locale.ROOT) + s.substring(1);
    }
}
