package net.deechael.visual.api.geometry.position;

import net.deechael.visual.api.geometry.Angle;

public interface RotationMatrix3d {

    Vector3d rotate(Vector3d vector, Angle angle);

}
