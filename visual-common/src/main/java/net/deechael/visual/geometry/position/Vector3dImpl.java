package net.deechael.visual.geometry.position;

import net.deechael.visual.api.geometry.Angle;
import net.deechael.visual.api.geometry.position.Coordinate3d;
import net.deechael.visual.api.geometry.position.RotationMatrix3d;
import net.deechael.visual.api.geometry.position.Point3d;
import net.deechael.visual.api.geometry.position.Vector3d;
import net.deechael.visual.geometry.angle.RadiansAngle;
import net.deechael.visual.math.Calculation;

public class Vector3dImpl implements Vector3d {

    private final double x;
    private final double y;
    private final double z;

    public Vector3dImpl(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }

    @Override
    public double length() {
        return Math.sqrt(Calculation.square(this.x) + Calculation.square(this.y) + Calculation.square(this.z));
    }

    @Override
    public Vector3d add(Vector3d another) {
        return new Vector3dImpl(this.x() + another.x(), this.y() + another.y(), this.z() + another.z());
    }

    @Override
    public Vector3d add(double x, double y, double z) {
        return new Vector3dImpl(this.x + x, this.y + y, this.z + z);
    }

    @Override
    public Vector3d subtract(Vector3d another) {
        return new Vector3dImpl(this.x() + another.x(), this.y() + another.y(), this.z() + another.z());
    }

    @Override
    public Vector3d subtract(double x, double y, double z) {
        return new Vector3dImpl(this.x - x, this.y - y, this.z - z);
    }

    @Override
    public Vector3d scale(double scale) {
        double newX = Calculation.almostZero(this.x, 1e-6) ? 0 : this.x * scale;
        double newY = Calculation.almostZero(this.y, 1e-6) ? 0 : this.y * scale;
        double newZ = Calculation.almostZero(this.z, 1e-6) ? 0 : this.z * scale;
        return new Vector3dImpl(newX, newY, newZ);
    }

    @Override
    public Vector3d normalize() {
        double length = this.length();
        return new Vector3dImpl(this.x / length, this.y / length, this.z / length);
    }

    @Override
    public Point3d getEndPoint(Coordinate3d coordinate) {
        return new Point3dImpl(coordinate, this.x, this.y, this.z);
    }

    @Override
    public Point3d toPoint(Point3d startPoint) {
        return new Point3dImpl(startPoint.getCoordinate(), startPoint.x() + this.x, startPoint.y() + this.y, startPoint.z() + this.z);
    }

    @Override
    public double dotConduct(Vector3d another) {
        return this.x() * another.x() + this.y() * another.y() + this.z() * another.z();
    }

    @Override
    public Vector3d crossConduct(Vector3d another) {
        double thisX = this.x();
        double thisY = this.y();
        double thisZ = this.z();
        double anotherX = another.x();
        double anotherY = another.y();
        double anotherZ = another.z();
        return new Vector3dImpl(thisY * anotherZ - thisZ * anotherY, thisZ * anotherX - thisX * anotherZ, thisX * anotherY - thisY * anotherX);
    }

    @Override
    public Vector3d reverse() {
        return new Vector3dImpl(-this.x, -this.y, -this.z);
    }

    @Override
    public double getProjectionOn(Vector3d vector) {
        return this.length() * (this.getAngleWith(vector, 1e-6)).cos();
    }

    @Override
    public Vector3d getProjectionVectorOn(Vector3d vector) {
        return vector.normalize().scale(this.getProjectionOn(vector));
    }

    @Override
    public RotationMatrix3d createMatrix() {
        return new RotationMatrix3DImpl(this);
    }

    @Override
    public Angle getAngleWith(Vector3d another) {
        return this.getAngleWith(another, 1e-6);
    }

    @Override
    public Angle getAngleWith(Vector3d another, double accuracy) {
        if (Calculation.almostZero(this.x() * another.x(), accuracy) && Calculation.almostZero(this.y() * another.y(), accuracy) && Calculation.almostZero(this.z() * another.z(), accuracy))
            return null;
        return new RadiansAngle(Math.acos(this.dotConduct(another) / (this.length() * another.length())));
    }

    @Override
    public boolean isParallel(Vector3d another) {
        return this.isParallel(another, 1e-6D);
    }

    @Override
    public boolean isParallel(Vector3d another, double accuracy) {
        if (Calculation.almostZero(this.x(), accuracy) && !Calculation.almostZero(another.x(), accuracy))
            return false;
        if (Calculation.almostZero(this.y(), accuracy) && !Calculation.almostZero(another.y(), accuracy))
            return false;
        if (Calculation.almostZero(this.z(), accuracy) && !Calculation.almostZero(another.z(), accuracy))
            return false;
        if (!Calculation.almostZero(this.x(), accuracy) && Calculation.almostZero(another.x(), accuracy))
            return false;
        if (!Calculation.almostZero(this.y(), accuracy) && Calculation.almostZero(another.y(), accuracy))
            return false;
        if (!Calculation.almostZero(this.z(), accuracy) && Calculation.almostZero(another.z(), accuracy))
            return false;

        if (Calculation.almostZero(this.x(), accuracy) && Calculation.almostZero(another.x(), accuracy)) {
            if (Calculation.almostZero(this.y(), accuracy) && Calculation.almostZero(another.y(), accuracy))
                return true;

            if (Calculation.almostZero(this.z(), accuracy) && Calculation.almostZero(another.z(), accuracy))
                return true;

            double deltaY = another.y() / this.y();
            double deltaZ = another.z() / this.z();

            return Calculation.almostZero(Math.abs(deltaZ - deltaY), accuracy);
        }

        if (Calculation.almostZero(this.y(), accuracy) && Calculation.almostZero(another.y(), accuracy)) {

            if (Calculation.almostZero(this.z(), accuracy) && Calculation.almostZero(another.z(), accuracy))
                return true;

            double deltaX = another.x() / this.x();
            double deltaZ = another.z() / this.z();

            return Calculation.almostZero(Math.abs(deltaZ - deltaX), accuracy);
        }

        if (Calculation.almostZero(this.z(), accuracy) && Calculation.almostZero(another.z(), accuracy)) {

            double deltaX = another.x() / this.x();
            double deltaY = another.y() / this.y();

            return Calculation.almostZero(Math.abs(deltaY - deltaX), accuracy);
        }

        double deltaX = another.x() / this.x();
        double deltaY = another.y() / this.y();
        double deltaZ = another.z() / this.z();
        return (Calculation.almostZero(deltaX - deltaY, accuracy) && Calculation.almostZero(deltaZ - deltaX, accuracy) && Calculation.almostZero(deltaY - deltaZ, accuracy));
    }

    @Override
    public boolean isVertical(Vector3d another) {
        return this.isVertical(another, 1e-6);
    }

    @Override
    public boolean isVertical(Vector3d another, double accuracy) {
        return Calculation.almostZero(this.dotConduct(another), accuracy);
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ")";
    }

    @Override
    public Vector3d toDirection(Point3d startPoint) {
        return this;
    }

}
