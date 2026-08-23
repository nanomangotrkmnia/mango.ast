package mango.ast.module.impl.combat.velocity;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.MoveEvent;
import mango.ast.event.events.impl.game.StrafeEvent;
import mango.ast.event.events.impl.game.TickEvent;
import mango.ast.event.events.impl.network.PacketEvent;
import mango.ast.event.types.EventModes;
import mango.ast.module.Module;
import mango.ast.module.SubModule;
import mango.ast.property.properties.NumberProperty;
import mango.ast.util.network.PacketUtil;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.network.protocol.game.ServerboundUseItemOnPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

// thanks balls.
public class GrimVelocity extends SubModule {
    private final NumberProperty value = new NumberProperty("Grim Value", 3,1 , 10, 1);

    public GrimVelocity(Module parentClass) {
        super(parentClass, "Grim");
        registerPropertyToParentClass(value);
    }

    private int anInt;
    private Vec3 motion;

    @EventTarget
    public void onMove(MoveEvent event) {
        if (anInt > 0) {
            event.setCancelled(true);
            anInt--;
        }
    }

    @EventTarget
    public void onStrafe(StrafeEvent event) {
        if (event.getEventMode() == EventModes.PRE) {
            if (anInt > 0) {
                event.setCancelled(true);
                if (motion == null) {
                    motion = get();
                }
            } else if (motion != null) {
                set(motion);
                motion = null;
            }
        }
    }

    @EventTarget
    public void onUpdate(TickEvent event) {
        if (anInt > 0) {
            PacketUtil.sendNoEvent(new ServerboundUseItemOnPacket(InteractionHand.MAIN_HAND, new BlockHitResult(mc.player.position().with(Direction.Axis.Y,
                    mc.player.getBlockY()), Direction.UP, mc.player.blockPosition().below(), false), 0));
        }
    }

    @EventTarget
    public void onPacket(PacketEvent e) {
        if (e.getPacket() instanceof ClientboundSetEntityMotionPacket packet && packet.getId() == mc.player.getId()) {
            e.setCancelled(true);
            anInt = value.getProperty().intValue();
        }
    }

    private static Vec3 get() {
        return mc.player != null ? mc.player.getDeltaMovement() : Vec3.ZERO;
    }

    private static void set(Vec3 vec3) {
        if (mc.player != null) mc.player.setDeltaMovement(vec3);
    }
}
