package mango.ast.commands.commands.player;

import mango.ast.commands.Command;
import mango.ast.util.network.PacketUtil;
import mango.ast.util.render.ChatUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;
import net.minecraft.network.protocol.game.ServerboundSetCarriedItemPacket;

public class SelfBanCommand extends Command {
    public SelfBanCommand() {
        super(new String[]{ "selfban" }, "Automatically bans you on hypixel.");
    }

    @Override
    public void execute(String[] args, String message) {
        ChatUtil.print("ban");
        for (int i = 0; i < 20; i++) {
            PacketUtil.send(new ServerboundSetCarriedItemPacket(0));
            PacketUtil.sendSequenced(sequence -> new ServerboundPlayerActionPacket(ServerboundPlayerActionPacket.Action.RELEASE_USE_ITEM, BlockPos.ZERO.below(), Direction.UP));
        }
    }
}
