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
    public static ItemEntry<Item> DIAMOND = REGISTRATE.item("diamond", Item::new).lang("Diamond")
            .tag(SVOTags.Items.Gemstones)
            .defaultModel().register();
    public static ItemEntry<Item> PRISMATIC_SHARD = REGISTRATE.item("prismatic_shard", Item::new)
            .lang("Prismatic Shard").tag(SVOTags.Items.Gemstones)
            .defaultModel().register();

    // Geode Minerals
    public static ItemEntry<Item> TIGERSEYE = REGISTRATE.item("tigerseye", Item::new)
            .lang("Tigerseye").tag(SVOTags.Items.Geode_Mineral)
            .defaultModel().register();
    public static ItemEntry<Item> OPAL = REGISTRATE.item("opal", Item::new).lang("Opal")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> FIRE_OPAL = REGISTRATE.item("fire_opal", Item::new).lang("Fire Opal")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> ALAMITE = REGISTRATE.item("alamite", Item::new).lang("Alamite")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> BIXITE = REGISTRATE.item("bixite", Item::new).lang("Bixite")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> BARYTE = REGISTRATE.item("baryte", Item::new).lang("Baryte")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> AERINITE = REGISTRATE.item("aerinite", Item::new).lang("Aerinite")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> CAlCITE = REGISTRATE.item("calcite", Item::new).lang("Calcite")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> DOLOMITE = REGISTRATE.item("dolomite", Item::new).lang("Dolomite")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> ESPERITE = REGISTRATE.item("esperite", Item::new).lang("Esperite")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> FLUORAPATITE = REGISTRATE.item("fluorapatite", Item::new).lang("Fluorapatite").tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> GEMINITE = REGISTRATE.item("geminite", Item::new).lang("Geminite").tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> HELVITE = REGISTRATE.item("helvite", Item::new).lang("Helvite").tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> JAMBORITE = REGISTRATE.item("jamborite", Item::new).lang("Jamborite").tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> JAGOITE = REGISTRATE.item("jagoite", Item::new).lang("Jagoite").tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> KYANITE = REGISTRATE.item("kyanite", Item::new).lang("Kyanite").tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> LUNARITE = REGISTRATE.item("lunarite", Item::new).lang("Lunarite").tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> MALACHITE = REGISTRATE.item("malachite", Item::new).lang("Malachite").tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> NEPTUNITE = REGISTRATE.item("neptunite", Item::new).lang("Neptunite").tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> LEMON_STONE = REGISTRATE.item("lemon_stone", Item::new).lang("Lemon Stone").tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> NEKOITE = REGISTRATE.item("nekoite", Item::new).lang("Nekoite").tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
}
