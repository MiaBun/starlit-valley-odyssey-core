package com.CuteNekoDragon.Core.common.data;

import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;

import com.tterrag.registrate.util.entry.RegistryEntry;

import static com.CuteNekoDragon.Core.SVOCore.REGISTRATE;

@SuppressWarnings("removal")
public class SVOCreativeTab {

    public static void init() {}

    public static RegistryEntry<CreativeModeTab> SVO = REGISTRATE.defaultCreativeTab("svo_core",
            builder -> builder.title(Component.translatable("creative_tab.svo_core.svo"))
                    .icon(() -> new ItemStack(Items.AMETHYST_SHARD))
                    .displayItems(new RegistrateDisplayItemsGenerator("svo_core", REGISTRATE)))
            .register();

    public record RegistrateDisplayItemsGenerator(String name,
                                                  GTRegistrate registrate)
            implements CreativeModeTab.DisplayItemsGenerator {

        @Override
        public void accept(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output) {
            var tab = registrate.get(name, Registries.CREATIVE_MODE_TAB);
            for (var entry : registrate.getAll((Registries.BLOCK))) {
                Block block = entry.get();
                var stack = new ItemStack(block, 1);
                if (registrate.isInCreativeTab(entry, tab))
                    continue;
                if (entry.getId().getNamespace().equals("svo_core") && !stack.isEmpty())
                    output.accept(block);
            }
            for (var entry : registrate.getAll(Registries.ITEM)) {
                if (registrate.isInCreativeTab(entry, tab))
                    continue;
                Item item = entry.get();
                var stack = new ItemStack(item, 1);
                if (item instanceof BlockItem)
                    continue;
                if (entry.getId().getNamespace().equals("svo_core"))
                    output.accept(stack);
            }
        }
    }
}
