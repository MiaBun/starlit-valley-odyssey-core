package com.CuteNekoDragon.Core.client;

import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import com.CuteNekoDragon.Core.common.CommonProxy;
import com.CuteNekoDragon.Core.common.data.SVOParticles;
import com.CuteNekoDragon.Core.common.particle.RedExclamationParticle;
import org.jetbrains.annotations.NotNull;

public class ClientProxy extends CommonProxy {

    @SubscribeEvent
    public void registerParticles(@NotNull RegisterParticleProvidersEvent e) {
        e.registerSpriteSet(SVOParticles.RED_EXCLAMATION.get(), RedExclamationParticle.Provider::new);
    }

    @SuppressWarnings("removal")
    public ClientProxy() {
        super();

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    }
}
