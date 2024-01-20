package net.deechael.visual.geometry.graphic;

import net.deechael.visual.api.geometry.graphic.Line;
import net.deechael.visual.api.geometry.position.Point2d;
import net.deechael.visual.geometry.position.Point2dImpl;

public class LineImpl implements Line {

    private final double a;
    private final double b;
    private final double c;

    public LineImpl(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double getA() {
        return 0;
    }

    @Override
    public double getB() {
        return 0;
    }

    @Override
    public double getC() {
        return 0;
    }

    @Override
    public double getSlope() {
        return (-a) / b;
    }

    @Override
    public boolean hasSlope() {
        return this.b != 0;
    }

    @Override
    public Point2d getPointWithX(double x) {
        return new Point2dImpl(x, (this.a * x + this.c) / (-this.b));
    }

    @Override
    public Point2d getPointWithY(double y) {
        return new Point2dImpl((this.b * y + this.c) / (-this.a), y);
    }

}
