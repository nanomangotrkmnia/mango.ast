package mango.ast.module.impl.combat;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.LoseSprintEvent;
import mango.ast.module.Category;
import mango.ast.module.Module;
import mango.ast.property.properties.BooleanProperty;

public class KeepSprintModule extends Module {
    private final BooleanProperty groundOnly = new BooleanProperty("Ground Only", false);

    public KeepSprintModule() {
        super(Category.COMBAT);
        this.registerProperty(groundOnly);
    }

    @EventTarget
    public void onLoseSprint(LoseSprintEvent event) {
        if (!groundOnly.getProperty() || mc.player.onGround())
            event.setCancelled(true);
    }
}
