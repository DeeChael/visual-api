package net.deechael.visual.api.geometry.graphic;

import net.deechael.visual.api.animation.path.Path;
import net.deechael.visual.api.geometry.position.Point2d;

public interface Line extends Path {

    double getA();

    double getB();

    double getC();

    double getSlope();

    boolean hasSlope();

    Point2d getPointWithX(double x);

    Point2d getPointWithY(double y);

}
