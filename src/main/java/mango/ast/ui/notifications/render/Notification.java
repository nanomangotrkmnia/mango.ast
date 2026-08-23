package mango.ast.ui.notifications.render;

import mango.ast.Astralis;
import mango.ast.interfaces.Fonts;
import mango.ast.interfaces.IAccess;
import mango.ast.module.impl.visual.HudModule;
import mango.ast.module.impl.visual.NotificationsModule;
import mango.ast.ui.animations.Animation;
import mango.ast.ui.animations.Easing;
import mango.ast.util.Data;
import mango.ast.util.io.StringUtil;
import mango.ast.util.render.ColorUtil;
import com.mojang.blaze3d.platform.Window;
import mango.ast.skija.utils.SkijaUtil;
import java.awt.*;
import net.minecraft.client.model.geom.builders.UVPair;

public class Notification extends Data implements IAccess, Fonts {
    private final String subHeader;
    private final NotificationType notificationType;

    public final Animation slideAnimation = new Animation(Easing.EASE_OUT_BACK, 1000);

    private boolean initialized = false;
    private boolean slidingOut = false;

    public Notification(String subHeader, NotificationType notificationType) {
        this.subHeader = subHeader;
        this.notificationType = notificationType;
    }

    public void startSlideOut() {
        if (!slidingOut) {
            slidingOut = true;
            slideAnimation.reset();
        }
    }

    private float computeEdgeX(float yPos, long elapsed, long totalTime, float notificationWidth) {
        final float slideInTarget = 5f;

        if (!initialized) {
            slideAnimation.reset();
            slideAnimation.setStartPoint(-notificationWidth);
            slideAnimation.setEndPoint(slideInTarget);
            initialized = true;
        }

        long timeLeft = Math.max(0, totalTime - elapsed);

        if (timeLeft <= 0 && !slidingOut) {
            startSlideOut();
            slideAnimation.setStartPoint(slideAnimation.getValue());
            slideAnimation.setEndPoint(-notificationWidth);
        }

        slideAnimation.run(slideAnimation.getEndPoint());

        return (float) slideAnimation.getValue();
    }

    @SuppressWarnings("all")
    public void renderNotification(float yPos, long elapsed, long totalTime) {
        Window window = mc.getWindow();

        long timeLeft = Math.max(0, totalTime - elapsed);
        String subHeaderAndTime = subHeader + " (" + String.format("%.1f", timeLeft / 1000.0) + ")";

        float edgeY = yPos;
        float textWidth = product_bold_10.getStringWidth(subHeader) + 23.5f;
        float notificationWidth = 50 + textWidth;
        float height = 35;

        float edgeX = computeEdgeX(yPos, elapsed, totalTime, notificationWidth);

        if (edgeX <= -notificationWidth) {
            return;
        }

        float x = window.getGuiScaledWidth() - edgeX - notificationWidth;
        float y = window.getGuiScaledHeight() - edgeY - height;

        float ratio = totalTime > 0
                ? Math.max(0f, Math.min(1f, 1f - ((float) timeLeft / totalTime)))
                : 0f;

        float progressWidth = notificationWidth * ratio;
        HudModule hud = Astralis.getInstance().getModuleManager().getModule(HudModule.class);

        if (Astralis.getInstance().getModuleManager().getModule(NotificationsModule.class).mode.is("Modern"))
            SkijaUtil.roundedRectangle(x, y, notificationWidth, height, 5, new Color(15, 15, 18,
                    hud.backgroundAlpha.getProperty().intValue())
            );
        else
            SkijaUtil.rectangle(x, y, notificationWidth, height, new Color(15, 15, 18,
                    hud.backgroundAlpha.getProperty().intValue())
            );

        final Color firstColor = switch (hud.colorMode.getProperty()) {
            case "Rainbow" -> HudModule.getRainbow(3000, 0, 0.7f, 1);
            default -> ColorUtil.getAccentColor(new UVPair(0, 6), hud.firstColor.getProperty(), hud.secondColor.getProperty());
        };
        final Color secondColor = switch (hud.colorMode.getProperty()) {
            case "Rainbow" -> HudModule.getRainbow(4000, 0, 1f, 1);
            default -> ColorUtil.getAccentColor(new UVPair(0, 6), hud.firstColor.getProperty(), hud.secondColor.getProperty());
        };

        if (Astralis.getInstance().getModuleManager().getModule(NotificationsModule.class).mode.is("Legacy"))
            SkijaUtil.rectangleGradient(x, y + 33, progressWidth, 2, firstColor, secondColor, false);

        SkijaUtil.drawCircle(x + 16, y + 17.5f, 13, firstColor);
        icons.drawString(notificationType == NotificationType.INFO ? "c" : "d", x + 4.2f, y + 5.8f, Color.white);

        float textX = x + (notificationWidth - textWidth) - 15;
        product_bold_11.drawStringWithShadow(StringUtil.formatEnum(notificationType.name()), textX, y + 4, Color.white);
        product_bold_10.drawStringWithShadow(subHeaderAndTime, textX, y + 18, Color.white);
    }

    @SuppressWarnings("all")
    public void renderNotificationShader(float yPos, long elapsed, long totalTime) {
        Window window = mc.getWindow();

        float textWidth = product_bold_10.getStringWidth(subHeader) + 23.5f;
        float notificationWidth = 50 + textWidth;
        float height = 35;

        float edgeX = computeEdgeX(yPos, elapsed, totalTime, notificationWidth);

        if (edgeX <= -notificationWidth) {
            return;
        }

        float x = window.getGuiScaledWidth() - edgeX - notificationWidth;
        float y = window.getGuiScaledHeight() - yPos - height;

        if (Astralis.getInstance().getModuleManager().getModule(NotificationsModule.class).mode.is("Modern"))
            SkijaUtil.drawShaderRoundRectangle(x, y, notificationWidth, height, 5);
        else
            SkijaUtil.drawShaderRectangle(x, y, notificationWidth, height);
    }

    public enum NotificationType { WARNING, INFO }
}