package net.deechael.visual.geometry.position;

import net.deechael.visual.api.geometry.Angle;
import net.deechael.visual.api.geometry.position.*;
import net.deechael.visual.math.Calculation;

public class Coordinate3dImpl implements Coordinate3d {

    public final static Coordinate3d ROOT = new Coordinate3dImpl(null, 0, 0, 0);

    private final Coordinate3d parent;

    private final double offsetX;
    private final double offsetY;
    private final double offsetZ;

    private Coordinate3dImpl(Coordinate3d parent, double offsetX, double offsetY, double offsetZ) {
        this.parent = parent;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
    }

    @Override
    public Point3d getOrigin() {
        return new Point3dImpl(this, this.offsetX, this.offsetY, this.offsetZ);
    }

    @Override
    public Point3d getPoint(double x, double y, double z) {
        return new Point3dImpl(this, x, y, z);
    }

    @Override
    public Vector3d getVector(Point3d from, Point3d to) {
        return new Vector3dImpl(to.x() - from.x(), to.y() - from.y(), to.z() - from.z());
    }

    @Override
    public Vector3d getVector(double x, double y, double z) {
        return new Vector3dImpl(x, y, z);
    }

    @Override
    public Coordinate3d getParent() {
        if (this.isRoot())
            throw new RuntimeException("Root coordinate has no parent coordinate!");
        return this.parent;
    }

    @Override
    public Coordinate3d createCoordinate(Point3d relativeOrigin) {
        return new Coordinate3dImpl(this, relativeOrigin.x(), relativeOrigin.y(), relativeOrigin.z());
    }

    @Override
    public boolean isRoot() {
        return this.parent == null;
    }

    @Override
    public Coordinate2d makeCoordinate2d(Vector3d vector, Vector3d anotherVector) {
        if (vector.isParallel(anotherVector))
            throw new RuntimeException("Two parallel vector cannot define a 2d coordinate");

        Vector3d normal = vector.crossConduct(anotherVector);
        if (Calculation.smallerOrAlmostZero(normal.z(), 1e-6))
            normal = normal.reverse();

        Vector3d normalReverse = normal.reverse();

        double angleTan = Math.sqrt(Calculation.square(normal.x()) + Calculation.square(normal.y())) / Math.sqrt(Calculation.square(normal.z()));

        Vector3d vectorY = this.getVector(normalReverse.x(), normalReverse.y(), Math.sqrt(Calculation.square(normal.x()) + Calculation.square(normal.y())) * angleTan);

        Vector3d vertical = new Vector3dImpl(vectorY.y(), -vectorY.x(), 0);

        if (vectorY.y() >= 0) {
            if (vertical.y() < 0)
                vertical = new Vector3dImpl(-vectorY.y(), vectorY.x(), 0);
        } else {
            if (vertical.y() > 0)
                vertical = new Vector3dImpl(-vectorY.y(), vectorY.x(), 0);
        }

        Vector3d xAxis = new Vector3dImpl(vertical.x(), vertical.y(), 0);

        Vector3d yAxis = xAxis.crossConduct(normal);
        if (Calculation.smallerOrAlmostZero(yAxis.z(), 1e-6))
            yAxis = yAxis.reverse();

        // Angle rotation = Coordinate2dImpl.BOTTOM.getVector(vertical.x(), vertical.y()).getDeflectionAngle();
        // Angle angle = new RadiansAngle(Math.atan2(Math.sqrt(Calculation.square(vectorY.z())), Math.sqrt(Calculation.square(vectorY.x()) + Calculation.square(vectorY.y()))));

        return new Coordinate2dImpl(this, xAxis, yAxis);
    }

    @Override
    public Coordinate2d makeCoordinate2d(Vector3d xAxis, Angle verticalAngle) {
        Vector3d xAxisWithoutZ = new Vector3dImpl(xAxis.x(), xAxis.y(), 0);
        Vector3d normalOnXAxisFlat = xAxisWithoutZ.crossConduct(xAxisWithoutZ);

        RotationMatrix3d matrix = xAxis.createMatrix();

        Vector3d yAxis = matrix.rotate(normalOnXAxisFlat, verticalAngle);

        if (Calculation.smallerOrAlmostZero(yAxis.z(), 1e-6))
            yAxis = yAxis.reverse();

        return new Coordinate2dImpl(this, xAxis, yAxis);
    }

    @Override
    public Coordinate2d makeCoordinate2dByXAndY(Vector3d xAxis, Vector3d yAxis) {
        if (Calculation.smallerOrAlmostZero(yAxis.z(), 1e-6))
            yAxis = yAxis.reverse();

        return new Coordinate2dImpl(this, xAxis, yAxis);
    }

}
