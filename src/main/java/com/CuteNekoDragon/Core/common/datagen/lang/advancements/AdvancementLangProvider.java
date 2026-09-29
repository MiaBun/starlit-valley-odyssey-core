package com.CuteNekoDragon.Core.common.datagen.lang.advancements;

import com.CuteNekoDragon.Core.utils.lang.AdvancementLang;

import static com.CuteNekoDragon.Core.utils.lang.LangUtil.entry;

public final class AdvancementLangProvider {

    public static final AdvancementLang CHAPTER1 = chapter1("", "Chapter 1", "Welcome to Starlit Valley!");
    public static final AdvancementLang CRAFTING_TABLE = chapter1("crafting_table", "Craft a Crafting Table",
            "The Beginning of your crafting journey");
    public static final AdvancementLang MINE_STONE = chapter1("mine_stone", "Stone Age",
            "Mine Stone with your new Pickaxe");
    public static final AdvancementLang LEATHER_ARMOR = chapter1("leather_armor", "Suit Up",
            "Have any type of leather armor in the inventory");
    public static final AdvancementLang GETTING_AN_UPGRADE = chapter1("getting_an_upgrade", "Getting an Upgrade",
            "Construct a better Pickaxe");
    public static final AdvancementLang CHAINMAIL_ARMOR = chapter1("chainmail_armor", "Cover me with Chains",
            "Have any type of chainmail armor in the inventory");
    public static final AdvancementLang FINDING_COPPER = chapter1("finding_copper", "Copper!", "Acquire Copper");
    public static final AdvancementLang FURNACE = chapter1("furnace", "Smelt me some ores", "Construct a furnace");
    public static final AdvancementLang CHARCOAL_KILN = chapter1("charcoal_kiln", "Kiln me some Coal!",
            "Construct a Charcoal Kiln");
    public static final AdvancementLang SACK = chapter1("sack", "Sack them up!", "Construct a Sack");
    public static final AdvancementLang CHESTS = chapter1("chests", "Store them up!", "Construct a Chest");
    public static final AdvancementLang CHEST_UPGRADED = chapter1("chest_upgraded", "Improved chests!",
            "Construct a Sophisticated Storage Chest");
    public static final AdvancementLang SLEEPING_BAG = chapter1("sleeping_bag", "Where are my pj's?!",
            "Construct a Sleeping Bag");
    public static final AdvancementLang BED = chapter1("bed", "Goodnight!", "Construct a Bed");
    public static final AdvancementLang TOOLBELT = chapter1("toolbelt", "Belt these tools!", "Construct a Toolbelt");
    public static final AdvancementLang FINDING_IRON = chapter1("finding_iron", "Iron!", "Acquire Iron");
    public static final AdvancementLang MAILBOX = chapter1("mailbox", "Mail me some Mail!", "Construct a Mailbox");

    public static void init() {}

    private static AdvancementLang chapter1(String id, String title, String desc) {
        String path = id.isEmpty() ? "chapter1" : "chapter1." + id;
        return new AdvancementLang(
                entry("advancement", path + ".title", title),
                entry("advancement", path + ".description", desc));
    }
}
