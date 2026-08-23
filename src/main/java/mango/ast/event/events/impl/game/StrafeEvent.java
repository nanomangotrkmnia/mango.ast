package mango.ast.event.events.impl.game;

import mango.ast.event.events.callables.EventCancellable;
import mango.ast.event.events.callables.EventDual;
import mango.ast.event.types.EventModes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.world.phys.Vec3;

@Getter
@Setter
public class StrafeEvent extends EventDual {
    private final Vec3 type;

    public StrafeEvent(Vec3 type, EventModes eventModes) {
        super(eventModes);
        this.type = type;
    }
}
