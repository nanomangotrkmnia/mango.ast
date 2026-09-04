package astralis.mixin.accessor.network;

import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ServerboundMovePlayerPacket.class)
public interface PlayerMoveC2SPacketAccessor {
    @Accessor("x")
    double getX();

    @Mutable
    @Accessor("x")
    void setX(double x);

    @Accessor("y")
    double getY();

    @Mutable
    @Accessor("y")
    void setY(double y);

    @Accessor("z")
    double getZ();

    @Mutable
    @Accessor("z")
    void setZ(double z);

    @Accessor
    float getYRot();

    @Mutable
    @Accessor
    void setYRot(float yaw);

    @Accessor
    float getXRot();

    @Mutable
    @Accessor
    void setXRot(float pitch);

    @Accessor
    boolean isOnGround();

    @Mutable
    @Accessor
    void setOnGround(boolean onGround);

    @Accessor
    boolean isHasPos();

    @Mutable
    @Accessor
    void setHasPos(boolean changePosition);

    @Accessor
    boolean isHasRot();

    @Mutable
    @Accessor
    void setHasRot(boolean changeLook);
}
