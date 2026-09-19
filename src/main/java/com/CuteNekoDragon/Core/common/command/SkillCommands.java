package com.CuteNekoDragon.Core.common.command;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import com.CuteNekoDragon.Core.common.capability.SkillCapability;
import com.CuteNekoDragon.Core.utils.skills.ability.Ability;
import com.CuteNekoDragon.Core.utils.skills.ability.AbilityRegistry;
import com.CuteNekoDragon.Core.utils.skills.skill.SkillType;
import com.CuteNekoDragon.Core.utils.skills.skill.SkillXPManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

public class SkillCommands {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("skills")
                .then(Commands.literal("info").executes(SkillCommands::info))
                .then(Commands.literal("setlevel")
                        .requires(source -> source.hasPermission(2))
                        .then(Commands.argument("player", EntityArgument.player())
                                .then(Commands.argument("tree", StringArgumentType.word())
                                        .then(Commands
                                                .argument("level", IntegerArgumentType.integer(0, SkillType.MAX_LEVEL))
                                                .executes(SkillCommands::setLevel))))));
    }

    public static int info(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer player = ctx.getSource().getPlayerOrException();
        player.getCapability(SkillCapability.SKILL_DATA).ifPresent(data -> {
            for (SkillType type : SkillType.values()) {
                player.sendSystemMessage(Component.literal(
                        type.getDisplayName() + ": Lv " + data.getLevel(type) + " (" + data.getXP(type) + " xp)"));
            }
        });
        return 1;
    }

    public static int choose(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer player = ctx.getSource().getPlayerOrException();
        SkillType type;
        try {
            type = SkillType.valueOf(StringArgumentType.getString(ctx, "tree").toUpperCase());
        } catch (IllegalArgumentException e) {
            ctx.getSource().sendFailure(Component.literal("Unknown skill tree."));
            return 0;
        }
        int level = IntegerArgumentType.getInteger(ctx, "level");
        String optionStr = StringArgumentType.getString(ctx, "option").toUpperCase();
        int option = optionStr.equals("A") ? 0 : optionStr.equals("B") ? 1 : -1;
        if (option == -1) {
            ctx.getSource().sendFailure(Component.literal("Option must be A or B."));
            return 0;
        }

        SkillType finalType = type;
        player.getCapability(SkillCapability.SKILL_DATA).ifPresent(data -> {
            if (data.getLevel(finalType) < level) {
                ctx.getSource().sendFailure(Component.literal("You have not reached that level yet."));
                return;
            }
            if (data.getChosenAbility(finalType, level) != -1) {
                ctx.getSource().sendFailure(Component.literal("You already chose an ability at this level."));
                return;
            }
            Ability ability = AbilityRegistry.getAbility(finalType, level, option);
            if (ability == null) {
                ctx.getSource().sendFailure(Component.literal("No ability defined for that level."));
                return;
            }
            data.setChosenAbility(finalType, level, option);
            ability.onChosen(player);
            player.sendSystemMessage(Component.literal("Chose: " + ability.getDescription()));
        });

        SkillXPManager.sendTo(player);
        return 1;
    }

    public static int setLevel(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer player = EntityArgument.getPlayer(ctx, "player");
        SkillType type;
        try {
            type = SkillType.valueOf(StringArgumentType.getString(ctx, "tree").toUpperCase());
        } catch (IllegalArgumentException e) {
            ctx.getSource().sendFailure(Component.literal("Unknown skill tree."));
            return 0;
        }
        int level = IntegerArgumentType.getInteger(ctx, "level");

        SkillType finalType = type;
        player.getCapability(SkillCapability.SKILL_DATA).ifPresent(data -> {
            data.setLevel(finalType, level);
            ctx.getSource().sendSuccess(() -> Component.literal(
                    "Set " + player.getName().getString() + "'s " + finalType.getDisplayName() + " to level " + level),
                    true);
        });

        SkillXPManager.sendTo(player);
        return 1;
    }
}
