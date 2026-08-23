package mango.ast.ui.screens.clickgui.astralis.impl.display.property;

import mango.ast.Astralis;
import mango.ast.font.UIFont;
import mango.ast.font.FontManager;
import mango.ast.module.impl.visual.HudModule;
import mango.ast.property.properties.BooleanProperty;
import mango.ast.ui.screens.clickgui.astralis.Component;
import mango.ast.ui.screens.clickgui.astralis.impl.display.ModuleDisplay;
import mango.ast.util.render.RenderUtil;
import mango.ast.skija.utils.SkijaUtil;

import java.awt.*;

public class BooleanDisplay extends Component {
    private final BooleanProperty property;

    private static final int ENABLED_PADDING = 3;
    private static final Color START = new Color(40, 40, 40);

    public BooleanDisplay(final BooleanProperty property, final float width, final float height) {
        this.property = property;
        setWidth(width);
        setHeight(height);
    }

    @Override
    public void render(final float x, final float y, final float mouseX, final float mouseY) {
        setX(x);
        setY(y);
        final UIFont UIFont = FontManager.getFont("Sf-Ui", 10);
        UIFont.drawStringWithShadow(property.getName(), x + ModuleDisplay.PADDING, y + ModuleDisplay.PROPERTY_PADDING - 1.5f, ModuleDisplay.DESCRIPTION);

        final float enabledSize = super.getHeight() - (ENABLED_PADDING * 2);
        SkijaUtil.roundedRectangle(x + getWidth() - (enabledSize + ModuleDisplay.PADDING), y + ENABLED_PADDING, enabledSize, enabledSize, 2, START);

        if (property.getProperty()) {
            final HudModule hud = Astralis.getInstance().getModuleManager().getModule(HudModule.class);
            SkijaUtil.roundedRectangleDiagonalGradient(x + getWidth() - (enabledSize + ModuleDisplay.PADDING), y + ENABLED_PADDING, enabledSize, enabledSize, 2, hud.firstColor.getProperty(), hud.secondColor.getProperty());
        }
    }

    @Override
    public void mouseClicked(final double mouseX, final double mouseY, final int button) {
        if (RenderUtil.isHovered(mouseX, mouseY, getX(), getY(), getWidth(), getHeight())) {
            property.setProperty(!property.getProperty());
        }
    }
}
