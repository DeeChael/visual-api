package net.deechael.visual.api.curve;

import net.deechael.visual.api.geometry.position.Point2d;

public interface CurveManager {

    CubicBezierCurve linear();

    CubicBezierCurve ease();

    CubicBezierCurve easeIn();

    CubicBezierCurve easeOut();

    CubicBezierCurve easeInOut();

    CubicBezierCurve cubicBezier(Point2d point1, Point2d point2);

    CubicBezierCurve cubicBezier(double x1, double y1, double x2, double y2);

}
