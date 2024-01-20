package net.deechael.visual.api.animation;

import net.deechael.visual.api.animation.path.Path;
import net.deechael.visual.api.curve.Curve;
import net.deechael.visual.api.geometry.Angle;
import net.deechael.visual.api.geometry.Direction;
import net.deechael.visual.api.geometry.position.Point3d;

public interface Animation {

    Point3d getStart();

    Point3d getEnd();

    double getDuration();

    int getFramerate();

    Path getPath();

    boolean isPathReversed();

    Direction getPerspective();

    boolean isPerspectiveReversed();

    Angle getVerticalAngle();

    Curve getCurve();

}
