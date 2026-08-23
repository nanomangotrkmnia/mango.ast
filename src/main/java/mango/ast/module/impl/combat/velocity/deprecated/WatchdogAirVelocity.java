package mango.ast.module.impl.combat.velocity.deprecated;

import mango.ast.Astralis;
import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.MotionEvent;
import mango.ast.event.events.impl.network.PacketEvent;
import mango.ast.event.types.EventModes;
import astralis.mixin.accessor.network.EntityVelocityUpdateS2CPacketAccessor;
import mango.ast.module.Module;
import mango.ast.module.SubModule;
import mango.ast.module.impl.movement.LongJumpModule;
import mango.ast.property.properties.BooleanProperty;
import mango.ast.util.network.PacketUtil;
import mango.ast.util.player.PlayerUtil;
import mango.ast.util.render.ChatUtil;
import java.util.ArrayList;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.common.ServerboundKeepAlivePacket;
import net.minecraft.network.protocol.common.ServerboundPongPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;

public class WatchdogAirVelocity extends SubModule {
    private final BooleanProperty breaker = new BooleanProperty("Breaker", true);

    private final ArrayList<Packet<?>> delayed = new ArrayList<>();
    private int stage = 0;

    public WatchdogAirVelocity(Module parentClass)  {
        super(parentClass,"Watchdog Air");
        this.registerPropertyToParentClass(breaker);
    }

    @Override
    public void onDisable() {
        if (!delayed.isEmpty()) {
            delayed.forEach(PacketUtil::sendNoEvent);
            delayed.clear();
        }

        super.onDisable();
    }

    @EventTarget
    public void onPacket(PacketEvent event)  {
        if (event.getEventMode() == EventModes.RECEIVE && event.getPacket() instanceof ClientboundSetEntityMotionPacket velocityPacket) {
            EntityVelocityUpdateS2CPacketAccessor velocityAccessor = ((EntityVelocityUpdateS2CPacketAccessor) event.getPacket());

            if (velocityAccessor.getId() != mc.player.getId()) {
                return;
            }

            if (Astralis.getInstance().getModuleManager().getModule(LongJumpModule.class).isToggled())
                return;

          /*  if (breaker.getProperty() && Astralis.getInstance().getModuleManager().getModule(BreakerModule.class).breakCache.isBreaking) {
                return;
            }
*/
            event.setCancelled(true);

            if (PlayerUtil.getDistanceToGround() < 3 && !mc.player.onGround()) {
                stage = 1;
            } else {
                PlayerUtil.setMotionY(velocityPacket.getMovement().y);
            }
        }

        if (event.getPacket() instanceof ClientboundPlayerPositionPacket) {
            this.stage = 2;
        }

        if (event.getEventMode() == EventModes.SEND) {
            if ( (event.getPacket() instanceof ServerboundPongPacket || event.getPacket() instanceof ServerboundKeepAlivePacket)) {
                if (stage >= 1 && !event.isCancelled()) {
                    event.setCancelled(true);

                    ChatUtil.printDebug("add");
                    delayed.add(event.getPacket());
                    if (stage == 2) {
                        stage = 0;
                        delayed.forEach(PacketUtil::sendNoEvent);
                        delayed.clear();
                    }
                }
            }
        }
    }

    @EventTarget
    public void onMotion(MotionEvent event) {
        if ((offGroundTicks <= 2 && !mc.player.onGround()) || (offGroundTicks > 3)) {
            if (stage == 1) {
                stage = 2;
            }
        }
    }
}
