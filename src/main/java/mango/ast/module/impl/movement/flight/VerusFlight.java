package mango.ast.module.impl.movement.flight;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.MotionEvent;
import mango.ast.module.Module;
import mango.ast.module.SubModule;
import mango.ast.util.player.MoveUtil;
import net.minecraft.core.Direction;

public class VerusFlight extends SubModule {

    public VerusFlight(Module parentClass) {
        super(parentClass, "Verus Glide");
    }

    @EventTarget
    public void onMotion(MotionEvent event) {
        mc.player.setDeltaMovement(mc.player.getDeltaMovement().with(Direction.Axis.Y, -0.078400001525878));
        if(!mc.player.onGround())
            MoveUtil.strafe(0.37);
    }
}
