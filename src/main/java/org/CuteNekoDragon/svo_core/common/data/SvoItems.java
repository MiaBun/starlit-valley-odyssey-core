package org.CuteNekoDragon.svo_core.common.data;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.CuteNekoDragon.svo_core.Main;
import org.CuteNekoDragon.svo_core.common.item.SVOSmithingTemplateItem;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SvoItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, Main.MOD_ID);

    private static final List<String> UPGRADE_TIERS = List.of("iron", "gold", "diamond");

    public static final Map<String, RegistryObject<Item>> UPGRADE_TEMPLATES = new LinkedHashMap<>();

    static {
        for (String tier : UPGRADE_TIERS) {
            String id = tier + "_upgrade_smithing_template";
            UPGRADE_TEMPLATES.put(
                    tier,
                    ITEMS.register(id, () -> new SVOSmithingTemplateItem(tier, new Item.Properties()))
            );
        }
    }

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
