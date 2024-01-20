package net.deechael.visual.geometry.graphic;

import net.deechael.visual.api.geometry.graphic.Ellipse;
import net.deechael.visual.api.geometry.position.Point2d;
import net.deechael.visual.geometry.position.Point2dImpl;
import net.deechael.visual.math.Calculation;

public class EllipseImpl implements Ellipse {

    private final Point2d center;
    private final double halfLongAxis;
    private final double halfShortAxis;

    public EllipseImpl(Point2d center, double halfLongAxis, double halfShortAxis) {
        this.center = center;
        this.halfLongAxis = halfLongAxis;
        this.halfShortAxis = halfShortAxis;
    }

    @Override
    public Point2d getCenter() {
        return this.center;
    }

    @Override
    public double getHalfLongAxis() {
        return this.halfLongAxis;
    }

    @Override
    public double getHalfShortAxis() {
        return this.halfShortAxis;
    }

    @Override
    public Point2d[] getPointWithX(double x) {
        if (Math.abs(x - this.center.x()) > this.halfLongAxis)
            throw new ArrayIndexOutOfBoundsException("Cannot find point on this ellipse");
        if (Math.abs(x - this.center.x()) == this.halfLongAxis)
            return new Point2d[]{new Point2dImpl(x, 0)};
        double y = Math.sqrt(Calculation.square(this.halfShortAxis) * (1.0 - Calculation.square(x - this.center.x()) / Calculation.square(this.halfLongAxis)));
        return new Point2d[]{new Point2dImpl(x, this.center.y() + y), new Point2dImpl(x, this.center.y() - y)};
    }

    @Override
    public Point2d[] getPointWithY(double y) {
        if (Math.abs(y - this.center.y()) > this.halfShortAxis)
            throw new ArrayIndexOutOfBoundsException("Cannot find point on this ellipse");
        if (Math.abs(y - this.center.y()) == this.halfShortAxis)
            return new Point2d[]{new Point2dImpl(0, y)};
        double x = Math.sqrt(Calculation.square(this.halfLongAxis) * (1.0 - Calculation.square(y - this.center.y()) / Calculation.square(this.halfShortAxis)));
        return new Point2d[]{new Point2dImpl(this.center.x() + x, y), new Point2dImpl(this.center.x() - x, y)};
    }

}
