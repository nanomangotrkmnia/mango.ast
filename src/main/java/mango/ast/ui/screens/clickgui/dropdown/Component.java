package mango.ast.ui.screens.clickgui.dropdown;

import mango.ast.interfaces.Fonts;
import mango.ast.interfaces.IAccess;
import mango.ast.property.Property;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Component implements IAccess, Fonts {
    private float width, height;
    private float x, y;
    private Property<?> property;

    public void render(float mouseX, float mouseY) { /* w */ }

    public void release(double mouseX, double mouseY, int button) { /* w */ }
    public void click(double mouseX, double mouseY, int button) { /* w */ }
}
