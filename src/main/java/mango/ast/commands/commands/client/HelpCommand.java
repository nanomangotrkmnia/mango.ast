package mango.ast.commands.commands.client;

import mango.ast.Astralis;
import mango.ast.commands.Command;
import mango.ast.util.render.ChatUtil;

public class HelpCommand extends Command {
    public HelpCommand() {
        super(new String[]{ "help", "h"}, "Shows Information And Usages About Other Commands");
    }

    @Override
    public void execute(String[] args, String message) {
        for (Command command : Astralis.getInstance().getCommandManager().getObjects()) {
            ChatUtil.print("Expresion: " + command.getExpressions()[0]);
            ChatUtil.print("Description: " + command.getDescription());
        }
    }
}
