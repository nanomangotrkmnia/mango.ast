package mango.ast.event.events.impl.game;

import mango.ast.event.events.callables.EventCancellable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlowDownEvent extends EventCancellable {
    public float slowDown;
}
