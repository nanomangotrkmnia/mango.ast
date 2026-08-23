package mango.ast.commands;

import mango.ast.Astralis;
import mango.ast.commands.commands.client.*;
import mango.ast.commands.commands.player.HclipCommand;
import mango.ast.commands.commands.player.SelfBanCommand;
import mango.ast.commands.commands.player.TpCommand;
import mango.ast.commands.commands.player.VclipCommand;
import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.input.ChatInputEvent;
import mango.ast.manager.Manager;
import mango.ast.util.render.ChatUtil;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public final class CommandManager extends Manager<Command> {
    public CommandManager() {
        Astralis.getInstance().getEventManager().register(this);
    }

    @EventTarget
    public void onChatInputEvent(ChatInputEvent event) {
        String message = event.getInput();

        if (!message.startsWith("."))
            return;

        message = message.substring(1);
        final String[] args = message.split(" ");

        final AtomicBoolean commandFound = new AtomicBoolean(false);

        try {
            String finalMessage = message;
            getObjects().stream().filter(command ->
                            Arrays.stream(command.getExpressions())
                                    .anyMatch(expression -> expression.equalsIgnoreCase(args[0])))
                    .forEach(cmd -> {
                        commandFound.set(true);
                        cmd.execute(args, finalMessage);
                    });
        } catch (final Exception ex) {
            ex.printStackTrace();
        }

        if (!commandFound.get())
            ChatUtil.print("Not found.");

        event.setCancelled(true);
    }

    public void registerCommands() {
        register(
                new BindCommand(), new ToggleModuleCommand(),
                new FriendCommand(), new HelpCommand(),
                new VclipCommand(), new HclipCommand(),
                new IrcCommand(), new ConfigCommand(),
                new IgnCommand(), new TpCommand(),
                new HideCommand(), new SelfBanCommand()
        );
    }
}
