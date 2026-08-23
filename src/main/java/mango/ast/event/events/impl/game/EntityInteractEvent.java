package mango.ast.event.events.impl.game;

import mango.ast.event.events.callables.EventDual;
import mango.ast.event.types.EventModes;
import lombok.Getter;
import net.minecraft.world.entity.Entity;

@Getter
public class EntityInteractEvent extends EventDual {
    private final Entity target;

    public EntityInteractEvent(Entity target, EventModes eventMode) {
        super(eventMode);
        this.target = target;
    }
}
