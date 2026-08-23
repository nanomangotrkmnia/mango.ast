package mango.ast.module.impl.combat.velocity;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.input.InputTickEvent;
import mango.ast.module.Module;
import mango.ast.module.SubModule;
import mango.ast.property.properties.NumberProperty;

public class JumpVelocity extends SubModule {
    private final NumberProperty chance = new NumberProperty("Chance", 100, 0, 100, 1);

    public JumpVelocity(Module parent) {
        super(parent, "Jump");
        registerPropertyToParentClass(chance);
    }

    @EventTarget
    public void onInputTick(InputTickEvent event) {
        if (mc.player.hurtTime > 0) {
            if (Math.random() * 100 < chance.getProperty().intValue()) {
                event.up = true;
                if (mc.player.hurtTime == 9) {
                    event.jump = true;
                }
            }
        }
    }
}