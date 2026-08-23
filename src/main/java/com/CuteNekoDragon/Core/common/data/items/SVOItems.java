package com.CuteNekoDragon.Core.common.data.items;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackItem;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.common.data.SVOTags;
import com.CuteNekoDragon.Core.common.data.blocks.SVOBlocks;
import com.CuteNekoDragon.Core.common.item.*;
import com.tterrag.registrate.util.entry.ItemEntry;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.CuteNekoDragon.Core.SVOCore.REGISTRATE;

public class SVOItems {

    private static Item vanillaDye(DyeColor color) {
        return switch (color) {
            case WHITE -> Items.WHITE_DYE;
            case ORANGE -> Items.ORANGE_DYE;
            case MAGENTA -> Items.MAGENTA_DYE;
            case LIGHT_BLUE -> Items.LIGHT_BLUE_DYE;
            case YELLOW -> Items.YELLOW_DYE;
            case LIME -> Items.LIME_DYE;
            case PINK -> Items.PINK_DYE;
            case GRAY -> Items.GRAY_DYE;
            case LIGHT_GRAY -> Items.LIGHT_GRAY_DYE;
            case CYAN -> Items.CYAN_DYE;
            case PURPLE -> Items.PURPLE_DYE;
            case BLUE -> Items.BLUE_DYE;
            case BROWN -> Items.BROWN_DYE;
            case GREEN -> Items.GREEN_DYE;
            case RED -> Items.RED_DYE;
            case BLACK -> Items.BLACK_DYE;
        };
    }

    public static void init() {}

    private static final List<String> UPGRADE_TIERS = List.of("copper", "iron", "gold", "diamond", "iridium");

    private static final Map<String, String> TIER_LABELS = Map.of(
            "copper", "Copper",
            "iron", "Iron",
            "gold", "Gold",
            "diamond", "Diamond",
            "iridium", "Iridium");

    private static final Map<String, String> APPLIES_TO_LABELS = Map.of(
            "copper", "Stone, Leather and Cotton",
            "iron", "Copper",
            "gold", "Iron",
            "diamond", "Gold",
            "iridium", "Diamond");

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
    public static ItemEntry<Item> FLUORAPATITE = REGISTRATE.item("fluorapatite", Item::new).lang("Fluorapatite")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> GEMINITE = REGISTRATE.item("geminite", Item::new).lang("Geminite")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> HELVITE = REGISTRATE.item("helvite", Item::new).lang("Helvite")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> JAMBORITE = REGISTRATE.item("jamborite", Item::new).lang("Jamborite")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> JAGOITE = REGISTRATE.item("jagoite", Item::new).lang("Jagoite")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> KYANITE = REGISTRATE.item("kyanite", Item::new).lang("Kyanite")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> LUNARITE = REGISTRATE.item("lunarite", Item::new).lang("Lunarite")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> MALACHITE = REGISTRATE.item("malachite", Item::new).lang("Malachite")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> NEPTUNITE = REGISTRATE.item("neptunite", Item::new).lang("Neptunite")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> LEMON_STONE = REGISTRATE.item("lemon_stone", Item::new).lang("Lemon Stone")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> NEKOITE = REGISTRATE.item("nekoite", Item::new).lang("Nekoite")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> ORPIMENT = REGISTRATE.item("orpiment", Item::new).lang("Orpiment")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> PETRIFIED_SLIME = REGISTRATE.item("petrified_slime", Item::new)
            .lang("Petrified Slime").tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> THUNDER_EGG = REGISTRATE.item("thunder_egg", Item::new).lang("Thunder Egg")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> PYRITE = REGISTRATE.item("pyrite", Item::new).lang("Pyrite")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> OCEAN_STONE = REGISTRATE.item("ocean_stone", Item::new).lang("Ocean Stone")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> GHOST_CRYSTAL = REGISTRATE.item("ghost_crystal", Item::new).lang("Ghost Crystal")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> JASPER = REGISTRATE.item("jasper", Item::new).lang("Jasper")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> CELESTINE = REGISTRATE.item("celestine", Item::new).lang("Celestine")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> MARBLE = REGISTRATE.item("marble", Item::new).lang("Marble")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> SANDSTONE = REGISTRATE.item("sandstone", Item::new).lang("Sandstone")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> GRANITE = REGISTRATE.item("granite", Item::new).lang("Granite")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> BASALT = REGISTRATE.item("basalt", Item::new).lang("Basalt")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> LIMESTONE = REGISTRATE.item("limestone", Item::new).lang("Limestone")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> SOAPSTONE = REGISTRATE.item("soapstone", Item::new).lang("Soapstone")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> HERMATITE = REGISTRATE.item("hermatite", Item::new).lang("Hermatite")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> MUDSTONE = REGISTRATE.item("mudstone", Item::new).lang("Mudstone")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> OBSIDIAN = REGISTRATE.item("obsidian", Item::new).lang("Obsidian")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> SLATE = REGISTRATE.item("slate", Item::new).lang("Slate")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> FAIRY_STONE = REGISTRATE.item("fairy_stone", Item::new).lang("Fairy Stone")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();
    public static ItemEntry<Item> STAR_SHARDS = REGISTRATE.item("star_shards", Item::new).lang("Star Shards")
            .tag(SVOTags.Items.Geode_Mineral).defaultModel().register();

    // Special Items

    public static ItemEntry<Item> GALAXY_SOUL = REGISTRATE.item("galaxy_soul", Item::new).lang("Galaxy Soul")
            .tag(SVOTags.Items.Special_Items).defaultModel().register();
    public static ItemEntry<Item> DRAGON_TOOTH = REGISTRATE.item("dragon_tooth", Item::new).lang("Dragon Tooth")
            .tag(SVOTags.Items.Special_Items).defaultModel().register();
    public static ItemEntry<Item> CINDER_SHARD = REGISTRATE.item("cinder_shard", Item::new).lang("Cinder Shard")
            .tag(SVOTags.Items.Special_Items).defaultModel().register();

    // Ingots and Blacksmith items
    public static ItemEntry<Item> IRIDIUM_INGOT = REGISTRATE.item("iridium_ingot", Item::new).lang("Iridium Ingot")
            .tag(SVOTags.Items.Blacksmith_Items).defaultModel().register();
    public static ItemEntry<Item> REFINED_QUARTZ = REGISTRATE.item("refined_quartz", Item::new).lang("Refined Quartz")
            .tag(SVOTags.Items.Blacksmith_Items).defaultModel().recipe((ctx, provider) -> {
                SimpleCookingRecipeBuilder.smelting(Ingredient.of(QUARTZ), RecipeCategory.MISC, ctx.get(), 0.7f, 200)
                        .unlockedBy("has_svo_quartz", provider.has(QUARTZ))
                        .save(provider, SVOCore.id("smelting/refined_quartz"));

                SimpleCookingRecipeBuilder.blasting(Ingredient.of(QUARTZ), RecipeCategory.MISC, ctx.get(), 0.7f, 100)
                        .unlockedBy("has_svo_quartz", provider.has(QUARTZ))
                        .save(provider, SVOCore.id("blasting/refined_quartz"));
            }).register();
    public static ItemEntry<Item> RADIOACTIVE_INGOT = REGISTRATE.item("radioactive_ingot", Item::new)
            .lang("Radioactive Ingot").tag(SVOTags.Items.Blacksmith_Items).defaultModel().register();

    // gear
    public static final ItemEntry<SwordItem> COPPER_SWORD = REGISTRATE
            .item("copper_sword", p -> new SwordItem(SVOTiers.COPPER_TIER, 3, -2.4f, p))
            .properties(p -> p.stacksTo(1))
            .register();

    public static final ItemEntry<SwordItem> IRIDIUM_SWORD = REGISTRATE
            .item("iridium_sword", p -> new SwordItem(SVOTiers.IRIDIUM_TIER, 3, -2.4f, p))
            .properties(p -> p.stacksTo(1))
            .register();

    public static final ItemEntry<PickaxeItem> COPPER_PICKAXE = REGISTRATE
            .item("copper_pickaxe", p -> new PickaxeItem(SVOTiers.COPPER_TIER, 1, -2.8f, p))
            .properties(p -> p.stacksTo(1))
            .register();

    public static final ItemEntry<PickaxeItem> IRIDIUM_PICKAXE = REGISTRATE
            .item("iridium_pickaxe", p -> new PickaxeItem(SVOTiers.IRIDIUM_TIER, 1, -2.8f, p))
            .properties(p -> p.stacksTo(1))
            .register();

    public static final ItemEntry<ShovelItem> COPPER_SHOVEL = REGISTRATE
            .item("copper_shovel", p -> new ShovelItem(SVOTiers.COPPER_TIER, 1, -3.0f, p))
            .properties(p -> p.stacksTo(1))
            .register();

    public static final ItemEntry<ShovelItem> IRIDIUM_SHOVEL = REGISTRATE
            .item("iridium_shovel", p -> new ShovelItem(SVOTiers.IRIDIUM_TIER, 1, -3.0f, p))
            .properties(p -> p.stacksTo(1))
            .register();

    public static final ItemEntry<AxeItem> COPPER_AXE = REGISTRATE
            .item("copper_axe", p -> new AxeItem(SVOTiers.COPPER_TIER, 6, -3.1f, p))
            .properties(p -> p.stacksTo(1))
            .register();

    public static final ItemEntry<AxeItem> IRIDIUM_AXE = REGISTRATE
            .item("iridium_axe", p -> new AxeItem(SVOTiers.IRIDIUM_TIER, 5, -3.0f, p))
            .properties(p -> p.stacksTo(1))
            .register();

    public static final ItemEntry<HoeItem> COPPER_HOE = REGISTRATE
            .item("copper_hoe", p -> new HoeItem(SVOTiers.COPPER_TIER, 1, -1.0f, p))
            .properties(p -> p.stacksTo(1))
            .register();

    public static final ItemEntry<HoeItem> IRIDIUM_HOE = REGISTRATE
            .item("iridium_hoe", p -> new HoeItem(SVOTiers.IRIDIUM_TIER, -4, 0.0f, p))
            .properties(p -> p.stacksTo(1))
            .register();

    // armor sets

    public static final ItemEntry<ArmorItem> COPPER_HELMET = REGISTRATE
            .item("copper_helmet", p -> new ArmorItem(SVOArmorMaterials.COPPER, ArmorItem.Type.HELMET, p))
            .properties(p -> p.stacksTo(1))
            .register();

    public static final ItemEntry<ArmorItem> COPPER_CHESTPLATE = REGISTRATE
            .item("copper_chestplate", p -> new ArmorItem(SVOArmorMaterials.COPPER, ArmorItem.Type.CHESTPLATE, p))
            .properties(p -> p.stacksTo(1))
            .register();

    public static final ItemEntry<ArmorItem> COPPER_LEGGINGS = REGISTRATE
            .item("copper_leggings", p -> new ArmorItem(SVOArmorMaterials.COPPER, ArmorItem.Type.LEGGINGS, p))
            .properties(p -> p.stacksTo(1))
            .register();

    public static final ItemEntry<ArmorItem> COPPER_BOOTS = REGISTRATE
            .item("copper_boots", p -> new ArmorItem(SVOArmorMaterials.COPPER, ArmorItem.Type.BOOTS, p))
            .properties(p -> p.stacksTo(1))
            .register();

    public static final ItemEntry<ArmorItem> IRIDIUM_HELMET = REGISTRATE
            .item("iridium_helmet", p -> new ArmorItem(SVOArmorMaterials.IRIDIUM, ArmorItem.Type.HELMET, p))
            .properties(p -> p.stacksTo(1))
            .register();

    public static final ItemEntry<ArmorItem> IRIDIUM_CHESTPLATE = REGISTRATE
            .item("iridium_chestplate", p -> new ArmorItem(SVOArmorMaterials.IRIDIUM, ArmorItem.Type.CHESTPLATE, p))
            .properties(p -> p.stacksTo(1))
            .register();

    public static final ItemEntry<ArmorItem> IRIDIUM_LEGGINGS = REGISTRATE
            .item("iridium_leggings", p -> new ArmorItem(SVOArmorMaterials.IRIDIUM, ArmorItem.Type.LEGGINGS, p))
            .properties(p -> p.stacksTo(1))
            .register();

    public static final ItemEntry<ArmorItem> IRIDIUM_BOOTS = REGISTRATE
            .item("iridium_boots", p -> new ArmorItem(SVOArmorMaterials.IRIDIUM, ArmorItem.Type.BOOTS, p))
            .properties(p -> p.stacksTo(1))
            .register();

    public static final ItemEntry<BackpackItem> IRIDIUM_BACKPACK = REGISTRATE
            .item("iridium_backpack", p -> new BackpackItem(
                    () -> 120,
                    () -> 7,
                    SVOBlocks.IRIDIUM_BACKPACK))
            .model((ctx, prov) -> {}).register();

    public static ItemEntry<SackItem> SACK = REGISTRATE
            .item("sack", p -> new SackItem(new Item.Properties().stacksTo(1)))
            .tag(SVOTags.Items.SACK)
            .recipe((ctx, provider) -> {
                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ctx.get())
                        .pattern(" A ")
                        .pattern(" B ")
                        .pattern("   ")
                        .define('A', Items.STRING)
                        .define('B', Items.LEATHER)
                        .unlockedBy("has_leather", InventoryChangeTrigger.TriggerInstance.hasItems(Items.LEATHER))
                        .save(provider, SVOCore.id("shaped/sack"));
            })
            .register();

    public static final Map<DyeColor, ItemEntry<SackItem>> DYED_SACKS = new LinkedHashMap<>();
    static {
        for (DyeColor color : DyeColor.values()) {
            String dyeName = color.getName() + "_sack";
            DYED_SACKS.put(
                    color,
                    REGISTRATE.item(dyeName, p -> new SackItem(new Item.Properties().stacksTo(1)))
                            .tag(SVOTags.Items.SACK)
                            .recipe((ctx, provider) -> {
                                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ctx.get())
                                        .pattern("   ")
                                        .pattern("AB ")
                                        .pattern("   ")
                                        .define('A', SVOItems.SACK)
                                        .define('B', vanillaDye(color))
                                        .unlockedBy("has_sack",
                                                InventoryChangeTrigger.TriggerInstance.hasItems(SVOItems.SACK))
                                        .save(provider, SVOCore.id("shaped/" + color + "_sack"));
                            })
                            .register());

        }
    }

    public static ItemEntry<LunchboxItem> LUNCHBOX = REGISTRATE
            .item("lunchbox", p -> new LunchboxItem(p.stacksTo(1), 3, 60)).lang("Lunchbox")
            .tag(SVOTags.Items.LUNCHBOX).tag(SVOTags.Items.CURIO_LUNCHBOX).defaultModel().register();
    public static ItemEntry<LunchboxItem> DELUXE_LUNCHBOX = REGISTRATE
            .item("deluxe_lunchbox", p -> new LunchboxItem(p.stacksTo(1), 4, 40)).lang("Deluxe Lunchbox")
            .tag(SVOTags.Items.LUNCHBOX).tag(SVOTags.Items.CURIO_LUNCHBOX).defaultModel().register();
    public static ItemEntry<LunchboxItem> SUPER_DELUXE_LUNCHBOX = REGISTRATE
            .item("super_deluxe_lunchbox", p -> new LunchboxItem(p.stacksTo(1), 5, 30)).lang("Super Deluxe Lunchbox")
            .tag(SVOTags.Items.LUNCHBOX).tag(SVOTags.Items.CURIO_LUNCHBOX).defaultModel().register();
    public static ItemEntry<LunchboxItem> PREMIUM_LUNCHBOX = REGISTRATE
            .item("premium_lunchbox", p -> new LunchboxItem(p.stacksTo(1), 6, 20)).lang("Premium Lunchbox")
            .tag(SVOTags.Items.LUNCHBOX).tag(SVOTags.Items.CURIO_LUNCHBOX).defaultModel().register();
    public static ItemEntry<LunchboxItem> ULTIMATE_LUNCHBOX = REGISTRATE
            .item("ultimate_lunchbox", p -> new LunchboxItem(p.stacksTo(1), 8, 15)).lang("Ultimate Lunchbox")
            .tag(SVOTags.Items.LUNCHBOX).tag(SVOTags.Items.CURIO_LUNCHBOX).defaultModel().register();
    public static ItemEntry<LunchboxItem> COLLECTORS_LUNCHBOX = REGISTRATE
            .item("collectors_lunchbox", p -> new LunchboxItem(p.stacksTo(1), 9, 5)).lang("Collectors Lunchbox")
            .tag(SVOTags.Items.LUNCHBOX).tag(SVOTags.Items.CURIO_LUNCHBOX).defaultModel().register();


}
