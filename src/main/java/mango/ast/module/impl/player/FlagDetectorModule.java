package mango.ast.module.impl.player;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.network.PacketEvent;
import mango.ast.module.Category;
import mango.ast.module.Module;
import mango.ast.util.render.ChatUtil;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;

public class FlagDetectorModule extends Module {
    public FlagDetectorModule() {
        super(Category.PLAYER);
    }

    @EventTarget
    public void onPacket(PacketEvent event)  {
        if (event.getPacket() instanceof ClientboundPlayerPositionPacket) {
            ChatUtil.print("Flag detected!");
        }
    }
}
