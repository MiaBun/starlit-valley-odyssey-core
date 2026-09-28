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
        REGISTRATE.addRawLang("gui.svo_core.toolbelt_radial", "Toolbelt Radial");

        REGISTRATE.addRawLang("gtceu.charcoal_kiln", "Charcoal Kiln");
        REGISTRATE.addRawLang("curios.identifier.lunchbox", "Lunchbox");
        REGISTRATE.addRawLang("curios.identifier.toolbelt", "Toolbelt");

        REGISTRATE.addRawLang("gui.svo_core.mailbox.new_mail", "You've got new mail!");
        REGISTRATE.addRawLang("gui.svo_core.mailbox.toast_setting", "Notification Settings");
        REGISTRATE.addRawLang("gui.svo_core.mailbox.toast_off", "Disabled");
        REGISTRATE.addRawLang("gui.svo_core.mailbox.toast_on", "Enabled");
        REGISTRATE.addRawLang("gui.svo_core.mailbox.select_a_letter", "Select a Letter");
        REGISTRATE.addRawLang("gui.svo_core.mailbox.from", "From: %s");
        REGISTRATE.addRawLang("gui.svo_core.mailbox.title", "Mail Box");
        REGISTRATE.addRawLang("gui.svo_core.mailbox.no_letters", "No Letters");

        REGISTRATE.addRawLang("gui.svo_core.mailbox.indicator_setting", "Indicator Settings");
        REGISTRATE.addRawLang("gui.svo_core.mailbox.indicator_off", "Disabled");
        REGISTRATE.addRawLang("gui.svo_core.mailbox.indicator_on", "Enabled");

        REGISTRATE.addRawLang("key.categories.svo", "Starlit Valley: Odyssey");
        REGISTRATE.addRawLang("key.svo.skilltree.open", "Opens the Skills Menu");
    }
}
