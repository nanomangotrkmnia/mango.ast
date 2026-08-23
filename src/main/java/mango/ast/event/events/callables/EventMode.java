package mango.ast.event.events.callables;

import mango.ast.event.events.Event;
import mango.ast.event.types.EventModes;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EventMode implements Event {
    public EventModes eventType;
}
