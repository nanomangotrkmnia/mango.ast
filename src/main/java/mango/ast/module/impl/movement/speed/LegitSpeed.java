package mango.ast.module.impl.movement.speed;

import mango.ast.Astralis;
import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.input.InputTickEvent;
import mango.ast.module.Module;
import mango.ast.module.SubModule;
import mango.ast.module.impl.movement.ScaffoldRecodeModule;

public class LegitSpeed extends SubModule {

    public LegitSpeed(Module parentClass) {
        super(parentClass, "Legit");
    }

    @EventTarget
    public void onUpdate(InputTickEvent event) {
        if (Astralis.getInstance().getModuleManager().getModule(ScaffoldRecodeModule.class).isToggled())
            return;

       event.jump = true;
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
