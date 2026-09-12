package com.CuteNekoDragon.Core.common.command;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import com.CuteNekoDragon.Core.utils.mail.LetterTemplate;
import com.CuteNekoDragon.Core.utils.mail.LetterTemplateLoader;
import com.CuteNekoDragon.Core.utils.mail.MailService;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

public final class MailCommands {

    private MailCommands() {}

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("svomail")
                .requires(source -> source.hasPermission(2))
                .then(Commands.literal("send")
                        .then(Commands.argument("players", EntityArgument.players())
                                .then(Commands.argument("letter", ResourceLocationArgument.id())
                                        .suggests(MailCommands::suggestLetterIds)
                                        .executes(ctx -> sendLetter(ctx, null))
                                        .then(Commands.argument("npcName", StringArgumentType.greedyString())
                                                .executes(ctx -> sendLetter(ctx,
                                                        StringArgumentType.getString(ctx, "npcName")))))))
                .then(Commands.literal("list")
                        .executes(MailCommands::listLetters)));
    }

    private static CompletableFuture<Suggestions> suggestLetterIds(CommandContext<CommandSourceStack> ctx,
                                                                   SuggestionsBuilder builder) {
        return SharedSuggestionProvider.suggestResource(LetterTemplateLoader.getAllTemplates().keySet(), builder);
    }

    private static int sendLetter(CommandContext<CommandSourceStack> ctx, @Nullable String npcNameOverride)
                                                                                                            throws CommandSyntaxException {
        Collection<ServerPlayer> targets = EntityArgument.getPlayers(ctx, "players");
        ResourceLocation letterId = ResourceLocationArgument.getId(ctx, "letter");

        LetterTemplate template = LetterTemplateLoader.getTemplate(letterId);
        if (template == null) {
            ctx.getSource().sendFailure(Component.literal(
                    "No letter template found with id '" + letterId + "'. Run /svogtmail list to see loaded ids."));
            return 0;
        }

        ResourceLocation npcId = template.getDefaultNpcId();
        Component npcName = Component.literal(npcNameOverride != null ? npcNameOverride : prettify(npcId));

        int sentCount = 0;
        for (ServerPlayer target : targets) {
            boolean sent = MailService.sendLetter(target, letterId, npcId, npcName);
            if (sent) {
                sentCount++;
                ctx.getSource().sendSuccess(() -> Component.literal(
                        "Sent '" + letterId + "' to " + target.getName().getString()), true);
            } else {
                ctx.getSource().sendFailure(Component.literal(
                        "Failed to send '" + letterId + "' to " + target.getName().getString()));
            }
        }
        return sentCount;
    }

    private static int listLetters(CommandContext<CommandSourceStack> ctx) {
        var templates = LetterTemplateLoader.getAllTemplates();
        if (templates.isEmpty()) {
            ctx.getSource().sendSuccess(() -> Component.literal(
                    "No letter templates are loaded (check data/<modid>/svogt_letters/*.md)."), false);
            return 0;
        }

        ctx.getSource().sendSuccess(() -> Component.literal(
                "Loaded letter templates (" + templates.size() + "):"), false);
        for (ResourceLocation id : templates.keySet()) {
            ctx.getSource().sendSuccess(() -> Component.literal(" - " + id), false);
        }
        return templates.size();
    }

    private static String prettify(ResourceLocation id) {
        String[] words = id.getPath().replace('_', ' ').split(" ");
        return Arrays.stream(words)
                .filter(w -> !w.isEmpty())
                .map(w -> Character.toUpperCase(w.charAt(0)) + w.substring(1))
                .collect(Collectors.joining(" "));
    }
}
