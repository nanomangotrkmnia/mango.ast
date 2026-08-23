package mango.ast.module.impl.visual;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.HurtCamEvent;
import mango.ast.module.Category;
import mango.ast.module.Module;

public class NoHurtCamModule extends Module {
    public NoHurtCamModule() {
        super(Category.VISUAL);
    }

    @EventTarget
    public void onHurtCam(HurtCamEvent event) {
        event.setCancelled(true);
    }
}
