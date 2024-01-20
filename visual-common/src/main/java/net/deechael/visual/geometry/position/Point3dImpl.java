package net.deechael.visual.geometry.position;

import net.deechael.visual.api.geometry.position.Coordinate3d;
import net.deechael.visual.api.geometry.position.Point3d;
import net.deechael.visual.api.geometry.position.Vector3d;

public class Point3dImpl implements Point3d {

    private final Coordinate3d coordinate;

    private final double x;
    private final double y;
    private final double z;

    public Point3dImpl(Coordinate3d coordinate, double x, double y, double z) {
        this.coordinate = coordinate;
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
    public Point3d getMidPointWith(Point3d another) {
        Point3d thisAbsolute = this.getAbsolute();
        Point3d anotherAbsolute = another.getAbsolute();
        return new Point3dImpl(Coordinate3dImpl.ROOT, (thisAbsolute.x() + anotherAbsolute.x()) / 2, (thisAbsolute.y() + anotherAbsolute.y()) / 2, (thisAbsolute.z() + anotherAbsolute.z()) / 2).getRelative(this.coordinate);
    }

    @Override
    public Point3d add(double x, double y, double z) {
        return new Point3dImpl(this.coordinate, this.x + x, this.y + y, this.z + z);
    }

    @Override
    public Point3d add(Vector3d vector) {
        return new Point3dImpl(this.coordinate, this.x + vector.x(), this.y + vector.y(), this.z + vector.z());
    }

    @Override
    public Point3d subtract(double x, double y, double z) {
        return new Point3dImpl(this.coordinate, this.x - x, this.y - y, this.z - z);
    }

    @Override
    public Point3d subtract(Vector3d vector) {
        return new Point3dImpl(this.coordinate, this.x - vector.x(), this.y - vector.y(), this.z - vector.z());
    }

    @Override
    public Point3d getAbsolute() {
        if (this.coordinate.isRoot())
            return this;
        Point3d coordinateOrigin = this.coordinate.getOrigin();
        return Coordinate3dImpl.ROOT.getVector(coordinateOrigin.x() + this.x, coordinateOrigin.y() + this.y, coordinateOrigin.z() + this.z).getEndPoint(Coordinate3dImpl.ROOT);
    }

    @Override
    public Point3d getRelative(Coordinate3d coordinate) {
        Point3d absolute = this.getAbsolute();
        if (coordinate.isRoot())
            return absolute;
        Point3d origin = coordinate.getOrigin();
        return new Point3dImpl(coordinate, absolute.x() - origin.x(), absolute.y() - origin.y(), absolute.z() - origin.z());
    }

    @Override
    public Coordinate3d getCoordinate() {
        return this.coordinate;
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ")";
    }

    @Override
    public Vector3d toDirection(Point3d startPoint) {
        return Coordinate3dImpl.ROOT.getVector(startPoint.getAbsolute(), this.getAbsolute());
    }

}
