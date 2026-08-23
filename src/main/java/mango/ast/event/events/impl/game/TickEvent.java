package mango.ast.event.events.impl.game;

import mango.ast.event.events.callables.EventDual;
import mango.ast.event.types.EventModes;

public class TickEvent extends EventDual {
    public TickEvent(EventModes eventModes) {
        super(eventModes);
    }
}
