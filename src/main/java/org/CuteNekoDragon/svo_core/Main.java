package org.CuteNekoDragon.svo_core;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Main.MOD_ID)
public class Main {

    public static final String MOD_ID = "svo_core";
    public static final String NAME = "SVO-Core";
    private static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    public static ResourceLocation id(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }

    public Main() {

    }

    @SuppressWarnings("removal")
    private static void setupFixForGlobalServerConfig() {
        ModLoadingContext.get().registerExtensionPoint(
                IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> NetworkConstants.IGNORESERVERONLY, (a, b) -> true)
        );
    }
}
