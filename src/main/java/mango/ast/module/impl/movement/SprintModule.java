package mango.ast.module.impl.movement;

import mango.ast.Astralis;
import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.UpdateEvent;
import mango.ast.module.Category;
import mango.ast.module.Module;
import mango.ast.util.player.MoveUtil;

public class SprintModule extends Module {
    public SprintModule() {
        super(Category.MOVEMENT);
    }

    @EventTarget
    public void onUpdate(UpdateEvent event) {
        if (Astralis.getInstance().getModuleManager().getModule(ScaffoldRecodeModule.class).isToggled()) {
            return;
        }

        mc.options.keySprint.setDown(MoveUtil.isMoving());
    }
}
