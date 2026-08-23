package mango.ast.module.impl.movement.scaffold.sprints;

import mango.ast.Astralis;
import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.MotionEvent;
import mango.ast.module.Module;
import mango.ast.module.SubModule;
import mango.ast.module.impl.movement.ScaffoldWalkModule;
import mango.ast.module.impl.movement.SpeedModule;
import mango.ast.util.player.MoveUtil;

public class WatchdogSafeSprint extends SubModule {
    private final ScaffoldWalkModule sc = (ScaffoldWalkModule) getParentClass();

    public WatchdogSafeSprint(Module parentClass) {
        super(parentClass,"Watchdog Safe");
    }
    //so this class is useless either u redo it or remove it

    @EventTarget
    public void onMotion(MotionEvent event) {
        SpeedModule speedModule = Astralis.getInstance().getModuleManager().getModule(SpeedModule.class);

        if (mc.options.keyJump.isDown()) {
            return;
        }

        if (MoveUtil.isGoingDiagonally()) {
            mc.player.setDeltaMovement(mc.player.getDeltaMovement().x * 0.98, mc.player.getDeltaMovement().y, mc.player.getDeltaMovement().z * 0.98);
        }

        if (MoveUtil.isPressingForwardAndStrafe()) {
            if (speedModule.isToggled())
                speedModule.toggle();
            mc.player.setDeltaMovement(mc.player.getDeltaMovement().x * 0.5, mc.player.getDeltaMovement().y, mc.player.getDeltaMovement().z * 0.5);
        }
    }
}
