package mango.ast.module.impl.combat.velocity;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.network.PacketEvent;
import mango.ast.module.Module;
import mango.ast.module.SubModule;
import net.minecraft.network.protocol.common.ServerboundPongPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;

public class BufferVelocity extends SubModule {
    private int buffer = 0;

    public BufferVelocity(Module parentClass) {
        super(parentClass, "Buffer");
    }

    @EventTarget
    public void onPacket(PacketEvent event) {

        if (event.getPacket() instanceof ClientboundSetEntityMotionPacket velocityPacket) {
            if (velocityPacket.getId() != mc.player.getId()) {
                return;
            }

            event.setCancelled(true);
            buffer++;
            // mc.getNetworkHandler().sendPacket(new ClientCommandC2SPacket(mc.player, ClientCommandC2SPacket.Mode.RELEASE_SHIFT_KEY));
           // mc.options.sneakKey.setPressed(true);
        }

        if (event.getPacket() instanceof ServerboundPongPacket pongPacket && buffer > 0) {
            event.setCancelled(true);
            buffer--;
        }

    }
}
