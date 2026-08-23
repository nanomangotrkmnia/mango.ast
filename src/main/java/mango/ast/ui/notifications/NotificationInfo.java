package mango.ast.ui.notifications;

import mango.ast.ui.notifications.render.Notification;
import mango.ast.ui.animations.Animation;
import mango.ast.ui.animations.Easing;
import mango.ast.util.math.TimeUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class NotificationInfo {
    public Notification notification;
    public TimeUtil notificationTime;
    private final Animation yAnimation = new Animation(Easing.EASE_IN_OUT_QUAD, 200);
    public int time;

    NotificationInfo() {}

    public static NotificationBuilder builder() {
        return new NotificationBuilder();
    }
}