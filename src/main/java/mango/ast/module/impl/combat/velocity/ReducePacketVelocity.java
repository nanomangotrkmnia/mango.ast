package mango.ast.module.impl.combat.velocity;

import astralis.mixin.accessor.network.EntityVelocityUpdateS2CPacketAccessor;
import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.network.PacketEvent;
import mango.ast.event.types.EventModes;
import mango.ast.module.Module;
import mango.ast.module.SubModule;
import mango.ast.util.network.PacketUtil;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.network.protocol.game.ServerboundSwingPacket;
import net.minecraft.world.InteractionHand;

public class ReducePacketVelocity extends SubModule {

    public ReducePacketVelocity(Module parentClass)  {
        super(parentClass,"Reduce Packet");
    }

    @EventTarget
    public void onPacket(PacketEvent event)  {
        if (event.getEventMode() == EventModes.RECEIVE && event.getPacket() instanceof ClientboundSetEntityMotionPacket velocityPacket) {
            EntityVelocityUpdateS2CPacketAccessor velocityAccessor = ((EntityVelocityUpdateS2CPacketAccessor) event.getPacket());

            if (velocityAccessor.getId() != mc.player.getId()) return;

            for (int i = 0; i < 5; i++) {
                //It ain't hard bro
                PacketUtil.send(new ServerboundSwingPacket(InteractionHand.MAIN_HAND));
            }
        }
    }
}


