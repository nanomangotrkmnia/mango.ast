package mango.ast.event.events.impl.render;

import mango.ast.event.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ShaderEvent implements Event {
    private final float width, height;
}
