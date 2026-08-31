package com.CuteNekoDragon.Core.integration.forks.terrafirmacraft.common;

import com.CuteNekoDragon.Core.common.data.SVOCreativeTab;
import com.CuteNekoDragon.Core.integration.forks.terrafirmacraft.TFC;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;

import static com.CuteNekoDragon.Core.SVOCore.REGISTRATE;

public class TFCCreativeTab {

    public static void init() {}

    public static RegistryEntry<CreativeModeTab> TFC_TAB = TFC.REGISTRATE.defaultCreativeTab("tfc",
                    builder -> builder.title(Component.translatable("creative_tab.tfc.tfc"))
                            .icon(() -> new ItemStack(Items.GRASS_BLOCK))
                            .displayItems(new SVOCreativeTab.RegistrateDisplayItemsGenerator("tfc", TFC.REGISTRATE)))
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
                if (entry.getId().getNamespace().equals("tfc") && !stack.isEmpty())
                    output.accept(block);
            }
            for (var entry : registrate.getAll(Registries.ITEM)) {
                if (registrate.isInCreativeTab(entry, tab))
                    continue;
                Item item = entry.get();
                var stack = new ItemStack(item, 1);
                if (item instanceof BlockItem)
                    continue;
                if (entry.getId().getNamespace().equals("tfc"))
                    output.accept(stack);
            }
        }
    }
}
