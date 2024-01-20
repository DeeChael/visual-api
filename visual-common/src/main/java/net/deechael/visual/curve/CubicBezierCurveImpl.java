package net.deechael.visual.curve;

import net.deechael.visual.api.curve.CubicBezierCurve;
import net.deechael.visual.api.geometry.position.Point2d;
import net.deechael.visual.geometry.position.Point2dImpl;
import net.deechael.visual.math.Calculation;

public class CubicBezierCurveImpl extends CurveImpl implements CubicBezierCurve {

    private final double p0;
    private final double p1;
    private final double p2;
    private final double p3;

    public CubicBezierCurveImpl(double p0, double p1, double p2, double p3) {
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    @Override
    public Point2d getPoint(double x) {
        double right = 1.0 - x;
        double rightSquared = Calculation.square(right);
        double xSquared = Calculation.square(x);

        Point2d point2d = new Point2dImpl(0, 0);

        point2d = point2d.add(p0 * 3 * rightSquared * x, p1 * 3 * rightSquared * x);
        point2d = point2d.add(p2 * 3 * right * xSquared, p3 * 3 * right * xSquared);
        point2d = point2d.add(1 * Calculation.cubic(x), 1 * Calculation.cubic(x));

        return point2d;
    }

}
