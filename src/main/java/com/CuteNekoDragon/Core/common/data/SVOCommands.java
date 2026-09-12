package com.CuteNekoDragon.Core.common.data;

import com.CuteNekoDragon.Core.common.command.MailCommands;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;

import static net.minecraft.commands.Commands.literal;

public class SVOCommands {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        MailCommands.register(dispatcher);
    }
}
