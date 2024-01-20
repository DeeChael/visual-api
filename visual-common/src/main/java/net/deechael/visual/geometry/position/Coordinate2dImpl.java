package net.deechael.visual.geometry.position;

import net.deechael.visual.api.geometry.position.*;

public class Coordinate2dImpl implements Coordinate2d {

    public final static Coordinate2dImpl BOTTOM = new Coordinate2dImpl(null, new Vector3dImpl(1, 0, 0), new Vector3dImpl(0, 1, 0));

    private final Coordinate3d space;
    private final Vector3d xAxis;
    private final Vector3d yAxis;

    public Coordinate2dImpl(Coordinate3d space, Vector3d xAxis, Vector3d yAxis) {
        this.space = space;
        this.xAxis = xAxis.normalize();
        this.yAxis = yAxis.normalize();
    }

    @Override
    public Point2d getOrigin() {
        return new Point2dImpl(0, 0);
    }

    @Override
    public Point2d getPoint(double x, double y) {
        return new Point2dImpl(x, y);
    }

    @Override
    public Vector2d getVector(Point2d from, Point2d to) {
        return new Vector2dImpl(from.y() - to.y(), from.x() - to.x());
    }

    @Override
    public Vector2d getVector(double x, double y) {
        return new Vector2dImpl(x, y);
    }

    @Override
    public Vector3d getXAsis() {
        return this.xAxis;
    }

    @Override
    public Vector3d getYAsis() {
        return this.yAxis;
    }

    @Override
    public Point3d to3d(Point2d flat) {
        // Point2d rotatedPoint = flat.rotate(this.rotation);
        // double z = rotatedPoint.length() * angle.sin();

        // return new Point3dImpl(this.space, rotatedPoint.x() * angle.cos(), rotatedPoint.y() * angle.cos(), z);
        Vector3d vector3d = this.xAxis.scale(flat.x()).add(this.yAxis.scale(flat.y()));
        return this.space.getPoint(vector3d.x(), vector3d.y(), vector3d.z());
    }

    @Override
    public Vector3d to3d(Vector2d flat) {
        return this.space.getVector(this.space.getOrigin(), this.to3d(flat.toPoint(this.getOrigin())));
    }

    @Override
    public Coordinate3d getSpace() {
        return this.space;
    }

}
