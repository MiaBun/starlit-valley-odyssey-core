package com.CuteNekoDragon.Core.common.data;

import net.minecraft.world.item.Item;

import com.CuteNekoDragon.Core.common.item.SVOSmithingTemplate;
import com.tterrag.registrate.util.entry.ItemEntry;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.CuteNekoDragon.Core.SVOCore.REGISTRATE;

public class SVOItems {

    public static void init() {}

    private static final List<String> UPGRADE_TIERS = List.of("iron", "gold", "diamond");

    private static final Map<String, String> TIER_LABELS = Map.of(
            "iron", "Iron",
            "gold", "Gold",
            "diamond", "Diamond");

    private static final Map<String, String> APPLIES_TO_LABELS = Map.of(
            "iron", "Stone, Leather and Cotton",
            "gold", "Iron",
            "diamond", "Gold");

    public static final Map<String, ItemEntry<SVOSmithingTemplate>> UPGRADE_TEMPLATES = new LinkedHashMap<>();

    static {
        REGISTRATE.addRawLang("itemGroup.svo_core", "Svo Core");

        for (String tier : UPGRADE_TIERS) {
            String id = tier + "_upgrade_smithing_template";
            String label = TIER_LABELS.get(tier);
            String appliesToLabel = APPLIES_TO_LABELS.get(tier);
            String ingredientWord = tier.equals("diamond") ? label + "s" : label + " Ingots";

            UPGRADE_TEMPLATES.put(
                    tier,
                    REGISTRATE.item(id, p -> new SVOSmithingTemplate(tier, p))
                            .lang("Smithing Template")
                            .model((ctx, prov) -> prov.withExistingParent(ctx.getName(), "minecraft:item/generated")
                                    .texture("layer0", prov.modLoc("item/smithing/" + tier)))
                            .register());

            REGISTRATE.addRawLang("item.svo." + id + ".upgrade", label + " Upgrade");
            REGISTRATE.addRawLang("item.svo." + id + ".applies_to", appliesToLabel + " Equipment");
            REGISTRATE.addRawLang("item.svo." + id + ".ingredients", ingredientWord);
        }
    }

    public static ItemEntry<Item> QUARTZ = REGISTRATE.item("quartz", Item::new)
            .lang("Quartz")
            .defaultModel()
            .register();
}
