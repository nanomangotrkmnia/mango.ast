package mango.ast.module.impl.player.nofall;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.MotionEvent;
import mango.ast.module.Module;
import mango.ast.module.SubModule;

public class GroundNofall extends SubModule {

    public GroundNofall(Module parentClass)  {
        super(parentClass,"Ground");
    }

    @EventTarget
    public void onMotion(MotionEvent event) {
        event.setOnGround(true);
    }
}
