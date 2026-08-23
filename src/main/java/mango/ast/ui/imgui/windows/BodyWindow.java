package mango.ast.ui.imgui.windows;

import imgui.ImGui;
import imgui.ImVec2;
import imgui.flag.ImGuiCond;
import imgui.flag.ImGuiWindowFlags;
import mango.ast.interfaces.IAccess;
import mango.ast.property.properties.body.BodyPart;
import mango.ast.property.properties.body.BodyProperty;
import mango.ast.util.render.ImguiUtil;
import com.mojang.blaze3d.systems.RenderSystem;

public class BodyWindow implements IAccess {
    private final BodyProperty bodyProperty;

    public BodyWindow(BodyProperty bodyProperty) {
        this.bodyProperty = bodyProperty;
    }

    public void show() {
        ImGui.setNextWindowPos(1301, 354, ImGuiCond.Once);

        if (ImGui.begin(bodyProperty.getName(), ImGuiWindowFlags.NoResize)) {
            ImGui.setCursorPos(76, 73);
        /*    if (ImGui.imageButton(ImguiUtil.loadTexture("parts/point.png", "ph"), 25, 25)) {
                bodyProperty.setProperty(BodyPart.HEAD);
            }

            ImGui.setCursorPos(76, 120);
            if (ImGui.imageButton(ImguiUtil.loadTexture("parts/point.png", "pn"), 25, 25)) {
                bodyProperty.setProperty(BodyPart.NECK);
            }

            ImGui.setCursorPos(76, 160);
            if (ImGui.imageButton(ImguiUtil.loadTexture("parts/point.png", "pc"), 25, 25)) {
                bodyProperty.setProperty(BodyPart.CHEST);
            }

            ImGui.setCursorPos(76, 220);
            if (ImGui.imageButton(ImguiUtil.loadTexture("parts/point.png", "plc"), 25, 25)) {
                bodyProperty.setProperty(BodyPart.LOWER_CHEST);
            }

            ImGui.setCursorPos(12, 140);
            if (ImGui.imageButton(ImguiUtil.loadTexture("parts/point.png", "la"), 25, 25)) {
                bodyProperty.setProperty(BodyPart.LEFT_ARM);
            }

            ImGui.setCursorPos(139, 140);
            if (ImGui.imageButton(ImguiUtil.loadTexture("parts/point.png", "ra"), 25, 25)) {
                bodyProperty.setProperty(BodyPart.RIGHT_ARM);
            }*/

            ImGui.end();
        }
    }
}