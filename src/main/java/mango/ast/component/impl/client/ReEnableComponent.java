package mango.ast.component.impl.client;

import mango.ast.Astralis;
import mango.ast.component.Component;
import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.UpdateEvent;
import mango.ast.event.events.impl.network.PacketEvent;
import mango.ast.event.events.impl.render.Render2DEvent;
import mango.ast.event.types.EventModes;
import mango.ast.module.impl.movement.SpeedModule;
import mango.ast.util.math.TimeUtil;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;

public class ReEnableComponent extends Component {
    private final TimeUtil timeUtil = new TimeUtil();
    private boolean didDisable;

    @EventTarget
    public void onPacket(PacketEvent event) {
        if (event.getEventMode() == EventModes.SEND || !Astralis.getInstance().getModuleManager().getModule(SpeedModule.class).isToggled())
            return;

        if (event.getPacket() instanceof ClientboundPlayerPositionPacket) {
            Astralis.getInstance().getModuleManager().getModule(SpeedModule.class).toggle();
            didDisable = true;
            timeUtil.reset();
        }
    }

    @EventTarget
    public void onUpdate(UpdateEvent event) {
        if (didDisable && timeUtil.finished(1000))
            didDisable = false;
    }

    @EventTarget
    public void onRender2D(Render2DEvent event) {
       /* if (!didDisable)
            return;

        // just a notice having that timer run continuously might not be the best thing.
        String text = "Disabled Due To Flag";
        Window window = mc.getWindow();

       *//* RenderUtil.drawGradientString(roboto_bold_11, text,
                (window.getScaledWidth() - product_regular_10.getStringWidth(text)) / 2,
                (window.getScaledHeight() / 2) + 5,
                Astralis.getInstance().getFirstColor(),
                Astralis.getInstance().getSecondColor(), true);*//*
        roboto_bold_11.drawCenteredStringWithShadow(text,
                (float) (window.getScaledWidth()) / 2,
                ((float) window.getScaledHeight() / 2) + 5,
                Astralis.getInstance().getFirstColor());*/
    }
}
