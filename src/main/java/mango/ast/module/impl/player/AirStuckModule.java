package mango.ast.module.impl.player;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.MotionEvent;
import mango.ast.event.events.impl.game.UpdateEvent;
import mango.ast.event.events.impl.input.InputTickEvent;
import mango.ast.event.events.impl.network.PacketEvent;
import mango.ast.event.types.EventModes;
import mango.ast.module.Category;
import mango.ast.module.Module;
import mango.ast.property.properties.ModeProperty;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;

public class AirStuckModule extends Module {
    private final ModeProperty mode = new ModeProperty("Mode", "Cancel", "Cancel", "Stop", "Stop 2");
    public AirStuckModule() {
        super(Category.EXPLOIT);
        registerProperty(mode);
    }

    @EventTarget
    public void onUpdate(UpdateEvent event) {
        switch (mode.getProperty()) {
            case "Cancel" -> event.setCancelled(true);
            case "Stop", "Stop 2" -> mc.player.setDeltaMovement(0, 0, 0);
        }
    }

    @EventTarget
    public void onInput(InputTickEvent event) {
        if (mode.is("Stop")) {
            event.sprint = false;
            event.jump = false;
            event.shift = false;
            event.left = false;
            event.right = false;
            event.up = false;
            event.down = false;
        }
    }

    @EventTarget
    public void onMotion(MotionEvent event) {
        if (mode.is("Stop"))
            event.setCancelled(true);
    }

    @EventTarget
    public void onPacket(PacketEvent event) {
        if (event.getEventMode() == EventModes.RECEIVE || mc.level == null) {
            return;
        }

        if (mode.is("Stop 2") && event.getPacket() instanceof ServerboundMovePlayerPacket)
            event.setCancelled(true);
    }
}
