package mango.ast.module.impl.movement;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.MotionEvent;
import mango.ast.module.Category;
import mango.ast.module.Module;
import mango.ast.util.player.MoveUtil;

public class QuickStopModule extends Module {

    public QuickStopModule() {
        super(Category.MOVEMENT);
    }

    @EventTarget
    public void onMotion(MotionEvent event) {
        if (!MoveUtil.isMoving())
            MoveUtil.stop();
    }
}
