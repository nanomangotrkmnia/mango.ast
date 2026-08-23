package mango.ast.module.impl.visual;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.UpdateEvent;
import mango.ast.module.Category;
import mango.ast.module.Module;
import mango.ast.property.properties.BooleanProperty;
import mango.ast.property.properties.ColorProperty;

import java.awt.*;

public class GlowEspModule extends Module {
    public final BooleanProperty reloadShader = new BooleanProperty("Reload Shader", false);
    public final ColorProperty color = new ColorProperty("Color", Color.RED);

    private boolean didReload = false;
    public GlowEspModule() {
        super(Category.VISUAL);
        this.registerProperties(color, reloadShader);
    }

    // this is retarded but since mc has removed .set for uniforms there is no way to update the shader dynamically.
    @EventTarget
    public void onUpdate(UpdateEvent event) {
        if (reloadShader.getProperty() && !didReload) {
            mc.reloadResourcePacks();
            didReload = true;
        }

        if (!reloadShader.getProperty() && didReload)
            didReload = false;
    }
}