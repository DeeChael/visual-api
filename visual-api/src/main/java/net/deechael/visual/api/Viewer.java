package net.deechael.visual.api;

import net.deechael.visual.api.geometry.position.Point3d;
import net.deechael.visual.api.geometry.position.Vector3d;

public interface Viewer {

    Point3d getPoint();

    boolean isLocked();

    boolean isReleased();

    void lock();

    void ensureAsync(Runnable runnable);

    void ensureSync(Runnable runnable);

    void release();

    void updateFrame(Point3d point, Vector3d direction, boolean reverse);
}
