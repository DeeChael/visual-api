package net.deechael.visual.api.builder;

import net.deechael.visual.api.animation.Animation;
import net.deechael.visual.api.animation.path.Path;
import net.deechael.visual.api.curve.Curve;
import net.deechael.visual.api.geometry.Angle;
import net.deechael.visual.api.geometry.Direction;
import net.deechael.visual.api.geometry.position.Point3d;

public interface AnimationBuilder {

    AnimationBuilder start(Point3d point);

    AnimationBuilder end(Point3d point);

    AnimationBuilder duration(double seconds);

    AnimationBuilder framerate(int framerate);

    AnimationBuilder path(Path path, boolean reverse);

    AnimationBuilder perspective(Direction direction, boolean reverse);

    AnimationBuilder verticalAngle(Angle angle);

    AnimationBuilder curve(Curve curve);

    Animation build();

}
