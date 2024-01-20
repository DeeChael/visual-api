package net.deechael.visual.api.geometry.position;

import net.deechael.visual.api.geometry.Angle;
import net.deechael.visual.api.geometry.Direction;

public interface Vector3d extends Direction {

    double x();

    double y();

    double z();

    double length();

    Vector3d add(Vector3d another);

    Vector3d add(double x, double y, double z);

    Vector3d subtract(Vector3d another);

    Vector3d subtract(double x, double y, double z);

    Vector3d scale(double scale);

    Vector3d normalize();

    Vector3d reverse();

    Point3d getEndPoint(Coordinate3d coordinate);

    Point3d toPoint(Point3d startPoint);

    double dotConduct(Vector3d another);

    Vector3d crossConduct(Vector3d another);

    double getProjectionOn(Vector3d vector);

    Vector3d getProjectionVectorOn(Vector3d vector);

    RotationMatrix3d createMatrix();

    Angle getAngleWith(Vector3d another);

    Angle getAngleWith(Vector3d another, double accuracy);

    boolean isParallel(Vector3d another);

    boolean isParallel(Vector3d another, double accuracy);

    boolean isVertical(Vector3d another);

    boolean isVertical(Vector3d another, double accuracy);

}
