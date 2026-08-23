package mango.ast.module.impl.movement.speed;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.MotionEvent;
import mango.ast.module.Module;
import mango.ast.module.SubModule;
import mango.ast.util.player.MoveUtil;

public class MMCSpeed extends SubModule {
    public MMCSpeed(Module parentClass) {
        super(parentClass, "MMC");
    }

    @EventTarget
    public void onMotion(MotionEvent event) {
        if (mc.player.onGround()){
            mc.player.jumpFromGround();
        }

        if (offGroundTicks == 10 && mc.player.hurtTime == 0) {
            MoveUtil.strafe();
        }
    }
}