package mango.ast.module.impl.movement.speed;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.MotionEvent;
import mango.ast.module.Module;
import mango.ast.module.SubModule;
import mango.ast.property.properties.NumberProperty;
import mango.ast.util.player.MoveUtil;
import mango.ast.util.player.PlayerUtil;

public class CubecraftSpeed extends SubModule {
    private final NumberProperty cubecraftSpeed = new NumberProperty("Speed", 1, 0, 10, 0.05f);

    public CubecraftSpeed(Module parentClass) {
        super(parentClass, "Cubecraft");
        this.registerPropertyToParentClass(cubecraftSpeed);
    }

    @EventTarget
    public void onMotion(MotionEvent event) {
        if (!MoveUtil.isMoving())
            return;

        if (mc.player.onGround())
            mc.player.jumpFromGround();

        if (!mc.player.horizontalCollision) {
            switch (offGroundTicks) {
                case 1 -> PlayerUtil.setMotionY(PlayerUtil.getMotionY() - 0.5);
                case 5 -> PlayerUtil.setMotionY(PlayerUtil.getMotionY() - 0.4);
            }
        }

        if (mc.player.hurtTime != 0)
            MoveUtil.strafe(cubecraftSpeed.getProperty().floatValue());
        else {
            MoveUtil.strafe(mc.player.onGround() ? 0.55 : MoveUtil.getBaseSpeed());
        }
    }
}
