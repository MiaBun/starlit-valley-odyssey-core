package com.CuteNekoDragon.Core.common.data;

import net.minecraft.commands.CommandSourceStack;

import com.CuteNekoDragon.Core.common.command.MailCommands;
import com.mojang.brigadier.CommandDispatcher;

public class SVOCommands {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        MailCommands.register(dispatcher);
    }
}
