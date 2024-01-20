package net.deechael.visual.api;

import net.deechael.visual.api.animation.AnimationManager;
import net.deechael.visual.api.curve.CurveManager;
import net.deechael.visual.api.geometry.Geometry;
import net.deechael.visual.bukkit.VisualBukkitPlugin;

public interface VisualPlatform {

    AnimationManager getAnimationManager();

    Geometry getGeometry();

    CurveManager getCurveManager();

    static VisualPlatform getPlatform() {
        return VisualBukkitPlugin.getInstance();
    }

}
