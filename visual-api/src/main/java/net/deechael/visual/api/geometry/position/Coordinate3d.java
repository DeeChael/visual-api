package net.deechael.visual.api.geometry.position;

import net.deechael.visual.api.geometry.Angle;

public interface Coordinate3d {

    Point3d getOrigin();

    Point3d getPoint(double x, double y, double z);

    Vector3d getVector(Point3d from, Point3d to);

    Vector3d getVector(double x, double y, double z);

    Coordinate3d getParent();

    Coordinate3d createCoordinate(Point3d relativeOrigin);

    boolean isRoot();

    Coordinate2d makeCoordinate2d(Vector3d vector, Vector3d anotherVector);

    Coordinate2d makeCoordinate2d(Vector3d xAxis, Angle verticalAngle);

    Coordinate2d makeCoordinate2dByXAndY(Vector3d xAxis, Vector3d yAxis);
}
