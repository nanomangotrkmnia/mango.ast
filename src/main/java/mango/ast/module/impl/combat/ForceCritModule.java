package mango.ast.module.impl.combat;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.EntityInteractEvent;
import mango.ast.event.types.EventModes;
import mango.ast.module.Category;
import mango.ast.module.Module;

public class ForceCritModule extends Module {

    public ForceCritModule() {
        super(Category.COMBAT);
    }

    @EventTarget
    public void onAttack(EntityInteractEvent event) {
        if (event.getEventMode() == EventModes.PRE && !mc.player.onGround()) {
            mc.player.setSprinting(false);
        }
    }
}
