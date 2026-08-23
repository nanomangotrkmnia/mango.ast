package mango.ast.module.impl.visual;

import mango.ast.module.Category;
import mango.ast.module.Module;
import mango.ast.property.properties.BooleanProperty;
import mango.ast.property.properties.NumberProperty;

public class CameraModule extends Module {

    public final BooleanProperty changeAspectRatio = new BooleanProperty("Change Aspect Ratio", true);
    public final NumberProperty aspectRatio = new NumberProperty("Zoom",1f,0.5f,5f,0.01f).setVisible(changeAspectRatio::getProperty);
    public final BooleanProperty modifierCamera = new BooleanProperty("Modify Third Person", true);
    public final BooleanProperty cameraNoClip = new BooleanProperty("Camera No Clip", true);
    public static NumberProperty itemX = new NumberProperty("X Item", 1, -10, 10, 0.1f),
            itemY = new NumberProperty("Y Item", 1, -10, 10, 0.1f),
            itemZ = new NumberProperty("Z Item", 1, -10, 10, 0.1f),
            itemRotX = new NumberProperty("Rot X", 0, -180, 180, 1f),
            itemRotY = new NumberProperty("Rot Y", 0, -180, 180, 1f),
            itemRotZ = new NumberProperty("Rot Z", 0, -180, 180, 1f);


    public CameraModule(){
        super(Category.VISUAL);
        registerProperties(modifierCamera, cameraNoClip.setVisible(modifierCamera::getProperty),changeAspectRatio,aspectRatio,itemX,itemY,itemZ,itemRotX,itemRotY,itemRotZ);
    }
}

