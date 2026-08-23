package mango.ast.module.impl.movement.scaffold.sprints;

import mango.ast.Astralis;
import mango.ast.component.impl.network.BlinkComponent;
import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.TickEvent;
import mango.ast.module.Module;
import mango.ast.module.SubModule;
import mango.ast.module.impl.movement.ScaffoldWalkModule;
import mango.ast.util.math.TimeUtil;

public class ModernWatchdogSprint extends SubModule {
    private final ScaffoldWalkModule sc = (ScaffoldWalkModule) getParentClass();
    private final TimeUtil timeUtil = new TimeUtil();
    private final BlinkComponent blinkComponent = Astralis.getInstance().getComponentManager().getComponent(BlinkComponent.class);
    private boolean hasBlinked = false;

    public ModernWatchdogSprint(Module parentClass) {
        super(parentClass, "Modern Watchdog");
    }

    @Override
    public void onDisable() {
        blinkComponent.stopBlinking();
        timeUtil.reset();
        hasBlinked = false;
        super.onDisable();
    }

    @EventTarget
    public void onTick(TickEvent event) {
        if (!hasBlinked) {
            blinkComponent.startBlinking();
            timeUtil.reset();
            hasBlinked = true;
        } else if (timeUtil.finished(1000)) {
            blinkComponent.stopBlinking();
        }
    }
}

