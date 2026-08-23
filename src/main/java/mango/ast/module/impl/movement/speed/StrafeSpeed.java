package mango.ast.module.impl.movement.speed;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.MotionEvent;
import mango.ast.module.Module;
import mango.ast.module.SubModule;
import mango.ast.util.player.MoveUtil;

public class StrafeSpeed extends SubModule {
    public StrafeSpeed(Module parentClass) {
        super(parentClass,"Strafe");
    }

    @EventTarget
    public void onMotion(MotionEvent event) {
        if (mc.player.onGround())
            mc.player.jumpFromGround();

        MoveUtil.strafe(MoveUtil.getBaseSpeed());
    }
}
