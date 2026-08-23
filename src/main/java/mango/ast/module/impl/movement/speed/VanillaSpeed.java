package mango.ast.module.impl.movement.speed;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.MotionEvent;
import mango.ast.module.Module;
import mango.ast.module.SubModule;
import mango.ast.property.properties.NumberProperty;
import mango.ast.util.player.MoveUtil;

public class VanillaSpeed extends SubModule {
    private final NumberProperty speed = new NumberProperty("Speed", 1, 0, 10, 0.1f);

    public VanillaSpeed(Module parentClass) {
        super(parentClass,"Vanilla");
        registerPropertyToParentClass(speed);
    }

    @EventTarget
    public void onMotion(MotionEvent event) {
        if (mc.player.onGround()) {
            mc.player.jumpFromGround();
        }

        MoveUtil.strafe(speed.getProperty().floatValue());
    }
}