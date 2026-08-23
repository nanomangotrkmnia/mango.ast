package mango.ast.component;

import mango.ast.Astralis;
import mango.ast.component.impl.client.BanDetectorComponent;
import mango.ast.component.impl.client.BedWhiteListComponent;
import mango.ast.component.impl.client.ReEnableComponent;
import mango.ast.component.impl.network.BlinkComponent;
import mango.ast.component.impl.network.PacketLossDetector;
import mango.ast.component.impl.player.RotationComponent;
import mango.ast.component.impl.ui.NotificationComponent;
import mango.ast.component.impl.ui.ProgressBarComponent;
import mango.ast.manager.Manager;

import java.util.Objects;


public class ComponentManager extends Manager<Component> {
    public void registerComponents() {
        this.register(
                new RotationComponent(),
                new NotificationComponent(),
                new ReEnableComponent(),
                new PacketLossDetector(),
                new BlinkComponent(),
                new ProgressBarComponent(),
                new BanDetectorComponent(),
                new BedWhiteListComponent()
        );

        Astralis.getInstance().getEventManager().register(this);
    }

    @SuppressWarnings("unchecked")
    public <T extends Component> T getComponent(final Class<T> clazz) {
        return (T) this.getBy(module -> Objects.equals(module.getClass(), clazz));
    }
}
