package mango.ast.module.impl.movement.scaffold.towers;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.MotionEvent;
import mango.ast.module.Module;
import mango.ast.module.SubModule;
import mango.ast.module.impl.movement.ScaffoldWalkModule;

public class VanillaTower extends SubModule {
    public VanillaTower(Module parentClass) {
        super(parentClass,"Vanilla");
    }

    @EventTarget
    public void onMotion(MotionEvent event) {
        if (!mc.options.keyJump.isDown() || !((ScaffoldWalkModule) getParentClass()).tower.getProperty()) {
            return;
        }
           // mc.player.setVelocity(mc.player.getVelocity().x, 0.42f, mc.player.getVelocity().z);

    }
}