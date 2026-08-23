package mango.ast.module.impl.movement.speed;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.MoveEvent;
import mango.ast.module.Module;
import mango.ast.module.SubModule;
import mango.ast.util.player.MoveUtil;

public class WatchdogYPort extends SubModule {

    public WatchdogYPort(Module parentClass) {
        super(parentClass,"Watchdog Yport");
    }

    @EventTarget
    public void onMove(MoveEvent event) {
        if (mc.player.fallDistance > 0.07)
            MoveUtil.stop();

        if (mc.player.onGround()) event.setY(0.003);
        if (mc.player.onGround() || mc.player.getDeltaMovement().y == (0.003 - 0.08) * 0.98F)
            MoveUtil.strafe(event, MoveUtil.getPerfectValue(0.3296F, 0.3689F, 0.4252F));
    }
}
