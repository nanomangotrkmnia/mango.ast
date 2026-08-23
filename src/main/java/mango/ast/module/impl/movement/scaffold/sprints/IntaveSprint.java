package mango.ast.module.impl.movement.scaffold.sprints;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.MotionEvent;
import mango.ast.module.Module;
import mango.ast.module.SubModule;

public class IntaveSprint extends SubModule {
    public IntaveSprint(Module parentClass) {
        super(parentClass,"Intave");
    }

    @EventTarget
    public void onMotion(MotionEvent event) {
        if (mc.player.onGround()) {
            final float multiply = 1.1f;
            mc.player.setDeltaMovement(mc.player.getDeltaMovement().x * multiply, mc.player.getDeltaMovement().y, mc.player.getDeltaMovement().z * multiply);
        }
    }
}
