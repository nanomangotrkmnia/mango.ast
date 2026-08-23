package mango.ast.component;

import mango.ast.Astralis;
import mango.ast.interfaces.Fonts;
import mango.ast.interfaces.IAccess;
import mango.ast.util.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Component extends Data implements IAccess, Fonts {
    public static boolean activate;

    public Component() {
        onEnable();
    }

    public void onEnable() {
        Astralis.getInstance().getEventManager().register(this);
    }

    public void onDisable() {
        Astralis.getInstance().getEventManager().unregister(this);
    }
}
