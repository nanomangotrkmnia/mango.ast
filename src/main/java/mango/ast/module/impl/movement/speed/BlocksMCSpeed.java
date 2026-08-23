package mango.ast.module.impl.movement.speed;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.UpdateEvent;
import mango.ast.module.Module;
import mango.ast.module.SubModule;
import mango.ast.util.player.MoveUtil;

public class BlocksMCSpeed extends SubModule {
    public BlocksMCSpeed(Module parentClass) {
        super(parentClass,"Blocks MC");
    }

    @EventTarget
    public void onUpdate(UpdateEvent event) {
        if (mc.player.onGround())
            mc.player.jumpFromGround();

      /*  int simpleY = (int) Math.round((mc.player.getY() % 1) * 10000);


        switch (simpleY) {
            case 13 -> PlayerUtil.setMotionY(PlayerUtil.getMotionY() - 0.02483);
            case 2000 -> PlayerUtil.setMotionY(PlayerUtil.getMotionY() - 0.1913);
        }*/
        MoveUtil.strafe(MoveUtil.getSpeed());

    }
}
