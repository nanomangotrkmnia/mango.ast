package mango.ast.module.impl.visual;

import mango.ast.module.Category;
import mango.ast.module.Module;
import mango.ast.property.properties.BooleanProperty;
import mango.ast.property.properties.ModeProperty;

public class NotificationsModule extends Module {
    public final BooleanProperty displayNotificationOnToggle = new BooleanProperty("Notification on Toggle", true);
    public final ModeProperty mode = new ModeProperty("Mode", "Modern", "Modern", "Legacy");
    public final BooleanProperty popSound = new BooleanProperty("Pop Sound", true);

    public NotificationsModule() {
        super(Category.VISUAL);
        this.registerProperties(mode, displayNotificationOnToggle, popSound);
    }
}
