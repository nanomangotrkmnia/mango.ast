package mango.ast.module.impl.player;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.UpdateEvent;
import mango.ast.event.events.impl.network.PacketEvent;
import mango.ast.event.types.EventModes;
import mango.ast.module.Category;
import mango.ast.module.Module;
import mango.ast.property.properties.ModeProperty;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;

public class NoRotateModule extends Module {
    private final ModeProperty mode = new ModeProperty("Mode", "Cancel", "Cancel", "Adjust");

    public NoRotateModule() {
        super(Category.EXPLOIT);
        registerProperties(mode);
    }

    @EventTarget
    public void onUpdate(UpdateEvent event) {
       this.setSuffix(mode.getProperty());
    }

    @EventTarget
    public void onPacket(PacketEvent event){
        if(event.getEventMode() == EventModes.RECEIVE && event.getPacket() instanceof ClientboundPlayerPositionPacket) {
            switch (mode.getProperty()){
                case "Cancel":
                    event.setCancelled(true);
                    break;
                case "Adjust":
                    break;
            }
        }
    }
}
