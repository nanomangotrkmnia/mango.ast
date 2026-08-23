package mango.ast.module.impl.client;

import mango.ast.module.Category;
import mango.ast.module.Module;
import mango.ast.property.properties.BooleanProperty;
import mango.ast.property.properties.NumberProperty;

public class AutoSaveModule extends Module {
    public final BooleanProperty autoSaveConfig = new BooleanProperty("Auto Save Config", true);
    public final NumberProperty autoSaveConfigDelay = new NumberProperty("Auto Save Config Delay", 300000, 1000, 3600000f, 1000);

    public AutoSaveModule() {
        super(Category.EXPLOIT);
    }
}
