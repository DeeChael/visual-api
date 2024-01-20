package net.deechael.visual.animation;

import net.deechael.visual.api.animation.Animation;
import net.deechael.visual.api.animation.path.Path;
import net.deechael.visual.api.curve.Curve;
import net.deechael.visual.api.geometry.Angle;
import net.deechael.visual.api.geometry.Direction;
import net.deechael.visual.api.geometry.position.Point3d;

public class AnimationImpl implements Animation {

    private final Point3d start;
    private final Point3d end;
    private final double seconds;
    private final int framerate;
    private final Path path;
    private final boolean reversePath;
    private final Direction perspective;
    private final boolean reversePerspective;
    private final Angle verticalAngle;
    private final Curve curve;

    public AnimationImpl(Point3d start, Point3d end, double seconds, int framerate, Path path, boolean reversePath, Direction perspective, boolean reversePerspective, Angle verticalAngle, Curve curve) {
        this.start = start;
        this.end = end;
        this.seconds = seconds;
        this.framerate = framerate;
        this.path = path;
        this.reversePath = reversePath;
        this.perspective = perspective;
        this.reversePerspective = reversePerspective;
        this.verticalAngle = verticalAngle;
        this.curve = curve;
    }

    @Override
    public Point3d getStart() {
        return start;
    }

    @Override
    public Point3d getEnd() {
        return end;
    }

    @Override
    public double getDuration() {
        return seconds;
    }

    @Override
    public int getFramerate() {
        return framerate;
    }

    @Override
    public Path getPath() {
        return path;
    }

    @Override
    public boolean isPathReversed() {
        return reversePath;
    }

    @Override
    public Direction getPerspective() {
        return perspective;
    }

    @Override
    public boolean isPerspectiveReversed() {
        return this.reversePerspective;
    }

    @Override
    public Angle getVerticalAngle() {
        return verticalAngle;
    }

    @Override
    public Curve getCurve() {
        return curve;
    }

}
