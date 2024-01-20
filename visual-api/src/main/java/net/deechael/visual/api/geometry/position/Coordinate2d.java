package net.deechael.visual.api.geometry.position;

public interface Coordinate2d {

    Point2d getOrigin();

    Point2d getPoint(double x, double y);

    Vector2d getVector(Point2d from, Point2d to);

    Vector2d getVector(double x, double y);

    Vector3d getXAsis();

    Vector3d getYAsis();

    Point3d to3d(Point2d flat);

    Vector3d to3d(Vector2d flat);

    Coordinate3d getSpace();

}
