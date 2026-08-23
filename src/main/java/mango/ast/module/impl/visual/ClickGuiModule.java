package mango.ast.module.impl.visual;

import mango.ast.Astralis;
import mango.ast.module.Category;
import mango.ast.module.Module;
import mango.ast.property.properties.BooleanProperty;
import mango.ast.property.properties.ColorProperty;
import mango.ast.property.properties.ModeProperty;
import mango.ast.property.properties.NumberProperty;
import mango.ast.ui.screens.clickgui.astralis.AstralisClickGUI;
import mango.ast.ui.screens.clickgui.dropdown.DropdownCGUIScreen;
import mango.ast.ui.imgui.windows.ClickGuiWindow;
import org.lwjgl.glfw.GLFW;

import java.awt.*;

public class ClickGuiModule extends Module {
    private final ModeProperty mode = new ModeProperty("Mode", "Astralis", "Imgui", "Dropdown", "Astralis");
    public NumberProperty imguiAlpha = new NumberProperty("Imgui Alpha", 240, 0, 255, 1);
    public BooleanProperty blur = new BooleanProperty("Blur", true);
    public BooleanProperty onlyBlurImgui = new BooleanProperty("Only Blur Imgui", false);
    public NumberProperty blurRadius = new NumberProperty("Blur Radius", 7, 1, 50, 1);
    public BooleanProperty gradient = new BooleanProperty("Gradient", true);
    public BooleanProperty onlyGradientImgui = new BooleanProperty("Only Gradient With Imgui", false);
    public NumberProperty gradientAlpha = new NumberProperty("Gradient Alpha", 55, 1, 255, 1);
    public ColorProperty color = new ColorProperty("Color", new Color(78, 73, 165));
    public ColorProperty hovColor = new ColorProperty("Hover Color", new Color(66, 61, 140));

    private final ClickGuiWindow imgui = new ClickGuiWindow();

    private AstralisClickGUI astralisScreen;
    private DropdownCGUIScreen dropdownScreen;
   // private final PowerShellCguiScreen powerShellCguiScreen = new PowerShellCguiScreen();

    public ClickGuiModule() {
        super(Category.VISUAL, GLFW.GLFW_KEY_RIGHT_SHIFT);
        registerProperties(mode,
                blur.setVisible(() -> mode.is("Imgui")),
                onlyBlurImgui.setVisible(() -> blur.getProperty() && blur.getVisible().get()),
                blurRadius.setVisible(() -> blur.getProperty() && blur.getVisible().get()),
                gradient.setVisible(() -> mode.is("Imgui")),
                onlyGradientImgui.setVisible(() -> gradient.getProperty() && gradient.getVisible().get()),
                gradientAlpha.setVisible(() -> gradient.getProperty() && gradient.getVisible().get()),
                imguiAlpha.setVisible(() -> mode.is("Imgui")),
                color.setVisible(() -> mode.is("Imgui")),
                hovColor.setVisible(() -> mode.is("Imgui"))
        );
    }

    @Override
    public void onEnable() {
        switch (mode.getProperty()) {
            case "Imgui" ->  mc.setScreen(imgui);
            case "Astralis" -> {
                if (astralisScreen == null) {
                    astralisScreen = new AstralisClickGUI();
                }

                mc.setScreen(astralisScreen);
            }
            case "Dropdown" -> {
                if (dropdownScreen == null) {
                    dropdownScreen = new DropdownCGUIScreen();
                }

                Astralis.getInstance().getEventManager().register(dropdownScreen);
                mc.setScreen(dropdownScreen);
            }
        }

        this.toggle();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
