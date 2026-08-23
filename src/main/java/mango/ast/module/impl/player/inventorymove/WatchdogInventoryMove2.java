package mango.ast.module.impl.player.inventorymove;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.UpdateEvent;
import mango.ast.event.events.impl.network.PacketEvent;
import mango.ast.event.types.EventModes;
import mango.ast.module.Module;
import mango.ast.module.SubModule;
import mango.ast.module.impl.player.InventoryMoveModule;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.protocol.game.ServerboundContainerClickPacket;

public class WatchdogInventoryMove2 extends SubModule {
    private final InventoryMoveModule parentClass;

    public WatchdogInventoryMove2(Module parentClass) {
        super(parentClass, "Modern Watchdog 2");
        this.parentClass = (InventoryMoveModule) parentClass;
    }

    private boolean stopMovement = false;
    private int ticksSinceClick = 0;

    @EventTarget
    public void onUpdate(UpdateEvent e) {
        Screen screen = mc.screen;

        if (screen == null || !parentClass.ScreenCheck()) {
            return;
        }

        if (stopMovement) {
            ticksSinceClick++;
            if (ticksSinceClick >= 10) {
                stopMovement = false;
                ticksSinceClick = 0;
            }
        }

        if (stopMovement) {
            mc.options.keyUp.setDown(false);
            mc.options.keyDown.setDown(false);
            mc.options.keyLeft.setDown(false);
            mc.options.keyRight.setDown(false);
            mc.options.keyJump.setDown(false);
            mc.options.keySprint.setDown(false);
            return;
        }

        KeyMapping.setAll();

        if (!parentClass.allowJumping.getProperty()) {
            mc.options.keyJump.setDown(false);
        }

        if (!parentClass.allowSneaking.getProperty()) {
            mc.options.keyShift.setDown(false);
        }

        if (!parentClass.allowSprinting.getProperty()) {
            mc.options.keySprint.setDown(false);
            mc.player.setSprinting(false);
        }
    }

    @EventTarget
    public void onPacket(PacketEvent e) {
        if (e.getEventMode() == EventModes.RECEIVE || mc.level == null) {
            return;
        }

        if (e.getPacket() instanceof ServerboundContainerClickPacket) {
            stopMovement = true;
            ticksSinceClick = 0;
        }
    }
}
