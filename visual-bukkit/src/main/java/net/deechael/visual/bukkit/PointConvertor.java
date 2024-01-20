package net.deechael.visual.bukkit;

import net.deechael.visual.api.geometry.position.Point3d;
import net.deechael.visual.geometry.position.Coordinate3dImpl;
import org.bukkit.Location;
import org.bukkit.World;

public class PointConvertor {

    public static Point3d toVisual(Location location) {
        return Coordinate3dImpl.ROOT.getPoint(location.x(), -location.z(), location.y());
    }

    public static Location toBukkit(World world, Point3d point) {
        point = point.getAbsolute();
        return new Location(world, point.x(), point.z(), -point.y());
    }

    private PointConvertor() {
    }

}
