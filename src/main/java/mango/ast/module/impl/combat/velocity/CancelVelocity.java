package mango.ast.module.impl.combat.velocity;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.network.PacketEvent;
import mango.ast.event.types.EventModes;
import astralis.mixin.accessor.network.EntityVelocityUpdateS2CPacketAccessor;
import mango.ast.module.Module;
import mango.ast.module.SubModule;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;

public class CancelVelocity extends SubModule {
    public CancelVelocity(Module parentClass)  {
        super(parentClass,"Cancel");
    }

    @EventTarget
    public void onPacket(PacketEvent event)  {
        if (event.getEventMode() == EventModes.RECEIVE && event.getPacket() instanceof ClientboundSetEntityMotionPacket velocityPacket) {
            EntityVelocityUpdateS2CPacketAccessor velocityAccessor = ((EntityVelocityUpdateS2CPacketAccessor) event.getPacket());

            if (velocityAccessor.getId() != mc.player.getId()) {
                return;
            }

            event.setCancelled(true);
        }
    }
}
