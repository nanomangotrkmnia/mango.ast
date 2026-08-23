package mango.ast.event.events.impl.network;

import mango.ast.event.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BackendMessageEvent implements Event {
    private final String message;
}
