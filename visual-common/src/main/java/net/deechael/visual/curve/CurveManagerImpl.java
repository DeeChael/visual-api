package net.deechael.visual.curve;

import net.deechael.visual.api.curve.CubicBezierCurve;
import net.deechael.visual.api.curve.CurveManager;
import net.deechael.visual.api.geometry.position.Point2d;
import net.deechael.visual.geometry.position.Point2dImpl;

public class CurveManagerImpl implements CurveManager {

    @Override
    public CubicBezierCurve linear() {
        return this.cubicBezier(new Point2dImpl(0, 0), new Point2dImpl(1, 1));
    }

    @Override
    public CubicBezierCurve ease() {
        return this.cubicBezier(new Point2dImpl(0.25, 0.1), new Point2dImpl(0.25, 0.1));
    }

    @Override
    public CubicBezierCurve easeIn() {
        return this.cubicBezier(new Point2dImpl(0.42, 0), new Point2dImpl(1, 1));
    }

    @Override
    public CubicBezierCurve easeOut() {
        return this.cubicBezier(new Point2dImpl(0, 0), new Point2dImpl(0.58, 1));
    }

    @Override
    public CubicBezierCurve easeInOut() {
        return this.cubicBezier(new Point2dImpl(0.42, 0), new Point2dImpl(0.58, 1));
    }

    @Override
    public CubicBezierCurve cubicBezier(Point2d point1, Point2d point2) {
        return new CubicBezierCurveImpl(point1.x(), point1.y(), point2.x(), point2.y());
    }

    @Override
    public CubicBezierCurve cubicBezier(double x1, double y1, double x2, double y2) {
        return this.cubicBezier(new Point2dImpl(x1, y1), new Point2dImpl(x2, y2));
    }

}
