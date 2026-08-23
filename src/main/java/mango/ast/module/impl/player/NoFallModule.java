package mango.ast.module.impl.player;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.MotionEvent;
import mango.ast.module.Category;
import mango.ast.module.Module;
import mango.ast.module.impl.player.nofall.*;
import mango.ast.property.properties.ClassModeProperty;

public class NoFallModule extends Module {

    public final ClassModeProperty mode = new ClassModeProperty("Mode",
            new GroundNofall(this), new UniversalNofall(this),
            new VulcanNofall(this), new WatchdogNofall(this), new BlocksMcNofall(this)
    );

    public NoFallModule() {
        super(Category.PLAYER);
        this.registerProperty(mode);
    }

    @EventTarget
    public void onMotion(MotionEvent event) {
        this.setSuffix(mode.getProperty().getFormatedName());
    }
}
