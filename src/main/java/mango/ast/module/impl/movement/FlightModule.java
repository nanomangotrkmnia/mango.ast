package mango.ast.module.impl.movement;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.MotionEvent;
import mango.ast.module.Category;
import mango.ast.module.Module;
import mango.ast.module.impl.movement.flight.*;
import mango.ast.property.properties.BooleanProperty;
import mango.ast.property.properties.ClassModeProperty;
import mango.ast.util.player.MoveUtil;

public class FlightModule extends Module {
    private final ClassModeProperty classModeProperty = new ClassModeProperty("Mode",
            new AirWalkFlight(this), new BlockFlight(this),
            new VanillaFlight(this), new VulcanFlight(this),
            new CubecraftFlight(this), new GrimFlight(this),
            new VerusFlight(this)
    );
    private final BooleanProperty stopOnDisable = new BooleanProperty("Stop on Disable",true);

    public FlightModule() {
        super(Category.MOVEMENT);
        registerProperties(classModeProperty,
                stopOnDisable
        );
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable(){
        if (stopOnDisable.getProperty())
            MoveUtil.stop();

        super.onDisable();
    }

    @EventTarget
    public void onMotion(MotionEvent event) {
        this.setSuffix(classModeProperty.getProperty().getName());
    }
}