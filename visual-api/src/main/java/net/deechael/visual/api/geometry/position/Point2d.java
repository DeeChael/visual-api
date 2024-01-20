package net.deechael.visual.api.geometry.position;

import net.deechael.visual.api.geometry.Angle;

public interface Point2d {

    double x();

    double y();

    double length();

    Point2d getMidPointWith(Point2d another);

    Point2d add(double x, double y);

    Point2d add(Vector2d vector);

    Point2d subtract(double x, double y);

    Point2d subtract(Vector2d vector);

    Point2d rotate(Angle angle);

}
