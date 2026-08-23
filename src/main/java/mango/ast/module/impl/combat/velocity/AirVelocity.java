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

public class AirVelocity extends SubModule {
    private final NumberProperty horizontalVelocity = new NumberProperty("Horizontal Velocity", 0, 0, 100, 1);

    public AirVelocity(Module parentClass)  {
        super(parentClass,"Air");
        this.registerPropertyToParentClass(horizontalVelocity);
    }

    @EventTarget
    public void onPacket(PacketEvent event) {
        if (event.getEventMode() == EventModes.RECEIVE && event.getPacket() instanceof ClientboundSetEntityMotionPacket packet) {
            EntityVelocityUpdateS2CPacketAccessor accessor = (EntityVelocityUpdateS2CPacketAccessor) packet;

            if (accessor.getId() != mc.player.getId()) {
                return;
            }

            if (mc.player.onGround()) {
                accessor.setMovement(VelocityBuilder.from(accessor.getMovement())
                        .setVelocityX(accessor.getMovement().x * (horizontalVelocity.getProperty().intValue() / 100D))
                        .setVelocityZ(accessor.getMovement().z * (horizontalVelocity.getProperty().intValue() / 100D))
                        .build()
                );
            } else {
                event.setCancelled(true);
            }
        }
    }


}
