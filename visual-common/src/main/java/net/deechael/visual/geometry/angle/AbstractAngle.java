package net.deechael.visual.geometry.angle;

import net.deechael.visual.api.geometry.Angle;

public abstract class AbstractAngle implements Angle {

    @Override
    public double sin() {
        return Math.sin(this.toRadians());
    }

    @Override
    public double cos() {
        return Math.cos(this.toRadians());
    }

    @Override
    public double tan() {
        return Math.tan(this.toRadians());
    }

    @Override
    public double ctan() {
        return this.cos() / this.sin();
    }

    @Override
    public String toString() {
        return "Angle(" + this.toRadians() + ", " + this.toDegree() + ")";
    }

}
