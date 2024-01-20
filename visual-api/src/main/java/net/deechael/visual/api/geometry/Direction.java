package net.deechael.visual.api.geometry;

import net.deechael.visual.api.geometry.position.Point3d;
import net.deechael.visual.api.geometry.position.Vector3d;

public interface Direction {

    Vector3d toDirection(Point3d startPoint);

}
