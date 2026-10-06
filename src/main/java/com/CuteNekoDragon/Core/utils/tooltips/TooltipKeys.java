package com.CuteNekoDragon.Core.utils.tooltips;

import com.CuteNekoDragon.Core.SVOCore;
import com.CuteNekoDragon.Core.common.datagen.lang.keybinds.KeybindsLangProvider;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public final class TooltipKeys {

    public static KeyMapping NEXT_PAGE;
    public static KeyMapping PREV_PAGE;

    private TooltipKeys() {}

    static void onRegisterKeys(RegisterKeyMappingsEvent event) {
        NEXT_PAGE = new KeyMapping(KeybindsLangProvider.TOOLTIP_NEXT_PAGE.key(), KeyConflictContext.GUI,
                InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_BRACKET, KeybindsLangProvider.CATEGORY.key());
        PREV_PAGE = new KeyMapping(KeybindsLangProvider.TOOLTIP_PREVIOUS_PAGE.key(), KeyConflictContext.GUI,
                InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_BRACKET, KeybindsLangProvider.CATEGORY.key());
        event.register(NEXT_PAGE);
        event.register(PREV_PAGE);
    }
}
