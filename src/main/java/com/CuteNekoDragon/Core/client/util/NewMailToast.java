package com.CuteNekoDragon.Core.client.util;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.network.chat.Component;

public class NewMailToast implements Toast {

    private static final long DISPLAY_TIME_MS = 5000L;


    private final Component npcName;
    private final Component letterTitle;
    private long firstRenderTime = -1L;

    public NewMailToast(Component npcName, Component letterTitle) {
        this.npcName = npcName;
        this.letterTitle = letterTitle;
    }

    @Override
    public int width() {
        return 160;
    }

    @Override
    public int height() {
        return 32;
    }

    @Override
    public Visibility render(GuiGraphics guiGraphics, ToastComponent toastComponent, long timeSinceLastVisible) {
        if (firstRenderTime < 0) {
            firstRenderTime = timeSinceLastVisible;
        }

        int width = width();
        int height = height();

        guiGraphics.fill(0, 0, width, height, 0xE8202020);
        guiGraphics.fill(0, 0, width, 1, 0xFFFFD24A);
        guiGraphics.fill(0, height - 1, width, height, 0xFFFFD24A);
        guiGraphics.fill(0, 0, 1, height, 0xFFFFD24A);
        guiGraphics.fill(width - 1, 0, width, height, 0xFFFFD24A);

        var font = toastComponent.getMinecraft().font;
        guiGraphics.drawString(font, Component.translatable("cutenekodragoncore.gui.mailbox.new_mail"),
                6, 6, 0xFFFFD24A, false);
        guiGraphics.drawString(font, npcName, 6, 16, 0xFFFFFFFF, false);
        guiGraphics.drawString(font,
                letterTitle.getString().length() > 24
                        ? letterTitle.getString().substring(0, 22) + "..."
                        : letterTitle.getString(),
                6, 25, 0xFFAAAAAA, false);

        return (timeSinceLastVisible - firstRenderTime) < DISPLAY_TIME_MS ? Visibility.SHOW : Visibility.HIDE;
    }
}
