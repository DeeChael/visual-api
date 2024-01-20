package net.deechael.visual.api.geometry.position;

import net.deechael.visual.api.geometry.Direction;

public interface Point3d extends Direction {

    double x();

    double y();

    double z();

    Point3d getMidPointWith(Point3d another);

    Point3d add(double x, double y, double z);

    Point3d add(Vector3d vector);

    Point3d subtract(double x, double y, double z);

    Point3d subtract(Vector3d vector);

    Point3d getAbsolute();

    Point3d getRelative(Coordinate3d coordinate);

    Coordinate3d getCoordinate();

}
