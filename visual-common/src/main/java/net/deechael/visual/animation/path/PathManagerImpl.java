package net.deechael.visual.animation.path;

import net.deechael.visual.api.VisualPlatform;
import net.deechael.visual.api.animation.path.Path;
import net.deechael.visual.api.animation.path.PathManager;
import net.deechael.visual.api.geometry.graphic.Line;
import net.deechael.visual.geometry.position.Point2dImpl;

public class PathManagerImpl implements PathManager {

    private final static Line LINE = VisualPlatform.getPlatform().getGeometry().createLine(0, 0, 0);

    @Override
    public Path createLine() {
        return LINE;
    }

    @Override
    public Path createEllipse(double halfShortAxis) {
        return VisualPlatform.getPlatform().getGeometry().createEllipse(new Point2dImpl(0, 0), halfShortAxis * 2, halfShortAxis);
    }

}
