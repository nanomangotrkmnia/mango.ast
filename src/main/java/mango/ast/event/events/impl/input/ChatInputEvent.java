package mango.ast.event.events.impl.input;

import mango.ast.event.events.callables.EventCancellable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChatInputEvent extends EventCancellable {
    private String input;
}
