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

    // Foraged Minerals
    public static ItemEntry<Item> QUARTZ = REGISTRATE.item("quartz", Item::new).lang("Quartz")
            .tag(SVOTags.Items.Minerals)
            .defaultModel().register();
    public static ItemEntry<Item> EARTH_CRYSTAL = REGISTRATE.item("earth_crystal", Item::new).lang("Earth Crystal")
            .tag(SVOTags.Items.Minerals)
            .defaultModel().register();
    public static ItemEntry<Item> FROZEN_TEAR = REGISTRATE.item("frozen_tear", Item::new).lang("Frozen Tear")
            .tag(SVOTags.Items.Minerals)
            .defaultModel().register();
    public static ItemEntry<Item> FIRE_QUARTZ = REGISTRATE.item("fire_quartz", Item::new).lang("Fire Quartz")
            .tag(SVOTags.Items.Minerals)
            .defaultModel().register();

    // Gemstones
    public static ItemEntry<Item> EMERALD = REGISTRATE.item("emerald", Item::new).lang("Emerald")
            .tag(SVOTags.Items.Gemstones)
            .defaultModel().register();
    public static ItemEntry<Item> AQUAMARINE = REGISTRATE.item("aquamarine", Item::new).lang("Aquamarine")
            .tag(SVOTags.Items.Gemstones)
            .defaultModel().register();
    public static ItemEntry<Item> RUBY = REGISTRATE.item("ruby", Item::new).lang("Ruby").tag(SVOTags.Items.Gemstones)
            .defaultModel().register();
    public static ItemEntry<Item> AMETHYST = REGISTRATE.item("amethyst", Item::new).lang("Amethyst")
            .tag(SVOTags.Items.Gemstones)
            .defaultModel().register();
    public static ItemEntry<Item> TOPAZ = REGISTRATE.item("topaz", Item::new).lang("Topaz").tag(SVOTags.Items.Gemstones)
            .defaultModel().register();
    public static ItemEntry<Item> JADE = REGISTRATE.item("jade", Item::new).lang("Jade").tag(SVOTags.Items.Gemstones)
            .defaultModel().register();
    public static ItemEntry<Item> DIAMOND = REGISTRATE.item("diamond", Item::new).lang("Diamond").tag(SVOTags.Items.Gemstones)
            .defaultModel().register();
    public static ItemEntry<Item> PRISMATIC_SHARD = REGISTRATE.item("prismatic_shard", Item::new).lang("Prismatic Shard").tag(SVOTags.Items.Gemstones)
            .defaultModel().register();
}
