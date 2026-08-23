package mango.ast.module.impl.player.inventorymove;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.UpdateEvent;
import mango.ast.module.Module;
import mango.ast.module.SubModule;
import mango.ast.module.impl.player.InventoryMoveModule;
import net.minecraft.client.KeyMapping;

public class IntaveInventoryMove extends SubModule {
    private final InventoryMoveModule parentClass;

    public IntaveInventoryMove(Module parentClass) {
        super(parentClass, "Intave");
        this.parentClass = (InventoryMoveModule) parentClass;
    }

    @EventTarget
    public void onUpdate(UpdateEvent e) {
        if (mc.screen == null || !parentClass.ScreenCheck()) {
            return;
        }

        KeyMapping.setAll();

        mc.options.keyShift.setDown(true);
        mc.options.keyJump.setDown(false);
    }
}
