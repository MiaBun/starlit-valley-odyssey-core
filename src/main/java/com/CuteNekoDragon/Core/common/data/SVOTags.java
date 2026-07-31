package com.CuteNekoDragon.Core.common.data;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

import com.CuteNekoDragon.Core.SVOCore;

public final class SVOTags {

    public static final class Items {

        public static final TagKey<Item> Minerals = createItemTag("minerals");
        public static final TagKey<Item> Gemstones = createItemTag("gemstones");
        public static final TagKey<Item> Geode_Mineral = createItemTag("geode_mineral");
        public static final TagKey<Item> Special_Items = createItemTag("special_items");
        public static final TagKey<Item> Blacksmith_Items = createItemTag("blacksmith_items");

        private static TagKey<Item> createItemTag(String path) {
            return createItemTag(SVOCore.id(path));
        }

        private static TagKey<Item> createItemTag(ResourceLocation resLoc) {
            return TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), resLoc);
        }
    }
}
