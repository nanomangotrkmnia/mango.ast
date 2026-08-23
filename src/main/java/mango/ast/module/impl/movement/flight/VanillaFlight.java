package mango.ast.module.impl.movement.flight;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.MotionEvent;
import mango.ast.module.Module;
import mango.ast.module.SubModule;
import mango.ast.property.properties.NumberProperty;
import mango.ast.util.player.MoveUtil;
import mango.ast.util.player.PlayerUtil;

public class VanillaFlight extends SubModule {
    private final NumberProperty speed = new NumberProperty("Speed", 1, 0, 10, 0.1f);

    public VanillaFlight(Module parentClass) {
        super(parentClass, "Vanilla");
        this.registerPropertyToParentClass(speed);
    }

    // todo: white list from obfuscation.
    @EventTarget
    public void onMotion(MotionEvent event) {
        PlayerUtil.setMotionY((mc.options.keyJump.isDown() ?
                speed.getProperty().floatValue() * 0.6 :
                mc.options.keyShift.isDown() ? -speed.getProperty().floatValue() * 0.6 : 0));

        MoveUtil.strafe(speed.getProperty().floatValue());
    }
}
