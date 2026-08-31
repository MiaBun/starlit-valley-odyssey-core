package com.CuteNekoDragon.Core;

import com.gregtechceu.gtceu.api.data.chemical.material.registry.MaterialRegistry;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkConstants;

import com.CuteNekoDragon.Core.client.ClientProxy;
import com.CuteNekoDragon.Core.common.CommonProxy;
import com.CuteNekoDragon.Core.config.SVOConfig;
import guideme.Guide;
import guideme.GuideItemSettings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(SVOCore.MOD_ID)
@SuppressWarnings("removal")
public class SVOCore {

    public static final String MOD_ID = "svo_core";
    public static final String NAME = "SVO-Core";
    public static final Logger LOGGER = LogManager.getLogger(NAME);

    public static MaterialRegistry MATERIAL_REGISTRY;
    public static final GTRegistrate REGISTRATE = GTRegistrate.create(SVOCore.MOD_ID);

    public static ResourceLocation id(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }

    public SVOCore() {
        System.out.println("  //");
        System.out.println(" ('>");
        System.out.println(" /rr");
        System.out.println("*\\))_");
        setupFixForGlobalServerConfig();
        SVOConfig.init();

        var guide = Guide.builder(ResourceLocation.fromNamespaceAndPath("svo_core", "guide"))
                .itemSettings(GuideItemSettings.DEFAULT)
                .startPage(ResourceLocation.fromNamespaceAndPath("svo_core", "index.md"))
                .build();

        DistExecutor.unsafeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    }

    private static void setupFixForGlobalServerConfig() {
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class,
                () -> new IExtensionPoint.DisplayTest(() -> NetworkConstants.IGNORESERVERONLY, (a, b) -> true));
    }
}
