package mango.ast.module.impl.client;

import mango.ast.module.Category;
import mango.ast.module.Module;
import mango.ast.property.properties.BooleanProperty;

public class RotationsModule extends Module {
    public final BooleanProperty movementCorrection = new BooleanProperty("Movement Correction", false),
        oldHitBoxOffset = new BooleanProperty("Old Hit Box Offset", false),
        modernHitVec = new BooleanProperty("Modern Hit Vec", true);

    public RotationsModule() {
        super(Category.PLAYER);
        this.registerProperties(movementCorrection, oldHitBoxOffset, modernHitVec);
    }
}
