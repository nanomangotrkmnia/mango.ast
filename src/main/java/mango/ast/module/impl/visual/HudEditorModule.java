package mango.ast.module.impl.visual;

import mango.ast.ui.screens.client.HudEditorScreen;
import mango.ast.module.Category;
import mango.ast.module.Module;

public class HudEditorModule extends Module {
    private final HudEditorScreen hudEditorScreen;

    public HudEditorModule() {
        super(Category.VISUAL);
        hudEditorScreen = new HudEditorScreen();
    }

    @Override
    public void onEnable() {
        mc.setScreen(hudEditorScreen);
        this.setToggled(false);
        super.onEnable();
    }
}
