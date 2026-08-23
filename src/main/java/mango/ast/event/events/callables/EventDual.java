package mango.ast.event.events.callables;

import mango.ast.event.types.EventModes;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class EventDual extends EventCancellable {
    public EventModes eventMode;
}
