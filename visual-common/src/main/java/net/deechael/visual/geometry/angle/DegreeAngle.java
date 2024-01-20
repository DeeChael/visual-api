package net.deechael.visual.geometry.angle;

import net.deechael.visual.api.geometry.Angle;

public class DegreeAngle extends AbstractAngle {

    private final double degree;

    public DegreeAngle(double degree) {
        this.degree = degree;
    }

    @Override
    public double toRadians() {
        return Math.toRadians(degree);
    }

    @Override
    public double toDegree() {
        return this.degree;
    }

    @Override
    public Angle getSimplest() {
        double degree = this.degree;
        while (degree >= 360)
            degree -= 360;
        while (degree < 0)
            degree += 360;
        return new DegreeAngle(degree);
    }

}
