package net.deechael.visual.geometry.angle;

import net.deechael.visual.api.geometry.Angle;

public class RadiansAngle extends AbstractAngle {

    private final double radians;

    public RadiansAngle(double radians) {
        this.radians = radians;
    }

    @Override
    public double toRadians() {
        return this.radians;
    }

    @Override
    public double toDegree() {
        return Math.toDegrees(this.radians);
    }

    @Override
    public Angle getSimplest() {
        double radians = this.radians;
        while (radians >= 2 * Math.PI)
            radians -= Math.PI;
        while (radians < 0)
            radians += Math.PI;
        return new RadiansAngle(radians);
    }

}
