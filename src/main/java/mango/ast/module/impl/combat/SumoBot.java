package mango.ast.module.impl.combat;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.UpdateEvent;
import mango.ast.module.Category;
import mango.ast.module.Module;

public class SumoBot extends Module {

    public SumoBot() {
        super(Category.COMBAT);
    }

    @EventTarget
    public void onUpdate(UpdateEvent event) {

    }
}
