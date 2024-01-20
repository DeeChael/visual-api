package net.deechael.visual.test.geometry.position;

import net.deechael.visual.api.geometry.position.Coordinate2d;
import net.deechael.visual.api.geometry.position.Coordinate3d;
import net.deechael.visual.api.geometry.position.Vector3d;
import net.deechael.visual.geometry.position.Coordinate3dImpl;
import org.junit.jupiter.api.Test;

public class CoordinateTest {

    @Test
    public void test() {
        Coordinate3d coordinate = Coordinate3dImpl.ROOT;

        Vector3d vector1 = coordinate.getVector(1, 1, 1);
        Vector3d vector2 = coordinate.getVector(-1, 1, 1);

        Vector3d vector3 = coordinate.getVector(0, 2, 2);

        Coordinate2d coordinate2d = coordinate.makeCoordinate2d(vector1, vector2);

        Vector3d converted = coordinate2d.to3d(coordinate2d.getVector(0, Math.sqrt(10)));

        System.out.println(converted);

        System.out.println(converted.isParallel(vector3));

        Vector3d unit = converted.normalize();

        System.out.println(unit);

        System.out.println(converted.length());
        System.out.println(unit.length());
    }

}
