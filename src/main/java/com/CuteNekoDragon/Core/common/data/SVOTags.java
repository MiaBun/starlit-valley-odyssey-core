package com.CuteNekoDragon.Core.common.data;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;

import com.CuteNekoDragon.Core.SVOCore;

@SuppressWarnings("removal")
public final class SVOTags {

    public static final class Items {

        public static final TagKey<Item> HIDDEN_FROM_RECIPE_VIEWERS = TagKey.create(Registries.ITEM,
                new ResourceLocation("c", "hidden_from_recipe_viewers"));

        public static final TagKey<Item> CURIO_LUNCHBOX = TagKey.create(Registries.ITEM,
                new ResourceLocation("curios", "lunchbox"));

        public static final TagKey<Item> Minerals = createItemTag("minerals");
        public static final TagKey<Item> Gemstones = createItemTag("gemstones");
        public static final TagKey<Item> Geode_Mineral = createItemTag("geode_mineral");
        public static final TagKey<Item> Special_Items = createItemTag("special_items");
        public static final TagKey<Item> Blacksmith_Items = createItemTag("blacksmith_items");

        public static final TagKey<Item> SACK = createItemTag("sack");

        public static final TagKey<Item> LUNCHBOX = createItemTag("lunchbox");
        public static final TagKey<Item> TOOLBELT = createItemTag("toolbelt");

        public static final TagKey<Item> Sleeping_Bags = createItemTag("sleeping_bags");

        public static final TagKey<Item> GARBAGE = createItemTag("garbage");

        private static TagKey<Item> createItemTag(String path) {
            return createItemTag(SVOCore.id(path));
        }

        private static TagKey<Item> createItemTag(ResourceLocation resLoc) {
            return TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), resLoc);
        }
    }

    public static final class Blocks {

        public static final TagKey<Block> Sleeping_Bags = createBlockTag("sleeping_bags");

        private static TagKey<Block> createBlockTag(String path) {
            return createBlockTag(SVOCore.id(path));
        }

        private static TagKey<Block> createBlockTag(ResourceLocation resLoc) {
            return TagKey.create(ForgeRegistries.BLOCKS.getRegistryKey(), resLoc);
        }
    }

    public static final class Fluids {

        private static TagKey<Fluid> createFluidTag(String path) {
            return createFluidTag(SVOCore.id(path));
        }

        private static TagKey<Fluid> createFluidTag(ResourceLocation resLoc) {
            return TagKey.create(ForgeRegistries.FLUIDS.getRegistryKey(), resLoc);
        }
    }

    public static final class Entities {

        private static TagKey<EntityType<?>> createEntityTag(String path) {
            return createEntityTag(SVOCore.id(path));
        }

        private static TagKey<EntityType<?>> createEntityTag(ResourceLocation resLoc) {
            return TagKey.create(ForgeRegistries.ENTITY_TYPES.getRegistryKey(), resLoc);
        }
    }

    public static final class Biomes {

        private static TagKey<Biome> createBiomeTag(String path) {
            return createBiomeTag(SVOCore.id(path));
        }

        private static TagKey<Biome> createBiomeTag(ResourceLocation resLoc) {
            return TagKey.create(ForgeRegistries.BIOMES.getRegistryKey(), resLoc);
        }
    }
}
