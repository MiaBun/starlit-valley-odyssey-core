package org.CuteNekoDragon.svo_core.common.data;

import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.CuteNekoDragon.svo_core.Main;

public class SvoCreativeTab {

    public static void init() {

    }

    public static final DeferredRegister<CreativeModeTab> SVO = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Main.MOD_ID);

    public static final RegistryObject<CreativeModeTab> SVO_TAB = SVO.register("svo-core", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + Main.MOD_ID))
            .icon(() -> new ItemStack(Items.AMETHYST_SHARD))
            .displayItems((parameters, output) -> {
                SvoItems.UPGRADE_TEMPLATES.values().forEach(itemRegistryObject ->
                        output.accept(itemRegistryObject.get()));
            }).build()
    );

    public static void register(IEventBus bus) {
        SVO.register(bus);
    }
}
