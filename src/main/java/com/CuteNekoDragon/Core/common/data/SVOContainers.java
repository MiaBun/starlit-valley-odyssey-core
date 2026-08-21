package com.CuteNekoDragon.Core.common.data;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.common.container.LunchboxContainer;

public class SVOContainers {

    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES,
            SVOCore.MOD_ID);

    public static final RegistryObject<MenuType<LunchboxContainer>> STORAGE_CONTAINER = CONTAINERS.register("storage",
            () -> IForgeMenuType.create(LunchboxContainer::new));

    public static <
            C extends AbstractContainerMenu> RegistryObject<MenuType<C>> registerContainer(String name,
                                                                                           IContainerFactory<C> factory) {
        return CONTAINERS.register(name, () -> IForgeMenuType.create(factory));
    }
}
