package mango.ast.event.events.impl.game;

import mango.ast.event.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Kawase
 * @since 03.10.2025
 */
@Getter
@Setter
@AllArgsConstructor
public class PlayerJoinEvent implements Event {
    private final long joinTimeMS;
}
