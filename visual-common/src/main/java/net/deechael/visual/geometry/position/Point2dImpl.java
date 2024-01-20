package net.deechael.visual.geometry.position;

import net.deechael.visual.api.geometry.Angle;
import net.deechael.visual.api.geometry.position.Point2d;
import net.deechael.visual.api.geometry.position.Vector2d;
import net.deechael.visual.math.Calculation;

public class Point2dImpl implements Point2d {

    private final double x;
    private final double y;

    public Point2dImpl(double x, double y) {
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
    public Point2d getMidPointWith(Point2d another) {
        return new Point2dImpl((this.x + another.x()) / 2, (this.y + another.y()) / 2);
    }

    @Override
    public Point2d add(double x, double y) {
        return new Point2dImpl(this.x + x, this.y + y);
    }

    @Override
    public Point2d add(Vector2d vector) {
        return new Point2dImpl(this.x + vector.x(), this.y + vector.y());
    }

    @Override
    public Point2d subtract(double x, double y) {
        return new Point2dImpl(this.x - x, this.y - y);
    }

    @Override
    public Point2d subtract(Vector2d vector) {
        return new Point2dImpl(this.x - vector.x(), this.y - vector.y());
    }

    @Override
    public Point2d rotate(Angle angle) {
        double length = this.length();
        double baseRadians = Math.atan2(this.y, this.x);
        baseRadians += angle.toRadians();

        return new Point2dImpl(length * Math.cos(baseRadians), length * Math.sin(baseRadians));
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

}
