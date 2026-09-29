package com.CuteNekoDragon.Core.common.datagen.lang;

import com.CuteNekoDragon.Core.common.datagen.lang.advancements.AdvancementLangProvider;
import com.CuteNekoDragon.Core.common.datagen.lang.external.ExternalLangProvider;
import com.CuteNekoDragon.Core.common.datagen.lang.gui.GUILangProvider;
import com.CuteNekoDragon.Core.common.datagen.lang.keybinds.KeybindsLangProvider;
import com.CuteNekoDragon.Core.common.datagen.lang.messages.MessageLangProvider;
import com.CuteNekoDragon.Core.common.datagen.lang.tooltips.TooltipLangProvider;

public class SVOLangProvider {

    public static void gatherData() {
        AdvancementLangProvider.init();
        ExternalLangProvider.init();
        GUILangProvider.init();
        KeybindsLangProvider.init();
        MessageLangProvider.init();
        TooltipLangProvider.init();
    }
}
