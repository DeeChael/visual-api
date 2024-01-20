package net.deechael.visual.api.geometry;

import net.deechael.visual.api.geometry.graphic.Ellipse;
import net.deechael.visual.api.geometry.graphic.Line;
import net.deechael.visual.api.geometry.position.Coordinate3d;
import net.deechael.visual.api.geometry.position.Point2d;

public interface Geometry {

    Coordinate3d getRootCoordinate();

    Angle angleByRadians(double radians);

    Angle angleByDegree(double degree);

    Point2d createPoint(double x, double y);

    Ellipse createEllipse(Point2d center, double halfLongAxis, double halfShortAxis);

    Line createLine(double a, double b, double c);

}
