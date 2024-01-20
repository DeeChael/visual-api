package net.deechael.visual.api;

import net.deechael.visual.api.animation.AnimationManager;
import net.deechael.visual.api.curve.CurveManager;
import net.deechael.visual.api.geometry.Geometry;

public interface VisualPlatform {

    AnimationManager getAnimationManager();

    Geometry getGeometry();

    CurveManager getCurveManager();

    static VisualPlatform getPlatform() {
        throw new RuntimeException("Not implemented");
    }

}
