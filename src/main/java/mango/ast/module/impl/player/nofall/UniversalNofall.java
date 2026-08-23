package mango.ast.module.impl.player.nofall;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.PostMotionEvent;
import astralis.mixin.accessor.entity.EntityAccessor;
import mango.ast.module.Module;
import mango.ast.module.SubModule;
import mango.ast.util.network.PacketUtil;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;

public class UniversalNofall extends SubModule {

    public UniversalNofall(Module parentClass){
        super(parentClass,"Universal");
    }

    @EventTarget
    public void onPostMotion(PostMotionEvent event) {
            double motionY = mc.player.getDeltaMovement().y;
            if (mc.player.fallDistance > 3 && motionY < 0 && ((EntityAccessor) mc.player).callCollide(mc.player.getDeltaMovement()).y > motionY) {
                PacketUtil.send(new ServerboundMovePlayerPacket.PosRot(mc.player.getX(), mc.player.getY() + 1E-14, mc.player.getZ(), mc.player.getYRot(), mc.player.getXRot(),
                        false,
                        false
                ));
            }
        }
    }
