package mango.ast.module.impl.combat;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.EntityInteractEvent;
import mango.ast.event.types.EventModes;
import mango.ast.module.Category;
import mango.ast.module.Module;
import mango.ast.property.properties.BooleanProperty;

public class NoMissDelayModule extends Module {
    public final BooleanProperty attackcooldown = new BooleanProperty("Removes Attack Cooldown",true);
    public final BooleanProperty attackonMiss = new BooleanProperty("Removes your attack on miss",true);
    public final BooleanProperty weaponOnly = new BooleanProperty("Weapon Only", true);
    public final BooleanProperty allowBlockHit = new BooleanProperty("Allow Block Hit", false);
    public final BooleanProperty allowAirHit = new BooleanProperty("Allow Air Hit", false);

    public NoMissDelayModule() {
        super(Category.COMBAT);
        registerProperties(attackcooldown, attackonMiss, weaponOnly, allowBlockHit, allowAirHit);
    }
}
