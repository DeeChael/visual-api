package net.deechael.visual.geometry;

import net.deechael.visual.api.geometry.Angle;
import net.deechael.visual.api.geometry.Geometry;
import net.deechael.visual.api.geometry.graphic.Ellipse;
import net.deechael.visual.api.geometry.graphic.Line;
import net.deechael.visual.api.geometry.position.Coordinate3d;
import net.deechael.visual.api.geometry.position.Point2d;
import net.deechael.visual.geometry.angle.DegreeAngle;
import net.deechael.visual.geometry.angle.RadiansAngle;
import net.deechael.visual.geometry.graphic.EllipseImpl;
import net.deechael.visual.geometry.graphic.LineImpl;
import net.deechael.visual.geometry.position.Coordinate3dImpl;
import net.deechael.visual.geometry.position.Point2dImpl;

public class GeometryImpl implements Geometry {

    @Override
    public Coordinate3d getRootCoordinate() {
        return Coordinate3dImpl.ROOT;
    }

    @Override
    public Angle angleByRadians(double radians) {
        return new RadiansAngle(radians);
    }

    @Override
    public Angle angleByDegree(double degree) {
        return new DegreeAngle(degree);
    }

    @Override
    public Point2d createPoint(double x, double y) {
        return new Point2dImpl(x, y);
    }

    @Override
    public Ellipse createEllipse(Point2d center, double halfLongAxis, double halfShortAxis) {
        return new EllipseImpl(center, halfLongAxis, halfShortAxis);
    }

    @Override
    public Line createLine(double a, double b, double c) {
        return new LineImpl(a, b, c);
    }

}
