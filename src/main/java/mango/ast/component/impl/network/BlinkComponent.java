package mango.ast.component.impl.network;

import mango.ast.component.Component;
import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.network.PacketEvent;
import mango.ast.event.types.EventModes;
import mango.ast.util.network.PacketUtil;
import mango.ast.util.render.ChatUtil;
import lombok.Getter;
import net.minecraft.network.protocol.Packet;
import java.util.LinkedList;
import java.util.Queue;

public class BlinkComponent extends Component {
    private final Queue<Packet<?>> packets = new LinkedList<>();
    @Getter
    private boolean blinking = false;

    public void startBlinking() {
        if (blinking)
            return;

        ChatUtil.printDebug("§b[blink] started");
        packets.clear();
        blinking = true;
        onEnable();
    }

    public void stopBlinking() {
        if (!blinking)
            return;

        ChatUtil.printDebug("§b[blink] stopped");
        blinking = false;
        onDisable();
    }

    @EventTarget
    public void onPacket(PacketEvent event) {
        if (!blinking || event.getEventMode() == EventModes.RECEIVE || mc.level == null) {
            return;
        }

        event.setCancelled(true);
        packets.add(event.getPacket());
    }

    @Override
    public void onEnable() {
        if (blinking) {
            super.onEnable();
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();

        while (!packets.isEmpty()) {
            PacketUtil.send(packets.poll());
        }
    }
}
