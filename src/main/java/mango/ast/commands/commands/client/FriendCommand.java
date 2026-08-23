package mango.ast.commands.commands.client;

import mango.ast.Astralis;
import mango.ast.commands.Command;
import mango.ast.friends.FriendManager;
import mango.ast.util.render.ChatUtil;

public class FriendCommand extends Command {
    public FriendCommand() {
        super(new String[]{ "friend", "f"}, "Whitelists ppl from certain modules (.friend <add or remove> <name>");
    }

    @Override
    public void execute(String[] args, String message) {
        String[] words = message.split(" ");
        FriendManager friendManager = Astralis.getInstance().getFriendManager();

        if (words.length < 2) {
            ChatUtil.print("Wrong Usage .friend <add or remove> <name>");
            return;
        }

        switch (words[1]) {
            case "add" -> {
                friendManager.addFriend(words[2]);
                ChatUtil.print("Added " + words[2] + " As a Friend");
            }
            case "remove" -> {
                friendManager.remove(words[2]);
                ChatUtil.print("Removed " + words[2] + " From Friends List");
            }
        }
    }
}
