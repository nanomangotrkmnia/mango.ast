package mango.ast.module.impl.combat;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.UpdateEvent;
import mango.ast.module.Category;
import mango.ast.module.Module;
import mango.ast.property.properties.BooleanProperty;
import mango.ast.property.properties.NumberProperty;


public class ReachModule extends Module {

    public final NumberProperty reach = new NumberProperty("Reach", 3.0f, 3.0f, 6.0f, 0.05f);
    public final BooleanProperty block_reach = new BooleanProperty("Modify Block Reach", false);
    public final NumberProperty blockReach = new NumberProperty("Block Reach", 3.0f, 3.0f, 6.0f, 0.05f).setVisible(() -> block_reach.getProperty());

    public ReachModule() {
        super(Category.COMBAT);
        registerProperties(reach, block_reach, blockReach);
    }

    @EventTarget
    public void onTick(UpdateEvent e) {
        setSuffix(reach.getProperty().toString());
    }

}
