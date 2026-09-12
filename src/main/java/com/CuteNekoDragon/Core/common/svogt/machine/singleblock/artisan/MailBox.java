package com.CuteNekoDragon.Core.common.svogt.machine.singleblock.artisan;

import com.CuteNekoDragon.Core.client.util.ClientMailCache;
import com.CuteNekoDragon.Core.common.capability.MailCapability;
import com.CuteNekoDragon.Core.common.capability.PlayerMailData;
import com.CuteNekoDragon.Core.network.SVONetworkHandler;
import com.CuteNekoDragon.Core.network.packet.SyncMailDataPacket;
import com.CuteNekoDragon.Core.utils.mail.Letter;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.UITemplate;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.IUIMachine;
import com.lowdragmc.lowdraglib.gui.modular.ModularUI;
import com.lowdragmc.lowdraglib.gui.texture.GuiTextureGroup;
import com.lowdragmc.lowdraglib.gui.texture.ResourceBorderTexture;
import com.lowdragmc.lowdraglib.gui.texture.TextTexture;
import com.lowdragmc.lowdraglib.gui.widget.*;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;

public class MailBox extends MetaMachine implements IUIMachine {

    private static final int LIST_WIDTH = 84;
    private static final int PANEL_HEIGHT = 132;
    private static final int CONTENT_WIDTH = 132;
    private static final int ROW_HEIGHT = 16;

    private static final ResourceBorderTexture WOOD_BACKGROUND = new ResourceBorderTexture(
            "minecraft:textures/block/spruce_planks.png", 16, 16, 0, 0);

    private static final ResourceBorderTexture WOOD_PANEL = new ResourceBorderTexture(
            "minecraft:textures/block/stripped_spruce_log.png", 16, 16, 0, 0);

    public MailBox(IMachineBlockEntity holder) {
        super(holder);
    }

    @Override
    public boolean shouldOpenUI(Player player, InteractionHand hand, BlockHitResult hit) {
        if (player instanceof ServerPlayer serverPlayer) {
            PlayerMailData data = MailCapability.getOrDefault(serverPlayer);
            SVONetworkHandler.sendLetterToPlayer(serverPlayer, new SyncMailDataPacket(data.getLetters(), data.isToastEnabled()));
        }
        return true;
    }

    private static List<Letter> getLetters(Player player) {
        return player.level().isClientSide
                ? ClientMailCache.getLetters()
                : MailCapability.getOrDefault(player).getLetters();
    }

    private static boolean getToastEnabled(Player player) {
        return player.level().isClientSide
                ? ClientMailCache.isToastEnabled()
                : MailCapability.getOrDefault(player).isToastEnabled();
    }

    @Override
    public ModularUI createUI(Player entityPlayer) {
        List<Letter> letters = getLetters(entityPlayer);
        boolean clientSide = entityPlayer.level().isClientSide;

        int[] selected = { letters.isEmpty() ? -1 : 0 };

        int totalWidth = 7 + LIST_WIDTH + 4 + CONTENT_WIDTH + 7;
        int totalHeight = 20 + PANEL_HEIGHT + 10 + 76 + 10;

        ModularUI ui = new ModularUI(totalWidth, totalHeight, this, entityPlayer)
                .background(GuiTextures.BACKGROUND)
                .widget(new LabelWidget(7, 6, "gui.svo_core.mailbox.title"));

        WidgetGroup settingsPanel = buildSettingsPanel(entityPlayer);
        settingsPanel.setVisible(false);
        settingsPanel.setActive(false);

        ui.widget(new ButtonWidget(totalWidth - 20, 6, 13, 13,
                new GuiTextureGroup(ResourceBorderTexture.BUTTON_COMMON, new TextTexture("#")),
                clickData -> {
                    boolean nowVisible = !settingsPanel.isVisible();
                    settingsPanel.setVisible(nowVisible);settingsPanel.setActive(nowVisible);

                })
                .setHoverTooltips("Settings"));

        DraggableScrollableWidgetGroup list = new DraggableScrollableWidgetGroup(7, 20, LIST_WIDTH, PANEL_HEIGHT)
                .setBackground(WOOD_PANEL);
        list.setYScrollBarWidth(6);
        list.setYBarStyle(GuiTextures.SLIDER_BACKGROUND_VERTICAL, GuiTextures.BUTTON);

        for (int i = 0; i < letters.size(); i++) {
            Letter letter = letters.get(i);
            int index = i;
            boolean unread = !letter.isRead();

            String label = (unread ? "* " : "") + letter.getTitle().getString();
            ButtonWidget row = new ButtonWidget(2, i * ROW_HEIGHT, LIST_WIDTH - 4 , ROW_HEIGHT - 1,
                    new GuiTextureGroup(ResourceBorderTexture.BUTTON_COMMON,
                            new TextTexture(label)
                                    .setColor(unread ? ChatFormatting.WHITE.getColor() : ChatFormatting.GRAY.getColor())),
                    clickData -> {
                        selected[0] = index;
                        if (!entityPlayer.level().isClientSide) {
                            letter.setRead(true);
                        }
                    });
            list.addWidget(row);
        }

        if (letters.isEmpty()) {
            list.addWidget(new LabelWidget(4, 6, "gui.svo_core.mailbox.no_letters"));
        }
        ui.widget(list);

        DraggableScrollableWidgetGroup content = new DraggableScrollableWidgetGroup(
                7 + LIST_WIDTH + 4, 20, CONTENT_WIDTH, PANEL_HEIGHT)
                .setBackground(WOOD_PANEL);

        content.addWidget(new ComponentPanelWidget(4, 4, out -> appendSelectedBody(out, letters, selected))
                .textSupplier(clientSide ? null : out -> appendSelectedBody(out, letters, selected))
                .setMaxWidthLimit(CONTENT_WIDTH - 12));

        ui.widget(content);

        ui.widget(settingsPanel);

        ui.widget(UITemplate.bindPlayerInventory(entityPlayer.getInventory(), GuiTextures.SLOT,
                7, 20 + PANEL_HEIGHT + 10, true));

        return ui;
    }

    private WidgetGroup buildSettingsPanel(Player entityPlayer) {
        WidgetGroup panel = new WidgetGroup(7, 20, LIST_WIDTH + 4 + CONTENT_WIDTH, PANEL_HEIGHT);
        panel.setBackground(GuiTextures.BACKGROUND);
        panel.addWidget(new LabelWidget(6, 6, "gui.svo_core.mailbox.toast_setting"));
        panel.addWidget(new SwitchWidget(6, 20, 100, 18, (clickData, value) -> {
            if (!entityPlayer.level().isClientSide) {
                MailCapability.getOrDefault(entityPlayer).setToastEnabled(value);
            }
        }).setTexture(
                        new GuiTextureGroup(ResourceBorderTexture.BUTTON_COMMON,
                                new TextTexture("gui.svo_core.mailbox.toast_off")),
                        new GuiTextureGroup(ResourceBorderTexture.BUTTON_COMMON,
                                new TextTexture("gui.svo_core.mailbox.toast_on")))
                .setPressed(getToastEnabled(entityPlayer)));
        return panel;

    }

    private static void appendSelectedBody(List<Component> out, List<Letter> letters, int[] selected) {
        if (selected[0] < 0 || selected[0] >= letters.size()) {
            out.add(Component.translatable("gui.svo_core.mailbox.select_a_letter"));
            return;
        }
        Letter letter = letters.get(selected[0]);
        out.add(letter.getTitle().copy().withStyle(ChatFormatting.BOLD));
        out.add(Component.translatable("gui.svo_core.mailbox.from", letter.getNpcName())
                .withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
        out.add(Component.empty());
        out.addAll(letter.getBody());
    }
}
