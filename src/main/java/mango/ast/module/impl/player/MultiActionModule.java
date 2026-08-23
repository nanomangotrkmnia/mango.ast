package mango.ast.module.impl.player;

import mango.ast.module.Category;
import mango.ast.module.Module;
import mango.ast.property.properties.BooleanProperty;

public class MultiActionModule extends Module {
    public final BooleanProperty placeIfBreaking = new BooleanProperty("Place while mining", false);
    public final BooleanProperty breakIfUsing = new BooleanProperty("Break while using item", false);

    public MultiActionModule() {
        super(Category.PLAYER);
        registerProperties(placeIfBreaking, breakIfUsing);
    }
}
