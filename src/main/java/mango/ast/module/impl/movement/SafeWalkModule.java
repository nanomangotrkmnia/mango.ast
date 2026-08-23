package mango.ast.module.impl.movement;

import mango.ast.module.Category;
import mango.ast.module.Module;
import mango.ast.property.properties.BooleanProperty;


public class SafeWalkModule extends Module {
    public final BooleanProperty blocksOnly = new BooleanProperty("Blocks only", false);
    public final BooleanProperty pitchCheck = new BooleanProperty("Pitch check", false);
    public final BooleanProperty backwards = new BooleanProperty("Backwards Only", false);

    public SafeWalkModule() {
        super(Category.MOVEMENT);
        registerProperties(blocksOnly, pitchCheck,backwards);
    }
}
