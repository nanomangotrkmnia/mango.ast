package mango.ast.module.impl.visual;

import astralis.mixin.accessor.mc.IdentifierAccessor;
import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.game.EntityInteractEvent;
import mango.ast.module.Category;
import mango.ast.module.Module;
import mango.ast.property.properties.ModeProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class HitSoundModule extends Module {

    private final ModeProperty mode = new ModeProperty("Hit Sound", "Skeet",
            "Skeet", "Never Lose", "Dark loves them");

    public HitSoundModule() {
        super(Category.VISUAL);
        this.registerProperty(mode);
    }

    @EventTarget
    public void onInteractEvent(EntityInteractEvent event) {
        SoundEvent soundEvent = BuiltInRegistries.SOUND_EVENT.getValue(getSoundIdentifier());
        if (soundEvent == null) {
            System.out.println("Sound Event is null.");
            return;
        }

        SoundInstance soundInstance = SimpleSoundInstance.forUI(soundEvent/*, SoundCategory.PLAYERS*/, 1.0f, 1.0f);
        Minecraft.getInstance().getSoundManager().play(soundInstance);
    }

    public Identifier getSoundIdentifier() {
        return switch (mode.getProperty()) {
            case "Skeet" -> IdentifierAccessor.createIdentifier("mango.ast", "effects.skeet");
            case "Never Lose" -> IdentifierAccessor.createIdentifier("mango.ast", "effects.neverlose");
            case "Dark loves them" -> IdentifierAccessor.createIdentifier("mango.ast", "effects.dark");
            default ->
                throw new IllegalStateException("Unexpected value: " + mode.getProperty());
        };
    }
}
