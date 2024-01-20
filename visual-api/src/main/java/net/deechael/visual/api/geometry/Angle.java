package net.deechael.visual.api.geometry;

public interface Angle {

    double toRadians();

    double toDegree();

    double sin();

    double cos();

    double tan();

    double ctan();

    Angle getSimplest();

}
