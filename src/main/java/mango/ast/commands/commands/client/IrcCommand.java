package mango.ast.commands.commands.client;

import mango.ast.Astralis;
import mango.ast.commands.Command;
import mango.ast.util.render.ChatUtil;
import club.serenityutils.packets.impl.BroadcastMessagePacket;
import club.serenityutils.packets.impl.GlobalIRCMessagePacket;

import java.util.Arrays;

public class IrcCommand extends Command {
    public IrcCommand() {
        super(new String[]{ "irc", "i" }, "Sends a message to all client users (.irc <message>");
    }

    @Override
    public void execute(String[] args, String message) {
        if (args.length < 1) {
            ChatUtil.print("Wrong Usage .irc <Message>");
            return;
        }

        // test 2
        String joined = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        new BroadcastMessagePacket(joined).sendPacket(Astralis.getInstance().getClient());
        new GlobalIRCMessagePacket(joined).sendPacket(Astralis.getInstance().getClient());
    }
}
