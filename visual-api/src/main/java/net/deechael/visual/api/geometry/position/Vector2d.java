package net.deechael.visual.api.geometry.position;

import net.deechael.visual.api.geometry.Angle;

public interface Vector2d {

    double x();

    double y();

    double length();

    Vector2d add(Vector2d another);

    Vector2d add(double x, double y);

    Vector2d subtract(Vector2d another);

    Vector2d subtract(double x, double y);

    Vector2d scale(double scale);

    Vector2d normalize();

    Vector2d reverse();

    Point2d getEndPoint();

    Point2d toPoint(Point2d start);

    double dotConduct(Vector2d another);

    Vector2d crossConduct(Vector2d another);

    double getProjectionOn(Vector2d vector);

    Vector2d getProjectionVectorOn(Vector2d vector);

    Angle getDeflectionAngle();

    Angle getAngleWith(Vector2d another);

    Angle getAngleWith(Vector2d another, double accuracy);

    boolean isParallel(Vector2d another);

    boolean isParallel(Vector2d another, double accuracy);

    boolean isVertical(Vector2d another);

    boolean isVertical(Vector2d another, double accuracy);

}
