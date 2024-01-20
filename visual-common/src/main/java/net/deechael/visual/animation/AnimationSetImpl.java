package net.deechael.visual.animation;

import net.deechael.visual.api.Viewer;
import net.deechael.visual.api.VisualPlatform;
import net.deechael.visual.api.animation.Animation;
import net.deechael.visual.api.animation.AnimationSet;
import net.deechael.visual.api.animation.path.Path;
import net.deechael.visual.api.curve.Curve;
import net.deechael.visual.api.geometry.graphic.Ellipse;
import net.deechael.visual.api.geometry.graphic.Line;
import net.deechael.visual.api.geometry.position.*;
import net.deechael.visual.geometry.position.Point2dImpl;
import net.deechael.visual.geometry.position.Point3dImpl;
import net.deechael.visual.geometry.position.Vector3dImpl;
import net.deechael.visual.math.Calculation;

import java.util.ArrayList;
import java.util.List;

public class AnimationSetImpl implements AnimationSet {

    private final List<Animation> animations;

    public AnimationSetImpl(List<Animation> animations) {
        this.animations = animations;
    }

    @Override
    public List<Animation> listAnimations() {
        return new ArrayList<>(this.animations);
    }

    @Override
    public void play(Viewer viewer) {
        viewer.lock();

        viewer.ensureAsync(() -> {

            for (Animation animation : this.animations) {
                playSingle(viewer, animation);
            }

            viewer.ensureSync(viewer::release);

        });
    }

    private void playSingle(Viewer viewer, Animation animation) {
        Coordinate3d root = VisualPlatform.getPlatform().getGeometry().getRootCoordinate();

        Point3d start = animation.getStart();
        Point3d end = animation.getEnd();

        double times = animation.getDuration() * animation.getFramerate();
        long timeGap = (long) ((1.0 / animation.getFramerate() * 1000.0));

        Path path = animation.getPath();
        Curve curve = animation.getCurve();

        Point3d midPoint = root.getPoint((start.x() + end.x()) / 2, (start.y() + end.y()) / 2, (start.z() + end.z()) / 2);

        Coordinate3d childCoordinate = root.createCoordinate(midPoint);

        Point3d relativeStart = start.getRelative(childCoordinate);
        Point3d relativeEnd = end.getRelative(childCoordinate);

        if (path instanceof Ellipse) {
            double halfShortAxis = ((Ellipse) path).getHalfShortAxis();

            Vector3d xAxis = childCoordinate.getVector(relativeStart, relativeEnd);

            double longAxis = xAxis.length();
            double halfLongAxis = longAxis / 2.0;

            double startX = -halfLongAxis;

            Ellipse ellipse = VisualPlatform.getPlatform().getGeometry().createEllipse(new Point2dImpl(0, 0), halfLongAxis, halfShortAxis);

            if (animation.getPerspective() instanceof Point3d) {
                Point3d relativeLooking = ((Point3d) animation.getPerspective()).getRelative(childCoordinate);

                Vector3d tempVector = childCoordinate.getVector(relativeLooking, relativeStart);

                Coordinate2d flat = childCoordinate.makeCoordinate2dByXAndY(xAxis, tempVector.crossConduct(xAxis).crossConduct(xAxis));

                for (double i = 0; i <= times; i = i == times ? times + 1.0 : (Math.min(i + 1.0, times))) {
                    Point2d curvePoint = curve.getPoint(i / times);
                    double finalX = Math.min(startX + longAxis * curvePoint.y(), halfLongAxis);
                    Point2d[] point2ds = ellipse.getPointWithX(finalX);

                    Point2d point = point2ds.length == 1 ? point2ds[0] : point2ds[animation.isPathReversed() ? 0 : 1];

                    Point3d point3d = flat.to3d(point);

                    Vector3d direction = relativeLooking.toDirection(point3d);

                    if (animation.getVerticalAngle() != null)
                        direction = new Vector3dImpl(direction.x(), direction.y(), Math.sqrt(Calculation.square(direction.x()) + Calculation.square(direction.y())) * animation.getVerticalAngle().sin());

                    Vector3d finalDirection = direction;
                    viewer.ensureSync(() -> {
                        viewer.updateFrame(point3d, finalDirection, animation.isPerspectiveReversed());
                    });

                    try {
                        Thread.sleep(timeGap);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else if (animation.getPerspective() instanceof Vector3d) {
                Vector3d looking = (Vector3d) animation.getPerspective();

                Coordinate2d flat = childCoordinate.makeCoordinate2dByXAndY(xAxis, looking.crossConduct(xAxis).crossConduct(xAxis));

                for (double i = 0; i <= times; i = i == times ? times + 1.0 : (Math.min(i + 1.0, times))) {
                    Point2d curvePoint = curve.getPoint(i / times);
                    double finalX = Math.min(startX + longAxis * curvePoint.y(), halfLongAxis);
                    Point2d[] point2ds = ellipse.getPointWithX(finalX);

                    Point2d point = point2ds.length == 1 ? point2ds[0] : point2ds[animation.isPathReversed() ? 0 : 1];

                    Point3d point3d = flat.to3d(point);

                    Vector3d direction = looking.toDirection(point3d);

                    if (animation.getVerticalAngle() != null)
                        direction = new Vector3dImpl(direction.x(), direction.y(), Math.sqrt(Calculation.square(direction.x()) + Calculation.square(direction.y())) * animation.getVerticalAngle().sin());

                    Vector3d finalDirection = direction;

                    viewer.ensureSync(() -> {
                        viewer.updateFrame(point3d, finalDirection, animation.isPerspectiveReversed());
                    });

                    try {
                        Thread.sleep(timeGap);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            }

        } else if (path instanceof Line) {
            double deltaX = end.x() - start.x();
            double deltaY = end.y() - start.y();
            double deltaZ = end.z() - start.z();

            double startX = start.x();
            double startY = start.y();
            double startZ = start.z();

            for (double i = 0; i <= times; i = i == times ? times + 1.0 : (Math.min(i + 1.0, times))) {
                Point2d curvePoint = curve.getPoint(i / times);

                Point3d newPoint = new Point3dImpl(start.getCoordinate(), startX + deltaX * curvePoint.y(), startY + deltaY * curvePoint.y(), startZ + deltaZ * curvePoint.y());

                Vector3d direction = animation.getPerspective().toDirection(newPoint);

                if (animation.getVerticalAngle() != null)
                    direction = new Vector3dImpl(direction.x(), direction.y(), Math.sqrt(Calculation.square(direction.x()) + Calculation.square(direction.y())) * animation.getVerticalAngle().tan());

                Vector3d finalDirection = direction;
                viewer.ensureSync(() -> {
                    viewer.updateFrame(newPoint, finalDirection, animation.isPerspectiveReversed());
                });

                try {
                    Thread.sleep(timeGap);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
