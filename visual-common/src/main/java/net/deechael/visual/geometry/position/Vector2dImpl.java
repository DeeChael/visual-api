package net.deechael.visual.geometry.position;

import net.deechael.visual.api.geometry.Angle;
import net.deechael.visual.api.geometry.position.Point2d;
import net.deechael.visual.api.geometry.position.Vector2d;
import net.deechael.visual.geometry.angle.RadiansAngle;
import net.deechael.visual.math.Calculation;

public class Vector2dImpl implements Vector2d {

    private final double x;
    private final double y;

    public Vector2dImpl(double x, double y) {
        this.x = x;
        this.y = y;
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
    public double length() {
        return Math.sqrt(Calculation.square(this.x) + Calculation.square(this.y));
    }

    @Override
    public Vector2d add(Vector2d another) {
        return new Vector2dImpl(this.x + another.x(), this.y + another.y());
    }

    @Override
    public Vector2d add(double x, double y) {
        return new Vector2dImpl(this.x + x, this.y + y);
    }

    @Override
    public Vector2d subtract(Vector2d another) {
        return new Vector2dImpl(this.x - another.x(), this.y - another.y());
    }

    @Override
    public Vector2d subtract(double x, double y) {
        return new Vector2dImpl(this.x - x, this.y - y);
    }

    @Override
    public Vector2d scale(double scale) {
        return new Vector2dImpl(this.x * scale, this.y * scale);
    }

    @Override
    public Vector2d normalize() {
        double length = this.length();
        return new Vector2dImpl(this.x / length, this.y / length);
    }

    @Override
    public Vector2d reverse() {
        return new Vector2dImpl(-this.x, -this.y);
    }

    @Override
    public Point2d getEndPoint() {
        return new Point2dImpl(this.x, this.y);
    }

    @Override
    public Point2d toPoint(Point2d start) {
        return new Point2dImpl(start.x() + this.x, start.y() + this.y);
    }

    @Override
    public double dotConduct(Vector2d another) {
        return this.x * another.x() + this.y * another.y();
    }

    @Override
    public Vector2d crossConduct(Vector2d another) {
        return new Vector2dImpl(this.x * another.y() - this.y * another.x(), this.y * another.x() - this.x * another.y());
    }

    @Override
    public double getProjectionOn(Vector2d vector) {
        return this.length() * (this.getAngleWith(vector, 1e-6)).cos();
    }

    @Override
    public Vector2d getProjectionVectorOn(Vector2d vector) {
        return vector.normalize().scale(this.getProjectionOn(vector));
    }

    @Override
    public Angle getDeflectionAngle() {
        double radians = Math.atan2(this.y, this.x);
        return new RadiansAngle(radians);
    }

    @Override
    public Angle getAngleWith(Vector2d another) {
        return this.getAngleWith(another, 1e-6);
    }

    @Override
    public Angle getAngleWith(Vector2d another, double accuracy) {
        if (Calculation.almostZero(this.x() * another.x(), accuracy) && Calculation.almostZero(this.y() * another.y(), accuracy))
            return null;
        return new RadiansAngle(Math.acos(this.dotConduct(another) / (this.length() * another.length())));
    }

    @Override
    public boolean isParallel(Vector2d another) {
        return this.isParallel(another, 1e-6);
    }

    @Override
    public boolean isParallel(Vector2d another, double accuracy) {
        if (Calculation.almostZero(this.x(), accuracy) && !Calculation.almostZero(another.x(), accuracy))
            return false;
        if (Calculation.almostZero(this.y(), accuracy) && !Calculation.almostZero(another.y(), accuracy))
            return false;
        if (!Calculation.almostZero(this.x(), accuracy) && Calculation.almostZero(another.x(), accuracy))
            return false;
        if (!Calculation.almostZero(this.y(), accuracy) && Calculation.almostZero(another.y(), accuracy))
            return false;

        if (Calculation.almostZero(this.x(), accuracy) && Calculation.almostZero(another.x(), accuracy))
            return true;

        if (Calculation.almostZero(this.y(), accuracy) && Calculation.almostZero(another.y(), accuracy))
            return true;

        double deltaX = another.x() / this.x();
        double deltaY = another.y() / this.y();
        return Calculation.almostZero(deltaX - deltaY, accuracy);
    }

    @Override
    public boolean isVertical(Vector2d another) {
        return this.isVertical(another, 1e-6);
    }

    @Override
    public boolean isVertical(Vector2d another, double accuracy) {
        return Calculation.almostZero(this.dotConduct(another), accuracy);
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

}
