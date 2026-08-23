package mango.ast.commands.commands.client;

import mango.ast.commands.Command;
import mango.ast.util.render.ChatUtil;

import static mango.ast.interfaces.IAccess.mc;

public class IgnCommand extends Command {
    public IgnCommand() {
        super(new String[]{ "ign", "name","n" }, "Shows you your current name and copies it to youre clip board (.ign)");
    }

    @Override
    public void execute(String[] args, String message) {
        mc.keyboardHandler.setClipboard(mc.getUser().getName());
        ChatUtil.print("Your name is: " + mc.getUser().getName());
    }
}
