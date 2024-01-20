package net.deechael.visual.api.geometry.graphic;

import net.deechael.visual.api.animation.path.Path;
import net.deechael.visual.api.geometry.position.Point2d;

public interface Ellipse extends Path {

    Point2d getCenter();

    double getHalfLongAxis();

    double getHalfShortAxis();

    Point2d[] getPointWithX(double x);

    Point2d[] getPointWithY(double y);

}
