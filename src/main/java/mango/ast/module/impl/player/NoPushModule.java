package mango.ast.module.impl.player;

import mango.ast.module.Category;
import mango.ast.module.Module;
import mango.ast.property.properties.BooleanProperty;

public class NoPushModule extends Module {
    public final BooleanProperty playerNoPush = new BooleanProperty("Player No Push", true);
    public final BooleanProperty waterNoPush = new BooleanProperty("Water No Push", false);
    public NoPushModule() {
        super(Category.PLAYER);
        registerProperties(playerNoPush, waterNoPush);
    }
}
