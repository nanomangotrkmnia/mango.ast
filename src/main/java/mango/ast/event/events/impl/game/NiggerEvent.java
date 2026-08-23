package mango.ast.event.events.impl.game;

import mango.ast.event.events.Event;
import mango.ast.event.events.callables.EventCancellable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NiggerEvent extends EventCancellable {
    private boolean sprint;
}
