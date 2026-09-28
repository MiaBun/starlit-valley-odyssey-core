package com.CuteNekoDragon.Core.common.datagen.lang;

import com.CuteNekoDragon.Core.common.datagen.lang.advancements.AdvancementLangProvider;
import com.CuteNekoDragon.Core.common.datagen.lang.external.ExternalLangProvider;
import com.CuteNekoDragon.Core.common.datagen.lang.gui.GUILangProvider;
import com.CuteNekoDragon.Core.common.datagen.lang.keybinds.KeybindsLangProvider;
import com.CuteNekoDragon.Core.common.datagen.lang.messages.MessageLangProvider;
import com.CuteNekoDragon.Core.common.datagen.lang.tooltips.TooltipLangProvider;

import static com.CuteNekoDragon.Core.SVOCore.REGISTRATE;

public class SVOLangProvider {

    public static void gatherData() {

        AdvancementLangProvider.init();
        ExternalLangProvider.init();
        GUILangProvider.init();
        KeybindsLangProvider.init();
        MessageLangProvider.init();
        TooltipLangProvider.init();

        REGISTRATE.addRawLang("message.svo_core.toolbelt_invalid_item", "Toolbelt Doesn't accept non-durable items.");

        REGISTRATE.addRawLang("gtceu.charcoal_kiln", "Charcoal Kiln");
        REGISTRATE.addRawLang("curios.identifier.lunchbox", "Lunchbox");
        REGISTRATE.addRawLang("curios.identifier.toolbelt", "Toolbelt");

        REGISTRATE.addRawLang("key.categories.svo", "Starlit Valley: Odyssey");
        REGISTRATE.addRawLang("key.svo.skilltree.open", "Opens the Skills Menu");
    }
}
