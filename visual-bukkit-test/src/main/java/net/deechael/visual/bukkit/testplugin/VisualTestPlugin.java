package net.deechael.visual.bukkit.testplugin;

import net.deechael.visual.api.VisualPlatform;
import net.deechael.visual.api.animation.AnimationSet;
import net.deechael.visual.api.curve.Curve;
import net.deechael.visual.api.geometry.position.Coordinate3d;
import net.deechael.visual.api.geometry.position.Point3d;
import net.deechael.visual.api.geometry.position.Vector3d;
import net.deechael.visual.bukkit.VisualBukkitPlugin;
import net.deechael.visual.geometry.position.Point2dImpl;
import net.deechael.visual.geometry.position.Point3dImpl;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class VisualTestPlugin extends JavaPlugin {

    private static int[] parse(String[] args, int index) {
        int[] ints = new int[3];
        for (int i = index; i < index + 3; i++) {
            ints[i - index] = Integer.parseInt(args[i]);
        }
        return ints;
    }

    @Override
    public void onEnable() {


        Coordinate3d root = VisualPlatform.getPlatform().getGeometry().getRootCoordinate();

        Bukkit.getCommandMap().register("visual", new Command("line") {
            @Override
            public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String[] args) {
                if (args.length != 7)
                    return true;
                if (!(sender instanceof Player))
                    return true;

                int[] startXYZ = parse(args, 0);
                int[] endXYZ = parse(args, 3);

                String curveType = args[6];

                Curve curve = switch (curveType) {
                    case "linear" -> VisualPlatform.getPlatform().getCurveManager().linear();
                    case "ease" -> VisualPlatform.getPlatform().getCurveManager().ease();
                    case "easeIn" -> VisualPlatform.getPlatform().getCurveManager().easeIn();
                    case "easeOut" -> VisualPlatform.getPlatform().getCurveManager().easeOut();
                    case "easeInOut" -> VisualPlatform.getPlatform().getCurveManager().easeInOut();
                    default -> VisualPlatform.getPlatform().getCurveManager().cubicBezier(.06, .88, .21, .95);
                };

                Point3d point1 = root.getPoint(startXYZ[0], -startXYZ[2], startXYZ[1]);
                Point3d point2 = root.getPoint(endXYZ[0], -endXYZ[2], endXYZ[1]);

                Vector3d vector = root.getVector(point1, point2);

                Vector3d direction = root.getVector(vector.y(), -vector.x(), 0);

                AnimationSet testSet = VisualPlatform.getPlatform().getAnimationManager()
                        .createAnimationSet()
                        .append(
                                VisualPlatform.getPlatform().getAnimationManager()
                                        .createAnimation()
                                        .start(point1)
                                        .end(point2)
                                        .duration(3)
                                        .framerate(999)
                                        .perspective(direction, false)
                                        .path(VisualPlatform.getPlatform().getGeometry().createLine(0, 0, 0), false)
                                        .curve(curve)
                                        .build()
                        )
                        .build();

                testSet.play(VisualBukkitPlugin.getInstance().getViewer((Player) sender));
                return false;
            }
        });

        Bukkit.getCommandMap().register("visual", new Command("test") {
            @Override
            public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String[] args) {
                if (args.length != 3 * 4)
                    return true;
                if (!(sender instanceof Player))
                    return true;

                int[] startXYZ = parse(args, 0);
                int[] endXYZ = parse(args, 3);
                int[] end2XYZ = parse(args, 6);
                int[] end3XYZ = parse(args, 9);

                Point3d point1 = root.getPoint(startXYZ[0], -startXYZ[2], startXYZ[1]);
                Point3d point2 = root.getPoint(endXYZ[0], -endXYZ[2], endXYZ[1]);
                Point3d point3 = root.getPoint(end2XYZ[0], -end2XYZ[2], end2XYZ[1]);
                Point3d point4 = root.getPoint(end3XYZ[0], -end3XYZ[2], end3XYZ[1]);

                Point3d looking = new Point3dImpl(point1.getCoordinate(), (point1.x() + point2.x()) / 2, (point1.y() + point2.y()) / 2, ((point1.z() + point2.z()) / 2));

                AnimationSet testSet = VisualPlatform.getPlatform().getAnimationManager()
                        .createAnimationSet()
                        .append(
                                VisualPlatform.getPlatform().getAnimationManager()
                                        .createAnimation()
                                        .duration(2)
                                        .framerate(999)
                                        .start(point1)
                                        .end(point2)
                                        .perspective(looking.add(0, 0, 10), false)
                                        .path(VisualPlatform.getPlatform().getGeometry().createLine(0, 0, 0), false)
                                        .curve(VisualPlatform.getPlatform().getCurveManager().linear())
                                        .build()
                        )
                        .append(
                                VisualPlatform.getPlatform().getAnimationManager()
                                        .createAnimation()
                                        .duration(3)
                                        .framerate(999)
                                        .start(point2)
                                        .end(point3)
                                        .perspective(looking.add(0, 0, 10), false)
                                        .path(VisualPlatform.getPlatform().getGeometry().createEllipse(new Point2dImpl(0, 0), 10, 5), false)
                                        .curve(VisualPlatform.getPlatform().getCurveManager().cubicBezier(.32, .17, .2, .97))
                                        .build()
                        )
                        .append(
                                VisualPlatform.getPlatform().getAnimationManager()
                                        .createAnimation()
                                        .duration(2)
                                        .framerate(999)
                                        .start(point3)
                                        .end(point4)
                                        .perspective(looking.add(0, 0, 10), false)
                                        .path(VisualPlatform.getPlatform().getGeometry().createEllipse(new Point2dImpl(0, 0), 10, 10), true)
                                        .curve(VisualPlatform.getPlatform().getCurveManager().easeInOut())
                                        .build()
                        )
                        .build();
                testSet.play(VisualBukkitPlugin.getInstance().getViewer((Player) sender));
                return true;
            }
        });

    }

}
