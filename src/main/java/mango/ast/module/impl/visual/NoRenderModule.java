package mango.ast.module.impl.visual;

import mango.ast.module.Category;
import mango.ast.module.Module;
import mango.ast.property.properties.BooleanProperty;

public class NoRenderModule extends Module {
    public final BooleanProperty nofire = new BooleanProperty("No Fire", true);
    public final BooleanProperty nowater = new BooleanProperty("No Water", true);
    public final BooleanProperty noblindness = new BooleanProperty("No Blindness",true);
    public final BooleanProperty noFog = new BooleanProperty("No Fog",false);
    public final BooleanProperty noWeather = new BooleanProperty("No Weather",false);
    public final BooleanProperty noClouds = new BooleanProperty("No Clouds",false);

    public NoRenderModule() {
        super(Category.VISUAL);
        registerProperties(nofire,nowater,noblindness,noFog,noWeather,noClouds);
    }


}
