package mango.ast.module.impl.combat;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.network.PacketEvent;
import mango.ast.module.Category;
import mango.ast.module.Module;
import mango.ast.module.impl.combat.velocity.*;
import mango.ast.property.properties.ClassModeProperty;

public class VelocityModule extends Module {
    // Club velocity code is in MixinEntityVelocityUpdateS2CPacket.java.
    public final ClassModeProperty mode = new ClassModeProperty("Mode",
            new AirVelocity(this), new CancelVelocity(this),
            new IntaveVelocity(this),
            new NormalVelocity(this), new ReverseVelocity(this),
            new VulcanVelocity(this)/*, new WatchdogAirVelocity(this),
            new WatchdogFullVelocity(this)*/, new MMCVelocity(this),
            new DelayVelocity(this), new BufferVelocity(this),
            new ModernWatchdogVelocity(this), new GrimVelocity(this),
            new JumpVelocity(this), new ReducePacketVelocity(this)
    );

    public VelocityModule() {
        super(Category.COMBAT);
        this.registerProperty(mode);
    }

    @EventTarget
    public void onPacket(PacketEvent event) {
        this.setSuffix(mode.getProperty().getFormatedName());
    }
}
