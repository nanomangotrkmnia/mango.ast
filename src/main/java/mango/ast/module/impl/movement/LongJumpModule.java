package mango.ast.module.impl.movement;

import mango.ast.module.Category;
import mango.ast.module.Module;

public class LongJumpModule extends Module {
  /*  private final ClassModeProperty mode = new ClassModeProperty(
            "Long Jump Mode", new WatchdogFireBall(this));
*/
    public LongJumpModule() {
        super(Category.MOVEMENT);
/*
        registerProperty(mode);
*/
    }
}
