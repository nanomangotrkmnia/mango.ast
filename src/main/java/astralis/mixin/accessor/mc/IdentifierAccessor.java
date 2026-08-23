package astralis.mixin.accessor.mc;

import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Identifier.class)
public interface IdentifierAccessor {
    @Invoker("<init>")
    static Identifier createIdentifier(String namespace, String path) {
        throw new UnsupportedOperationException();
    }
}
