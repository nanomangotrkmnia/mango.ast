package mango.ast.util.gif;

import mango.ast.interfaces.IAccess;
import com.mojang.blaze3d.platform.NativeImage;
import astralis.mixin.accessor.mc.IdentifierAccessor;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.resources.Identifier;

public class DynamicTextureManager implements IAccess {
    public static final Map<String, Identifier> textureCache = new HashMap<>();

    public static Identifier registerImageTexture(String imagePath) throws IOException {
        if (textureCache.containsKey(imagePath)) {
            return textureCache.get(imagePath);
        }

        File imageFile = new File(imagePath);
        try (InputStream in = new FileInputStream(imageFile)) {
            NativeImage image = NativeImage.read(in);
            Identifier id = IdentifierAccessor.createIdentifier("mango.ast", imagePath);

            mc.getTextureManager().registerForNextReload(
                    id
            );

            textureCache.put(imagePath, id);
            return id;
        }
       // return IdentifierAccessor.createIdentifier("mango.ast", "fuckoff");
    }

    public static void cleanup() {
        textureCache.values().forEach(id ->
               mc.getTextureManager().release(id)
        );
        textureCache.clear();
    }
}
