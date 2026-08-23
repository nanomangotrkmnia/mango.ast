package mango.ast.module.impl.player.nofall;

import mango.ast.Astralis;
import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.MotionEvent;
import mango.ast.module.Module;
import mango.ast.module.SubModule;
import mango.ast.module.impl.movement.ScaffoldRecodeModule;
import mango.ast.property.properties.NumberProperty;
import mango.ast.util.player.PlayerUtil;

public class VulcanNofall extends SubModule {
    private final NumberProperty motion = new NumberProperty("Motion", 10f, 0f, 10f, 1f);

    public VulcanNofall(Module parentClass){
        super(parentClass,"Vulcan");
        this.registerPropertyToParentClass(motion);
    }
    private int fallticks;

    @Override
    public void onDisable(){
        PlayerUtil.setMotionY(mc.player.getDeltaMovement().y());
        super.onDisable();
    }

    @EventTarget
    public void onMotion(MotionEvent event) {
        if (mc.player.onGround()) {
            fallticks = 0;
        }
        if (mc.player.fallDistance - mc.player.getDeltaMovement().y > 3 && !Astralis.getInstance().getModuleManager().getModule(ScaffoldRecodeModule.class).isToggled()) {
            fallticks++;
            if (fallticks > 1) {
                event.setOnGround(true);
            }
            if (fallticks > 2) {
                PlayerUtil.setMotionY(-motion.getProperty().floatValue());
            }
        }
    }

}
