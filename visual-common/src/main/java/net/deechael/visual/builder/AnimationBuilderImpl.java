package net.deechael.visual.builder;

import net.deechael.visual.animation.AnimationImpl;
import net.deechael.visual.api.VisualPlatform;
import net.deechael.visual.api.animation.Animation;
import net.deechael.visual.api.animation.path.Path;
import net.deechael.visual.api.builder.AnimationBuilder;
import net.deechael.visual.api.curve.Curve;
import net.deechael.visual.api.geometry.Angle;
import net.deechael.visual.api.geometry.Direction;
import net.deechael.visual.api.geometry.position.Point3d;
import net.deechael.visual.util.Preconditions;

public class AnimationBuilderImpl implements AnimationBuilder {

    private Point3d start;
    private Point3d end;
    private double seconds = -1;
    private int framerate = -1;
    private Path path;
    private boolean reversePath;
    private Direction perspective;
    private boolean reversePerspective;
    private Angle verticalAngle;
    private Curve curve;

    @Override
    public AnimationBuilder start(Point3d point) {
        Preconditions.notNull(point, "Point cannot be null");

        this.start = point.getAbsolute();
        return this;
    }

    @Override
    public AnimationBuilder end(Point3d point) {
        Preconditions.notNull(point, "Point cannot be null");

        this.end = point.getAbsolute();
        return this;
    }

    @Override
    public AnimationBuilder duration(double seconds) {
        Preconditions.range(seconds, 0, Double.MAX_VALUE, "Duration should be in range [0,)");

        this.seconds = seconds;
        return this;
    }

    @Override
    public AnimationBuilder framerate(int framerate) {
        Preconditions.range(framerate, 1, 1000, "Framerate should be in range (0, 1000)");

        this.framerate = framerate;
        return this;
    }

    @Override
    public AnimationBuilder path(Path path, boolean reverse) {
        Preconditions.notNull(path, "Direction cannot be null");

        this.path = path;
        this.reversePath = reverse;
        return this;
    }

    @Override
    public AnimationBuilder perspective(Direction direction, boolean reverse) {
        Preconditions.notNull(direction, "Direction cannot be null");

        this.perspective = direction;
        this.reversePerspective = reverse;

        if (this.perspective instanceof Point3d)
            this.perspective = ((Point3d) this.perspective).getAbsolute();

        return this;
    }

    @Override
    public AnimationBuilder verticalAngle(Angle angle) {
        this.verticalAngle = angle;
        return this;
    }

    @Override
    public AnimationBuilder curve(Curve curve) {
        Preconditions.notNull(curve, "Curve cannot be null");
        this.curve = curve;
        return this;
    }

    @Override
    public Animation build() {
        Preconditions.notNull(this.start, "Start point cannot be null");
        Preconditions.notNull(this.end, "End point cannot be null");
        Preconditions.notNull(this.perspective, "Perspective cannot be null");
        if (this.seconds <= 0)
            throw new RuntimeException("Duration must be set");
        if (this.framerate <= 0)
            throw new RuntimeException("Framerate must be set");

        if (this.path == null)
            this.path = VisualPlatform.getPlatform().getAnimationManager().getPathManager().createLine();

        if (this.curve == null)
            this.curve = VisualPlatform.getPlatform().getCurveManager().linear();

        return new AnimationImpl(
                this.start,
                this.end,
                this.seconds,
                this.framerate,
                this.path,
                this.reversePath,
                this.perspective,
                this.reversePerspective,
                this.verticalAngle,
                this.curve
        );
    }

}
