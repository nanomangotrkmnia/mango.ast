package mango.ast.event.events.impl.network;

import mango.ast.event.events.callables.EventDual;
import mango.ast.event.types.EventModes;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.network.protocol.Packet;

@Getter
@Setter
public class PacketEvent extends EventDual {
    private Packet<?> packet;

    public PacketEvent(Packet<?> packet, EventModes eventModes) {
        super(eventModes);
        this.packet = packet;
    }
}
