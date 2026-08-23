package mango.ast.component.impl.client;

import mango.ast.component.Component;
import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.network.PacketEvent;
import mango.ast.util.render.ChatUtil;
import net.minecraft.network.protocol.game.ClientboundSystemChatPacket;

/**
 * @author Kawase
 * @since 31.08.2025
 */
public class BanDetectorComponent extends Component {
    @EventTarget
    public void onPacket(PacketEvent packetEvent) {
        if (packetEvent.getPacket() instanceof ClientboundSystemChatPacket gameMessageS2CPacket &&
                gameMessageS2CPacket.content().getString().equalsIgnoreCase("An exception occurred in your connection, so you have been routed to limbo!")
        ) {
            ChatUtil.print("Limbo Detected at" + System.currentTimeMillis());
        }
    }
}
