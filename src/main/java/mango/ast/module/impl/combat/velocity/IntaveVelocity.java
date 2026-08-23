package mango.ast.module.impl.combat.velocity;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.network.PacketEvent;
import mango.ast.event.types.EventModes;
import astralis.mixin.accessor.network.EntityVelocityUpdateS2CPacketAccessor;
import mango.ast.module.Module;
import mango.ast.module.SubModule;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;

public class IntaveVelocity extends SubModule {
    public IntaveVelocity(Module parentClass) {
        super(parentClass, "Intave");
    }

    @EventTarget
    public void onPacket(PacketEvent event) {
        if (event.getEventMode() == EventModes.RECEIVE && event.getPacket() instanceof ClientboundSetEntityMotionPacket velocityPacket) {
            EntityVelocityUpdateS2CPacketAccessor velocityAccessor = ((EntityVelocityUpdateS2CPacketAccessor) event.getPacket());

            if (velocityAccessor.getId() != mc.player.getId()) {
                return;
            }

            if (mc.player.hurtTime > 1) {
                mc.player.setDeltaMovement(
                        velocityPacket.getMovement().x * 0.4,
                        velocityPacket.getMovement().y,
                        velocityPacket.getMovement().z * 0.4
                );
            }
        }
    }
}
