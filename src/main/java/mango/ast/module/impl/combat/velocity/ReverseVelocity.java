package mango.ast.module.impl.combat.velocity;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.network.PacketEvent;
import mango.ast.event.types.EventModes;
import astralis.mixin.accessor.network.EntityVelocityUpdateS2CPacketAccessor;
import mango.ast.module.Module;
import mango.ast.module.SubModule;
import mango.ast.property.properties.NumberProperty;
import mango.ast.velocity.VelocityBuilder;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;

public class ReverseVelocity extends SubModule {
    private final NumberProperty horizontalVelocity = new NumberProperty("Horizontal Velocity", 0, 0, 100, 1);

    public ReverseVelocity(Module parentClass)  {
        super(parentClass,"Reverse");
        this.registerPropertyToParentClass(horizontalVelocity);
    }

    @EventTarget
    public void onPacket(PacketEvent event)  {
        if (event.getEventMode() == EventModes.RECEIVE && event.getPacket() instanceof ClientboundSetEntityMotionPacket velocityPacket) {
            EntityVelocityUpdateS2CPacketAccessor velocityAccessor = ((EntityVelocityUpdateS2CPacketAccessor) event.getPacket());

            if (velocityAccessor.getId() != mc.player.getId()) {
                return;
            }

            velocityAccessor.setMovement(VelocityBuilder.from(velocityAccessor.getMovement())
                    .setVelocityX(-velocityAccessor.getMovement().x * (horizontalVelocity.getProperty().intValue() / 100D))
                    .setVelocityZ(-velocityAccessor.getMovement().z * (horizontalVelocity.getProperty().intValue() / 100D))
                    .build()
            );
        }
    }
}
