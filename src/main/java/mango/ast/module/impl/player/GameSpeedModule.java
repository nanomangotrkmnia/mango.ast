package mango.ast.module.impl.player;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.UpdateEvent;
import mango.ast.module.Category;
import mango.ast.module.Module;
import mango.ast.property.properties.ModeProperty;
import mango.ast.property.properties.NumberProperty;
import mango.ast.util.Data;

public class GameSpeedModule extends Module {
    private final ModeProperty mode = new ModeProperty("Mode","Custom", "Normal","Custom");
    private final NumberProperty timer = new NumberProperty("Timer", 1, 0, 10, 0.1f).setVisible(() -> mode.is("Normal"));
    private final NumberProperty groundTimer = new NumberProperty("Ground Timer", 1, 0, 10, 0.1f).setVisible(() -> mode.is("Custom"));
    private final NumberProperty airTimer = new NumberProperty("Air Timer", 1, 0, 10, 0.1f).setVisible(() -> mode.is("Custom"));

    public GameSpeedModule() {
        super(Category.EXPLOIT);
        registerProperties(mode, timer, groundTimer, airTimer);
    }

    @Override
    public void onDisable() {
        Data.timer = 1f;
        super.onDisable();
    }

    @EventTarget
    public void onUpdate(UpdateEvent event) {
        switch (mode.getProperty()) {
            case "Normal" -> Data.timer = timer.getProperty().floatValue();
            case "Custom" -> {
                if (mc.player.onGround()) {
                    Data.timer = groundTimer.getProperty().floatValue();
                } else {
                    Data.timer = airTimer.getProperty().floatValue();
                }
            }
        }
    }
}
