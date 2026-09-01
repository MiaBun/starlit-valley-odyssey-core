package com.CuteNekoDragon.Core.client;

import com.CuteNekoDragon.Core.client.screen.ToolbeltScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.client.screen.LunchboxScreen;
import com.CuteNekoDragon.Core.common.data.SVOContainers;

@Mod.EventBusSubscriber(modid = SVOCore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SVOClientScreens {

    private SVOClientScreens() {}

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent e) {
        e.enqueueWork(() -> {
            MenuScreens.register(SVOContainers.STORAGE_CONTAINER.get(), LunchboxScreen::new);
            MenuScreens.register(SVOContainers.TOOLBELT_CONTAINER.get(), ToolbeltScreen::new);
        });
    }
}
