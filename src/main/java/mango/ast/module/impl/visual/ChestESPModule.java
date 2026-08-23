package mango.ast.module.impl.visual;

import mango.ast.event.EventTarget;
import mango.ast.event.events.impl.render.Render3DEvent;
import mango.ast.module.Category;
import mango.ast.module.Module;
import mango.ast.property.properties.ColorProperty;
import mango.ast.util.player.StorageUtil;
import mango.ast.util.render.Render3DUtil;
import java.awt.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.AABB;

import static mango.ast.util.player.StorageUtil.collectLoadedAroundPlayer;

public class ChestESPModule extends Module {
    private final ColorProperty color = new ColorProperty("Color", Color.red);

    public ChestESPModule() {
        super(Category.VISUAL);
        this.registerProperties(color);
    }

    @Override
    public void onEnable() {
        StorageUtil.setCollectData(true);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        StorageUtil.setCollectData(false);
        super.onDisable();
    }

    @EventTarget
    public void onRender3D(Render3DEvent event) {
        collectLoadedAroundPlayer();
        for (BlockPos blockPos : StorageUtil.snapshotAll()) {
            Render3DUtil.drawBoxESP(event.getMatricies(), new AABB(blockPos), color.getProperty(),150);
        }
    }
}