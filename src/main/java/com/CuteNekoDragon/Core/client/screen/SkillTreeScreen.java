package com.CuteNekoDragon.Core.client.screen;

import com.CuteNekoDragon.Core.client.util.ClientSkillData;
import com.CuteNekoDragon.Core.network.SVONetworkHandler;
import com.CuteNekoDragon.Core.network.packet.ChooseAbilityPacket;
import com.CuteNekoDragon.Core.utils.skills.ability.Ability;
import com.CuteNekoDragon.Core.utils.skills.ability.AbilityRegistry;
import com.CuteNekoDragon.Core.utils.skills.skill.SkillType;
import com.CuteNekoDragon.Core.utils.skills.skill.SkillXPManager;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class SkillTreeScreen extends Screen {

    private static final int TAB_Y = 20;
    private static final int TAB_HEIGHT = 22;
    private static final int LIST_TOP = 62;
    private static final int ROW_HEIGHT = 20;

    private SkillType selectedTree = SkillType.FARMING;

    public SkillTreeScreen() {
        super(Component.literal("Skills"));
    }

    @Override
    protected void init() {
        super.init();
        rebuild();
    }

    private void rebuild() {
        clearWidgets();

        SkillType[] trees = SkillType.values();
        int tabWidth = Math.min(110, (this.width - 20) / trees.length);
        int totalWidth = tabWidth * trees.length;
        int startX = (this.width - totalWidth) / 2;

        for (int i = 0; i < trees.length; i++) {
            SkillType tree = trees[i];
            int x = startX + i * tabWidth;
            Button tab = Button.builder(Component.literal(tree.getDisplayName()), b -> {
                        selectedTree = tree;
                        rebuild();
                    })
                    .bounds(x, TAB_Y, tabWidth - 2, TAB_HEIGHT)
                    .build();
            tab.active = tree != selectedTree;
            addRenderableWidget(tab);
        }

        int level = ClientSkillData.getDATA().getLevel(selectedTree);
        int colWidth = (this.width - 60) / 2;
        int colAX = 30;
        int colBX = 30 + colWidth + 10;
        int rowY = LIST_TOP;

        for (int lvl = 1; lvl <= SkillType.MAX_LEVEL; lvl++) {
            if (!AbilityRegistry.hasChoice(selectedTree, lvl)) {
                rowY += ROW_HEIGHT;
                continue;
            }

            Ability optionA = AbilityRegistry.getAbility(selectedTree, lvl, 0);
            Ability optionB = AbilityRegistry.getAbility(selectedTree, lvl, 1);
            int chosen = ClientSkillData.getDATA().getChosenAbility(selectedTree, lvl);
            boolean unlocked = level >= lvl;

            addRenderableWidget(makeAbilityButton(optionA, lvl, 0, chosen, unlocked, colAX, rowY, colWidth));
            addRenderableWidget(makeAbilityButton(optionB, lvl, 1, chosen, unlocked, colBX, rowY, colWidth));

            rowY += ROW_HEIGHT;
        }
    }

    private Button makeAbilityButton(Ability ability, int level, int option, int chosen, boolean unlocked,
                                     int x, int y, int width) {

        String prefix = "Lv" + level + " ";
        String label;
        if (!unlocked) {
            label = prefix + "[Locked] " + ability.getDescription();
        } else if (chosen == option) {
            label = prefix + "[Selected] " + ability.getDescription();
        } else {
            label = prefix + ability.getDescription();
        }

        Button btn = Button.builder(Component.literal(label), b ->
                SVONetworkHandler.INSTANCE.sendToServer(new ChooseAbilityPacket(selectedTree, level, option)))
                .bounds(x, y, width, ROW_HEIGHT - 2)
                .build();

        btn.active = unlocked && chosen == -1;
        return btn;

    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTick);

        int level = ClientSkillData.getDATA().getLevel(selectedTree);
        int xp = ClientSkillData.getDATA().getXP(selectedTree);

        graphics.drawCenteredString(this.font,
                selectedTree.getDisplayName() + " - Level " + level + "/" + SkillType.MAX_LEVEL,
                this.width / 2, 6, 0xFFFFFF);

        int barX = this.width / 2 - 100, barY = 46, barW = 200, barH = 6;
        if (level < SkillType.MAX_LEVEL) {
            int needed = SkillXPManager.xpForLevel(level + 1);
            graphics.fill(barX, barY, barX + barW, barY + barH, 0xFF404040);
            int filled = (int) (barW * Math.min(1.0, xp / (double) needed));
            graphics.fill(barX, barY, barX + filled, barY + barH, 0xFF55FF55);
            graphics.drawCenteredString(this.font, xp + " / " + needed + " xp", this.width / 2, barY + 10, 0xAAAAAA);
        } else {
            graphics.drawCenteredString(this.font, "MAX LEVEL", this.width / 2, barY + 10, 0xFFD700);
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
