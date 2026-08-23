package mango.ast.module.impl.movement.speed;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.MotionEvent;
import mango.ast.module.Module;
import mango.ast.module.SubModule;
import mango.ast.util.player.MoveUtil;

public class MushSpeed extends SubModule {
    public MushSpeed(Module parentClass) {
        super(parentClass,"Mush");
    }

    @EventTarget
    public void onMotion(MotionEvent event) {
        if (mc.player.onGround())
            mc.player.jumpFromGround();
        else
            MoveUtil.strafe();

        /*if (mc.player.hurtTime != 0) {
            PlayerUtil.setMotionY(PlayerUtil.getMotionY() - 0.80);
        }*/
    }
}

