package mango.ast.module.impl.movement.flight;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.BlockShapeEvent;
import mango.ast.event.events.impl.game.MotionEvent;
import mango.ast.module.Module;
import mango.ast.module.SubModule;
import mango.ast.property.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.Shapes;

public class AirWalkFlight extends SubModule {
    private final BooleanProperty spoofGround = new BooleanProperty("Spoof Ground", false);

    public AirWalkFlight(Module parentClass) {
        super(parentClass, "Air Walk");
        this.registerPropertyToParentClass(spoofGround);
    }

    @EventTarget
    public void onMotion(MotionEvent event) {
        if (spoofGround.getProperty())
            event.setOnGround(true);
    }

    @EventTarget
    public void onBlockShape(BlockShapeEvent event) {
        if (event.getPos().getY() < mc.player.getBlockY()) {
            event.setShape(Shapes.block());
        }
    }
}
