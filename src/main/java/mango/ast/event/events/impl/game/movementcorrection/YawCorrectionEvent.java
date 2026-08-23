package mango.ast.event.events.impl.game.movementcorrection;

import mango.ast.event.events.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class YawCorrectionEvent implements Event {
    private float yaw;
}
