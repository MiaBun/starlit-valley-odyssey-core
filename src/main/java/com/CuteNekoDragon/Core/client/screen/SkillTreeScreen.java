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

import java.util.ArrayList;
import java.util.List;

public class SkillTreeScreen extends Screen {

    private static final int TAB_Y = 20;
    private static final int TAB_HEIGHT = 22;
    private static final int LIST_TOP = 70;
    private static final int LIST_BOTTOM_MARGIN = 10;
    private static final int ROW_HEIGHT = 20;
    private static final int MIN_TWO_COLUMN_WIDTH = 500;
    private static final int MIN_COL_WIDTH = 150;
    private static final int SCROLLBAR_WIDTH = 4;
    private static final double SCROLL_SPEED = ROW_HEIGHT * 1.5;

    private SkillType selectedTree = SkillType.FARMING;

    private final List<Button> abilityButtons = new ArrayList<>();
    private double scrollOffset = 0;
    private int maxScroll = 0;
    private int contentHeight = 0;
    private int lastKnownLevel = -1;
    private int lastChosenSignature = 0;

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
        abilityButtons.clear();

        SkillType[] trees = SkillType.values();
        int tabWidth = Math.min(110, (this.width - 20) / trees.length);
        int totalWidth = tabWidth * trees.length;
        int startX = (this.width - totalWidth) / 2;

        for (int i = 0; i < trees.length; i++) {
            SkillType tree = trees[i];
            int x = startX + i * tabWidth;
            Button tab = Button.builder(Component.literal(tree.getDisplayName()), b -> {
                        selectedTree = tree;
                        scrollOffset = 0;
                        rebuild();
                    })
                    .bounds(x, TAB_Y, tabWidth - 2, TAB_HEIGHT)
                    .build();
            tab.active = tree != selectedTree;
            addRenderableWidget(tab);
        }

        int level = ClientSkillData.getDATA().getLevel(selectedTree);
        lastKnownLevel = level;
        lastChosenSignature = computeChosenSignature(selectedTree);

        boolean twoColumn = this.width >= MIN_TWO_COLUMN_WIDTH;

        int colWidth, colAX, colBX;
        if (twoColumn) {
            colWidth = Math.max(MIN_COL_WIDTH, (this.width - 550) / 2);
            int totalContentWidth = colWidth * 2 + 10;
            colAX = (this.width - totalContentWidth) / 2;
            colBX = colAX + colWidth + 10;
        } else {
            colWidth = Math.max(MIN_COL_WIDTH, this.width - 60);
            colAX = (this.width - colWidth) / 2;
            colBX = colAX;
        }

        List<Integer> displayLevels = new ArrayList<>();
        for (int lvl = level; lvl >= 1; lvl--) {
            if (AbilityRegistry.hasChoice(selectedTree, lvl)) {
                displayLevels.add(lvl);
            }
        }

        int rowsCount = twoColumn ? displayLevels.size() : displayLevels.size() * 2;
        contentHeight = rowsCount * ROW_HEIGHT;

        int listBottom = this.height - LIST_BOTTOM_MARGIN;
        int visibleHeight = Math.max(0, listBottom - LIST_TOP);
        maxScroll = Math.max(0, contentHeight - visibleHeight);
        scrollOffset = Math.max(0, Math.min(scrollOffset, maxScroll));

        int rowY = LIST_TOP - (int) scrollOffset;

        for (int lvl : displayLevels) {
            Ability optionA = AbilityRegistry.getAbility(selectedTree, lvl, 0);
            Ability optionB = AbilityRegistry.getAbility(selectedTree, lvl, 1);
            int chosen = ClientSkillData.getDATA().getChosenAbility(selectedTree, lvl);

            if (twoColumn) {
                abilityButtons.add(makeAbilityButton(optionA, lvl, 0, chosen, colAX, rowY, colWidth));
                abilityButtons.add(makeAbilityButton(optionB, lvl, 1, chosen, colBX, rowY, colWidth));
                rowY += ROW_HEIGHT;
            } else {
                abilityButtons.add(makeAbilityButton(optionA, lvl, 0, chosen, colAX, rowY, colWidth));
                rowY += ROW_HEIGHT;
                abilityButtons.add(makeAbilityButton(optionB, lvl, 1, chosen, colAX, rowY, colWidth));
                rowY += ROW_HEIGHT;
            }
        }
    }

    private Button makeAbilityButton(Ability ability, int level, int option, int chosen,
                                     int x, int y, int width) {

        String prefix = "Lv" + level + " ";
        String label;
        if (chosen == option) {
            label = prefix + "[Selected] " + ability.getDescription();
        } else {
            label = prefix + ability.getDescription();
        }

        Button btn = Button.builder(Component.literal(label), b ->
                        SVONetworkHandler.INSTANCE.sendToServer(new ChooseAbilityPacket(selectedTree, level, option)))
                .bounds(x, y, width, ROW_HEIGHT - 2)
                .build();

        btn.active = chosen == -1;
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

        int listBottom = this.height - LIST_BOTTOM_MARGIN;
        graphics.enableScissor(0, LIST_TOP, this.width, listBottom);
        for (Button b : abilityButtons) {
            b.render(graphics, mouseX, mouseY, partialTick);
        }
        graphics.disableScissor();

        if (maxScroll > 0) {
            int visibleHeight = listBottom - LIST_TOP;
            int trackX = this.width - 6;
            graphics.fill(trackX, LIST_TOP, trackX + SCROLLBAR_WIDTH, listBottom, 0x40FFFFFF);

            int thumbHeight = Math.max(20, (int) ((double) visibleHeight / contentHeight * visibleHeight));
            int thumbY = LIST_TOP + (int) ((scrollOffset / maxScroll) * (visibleHeight - thumbHeight));
            graphics.fill(trackX, thumbY, trackX + SCROLLBAR_WIDTH, thumbY + thumbHeight, 0xFFAAAAAA);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int listBottom = this.height - LIST_BOTTOM_MARGIN;
        if (mouseY >= LIST_TOP && mouseY <= listBottom) {
            for (Button b : abilityButtons) {
                if (b.isMouseOver(mouseX, mouseY)) {
                    return b.mouseClicked(mouseX, mouseY, button);
                }
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        if (maxScroll > 0) {
            scrollOffset -= delta * SCROLL_SPEED;
            scrollOffset = Math.max(0, Math.min(scrollOffset, maxScroll));
            rebuild();
            return true;
        }
        return super.mouseScrolled(mouseX, mouseY, delta);
    }

    private int computeChosenSignature(SkillType tree) {
        int hash = 1;
        for (int lvl = 1; lvl <= SkillType.MAX_LEVEL; lvl++) {
            if (AbilityRegistry.hasChoice(tree, lvl)) {
                hash = hash * 31 + ClientSkillData.getDATA().getChosenAbility(tree, lvl);
            }
        }
        return hash;
    }

    @Override
    public void tick() {
        super.tick();
        int level = ClientSkillData.getDATA().getLevel(selectedTree);
        int chosenSignature = computeChosenSignature(selectedTree);
        if (level != lastKnownLevel || chosenSignature != lastChosenSignature) {
            rebuild();
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}