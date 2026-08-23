package mango.ast.module.impl.player.nofall;

import astralis.mixin.accessor.network.PlayerMoveC2SPacketAccessor;
import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.TickEvent;
import mango.ast.event.events.impl.network.PacketEvent;
import mango.ast.module.Module;
import mango.ast.module.SubModule;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;

public class BlocksMcNofall extends SubModule {

    public BlocksMcNofall(Module parentClass) {
        super(parentClass, "Blocks MC");
    }

    private boolean clip = false;

    @EventTarget
    public void onTick(TickEvent event) {
        if (mc.player.getDeltaMovement().y < -0.7) {
            clip = true;
        }
    }

    @EventTarget
    public void onPacket(PacketEvent event) {
        if (event.getPacket() instanceof ServerboundMovePlayerPacket) {
            PlayerMoveC2SPacketAccessor accessor = (PlayerMoveC2SPacketAccessor) event.getPacket();
            if (mc.player.onGround() && clip) {
                accessor.setY(-0.1);
            }
        }
        if (event.getPacket() instanceof ClientboundPlayerPositionPacket) {
            clip = false;
        }
    }
}
