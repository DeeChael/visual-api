package net.deechael.visual.bukkit;

import com.destroystokyo.paper.event.player.PlayerStopSpectatingEntityEvent;
import net.deechael.visual.api.Viewer;
import net.deechael.visual.api.geometry.position.Point3d;
import net.deechael.visual.api.geometry.position.Vector3d;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

class BukkitViewer implements Viewer {

    private final Player player;

    private Listener listener;

    private Location previousLocation = null;
    private GameMode previousGameMode = null;

    private ArmorStand armorStand;

    private final List<BukkitTask> runningTasks = new ArrayList<>();

    public BukkitViewer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public Point3d getPoint() {
        Location location = this.player.getLocation();
        return PointConvertor.toVisual(location.add(0.0, this.player.getEyeHeight(), 0.0));
    }

    @Override
    public boolean isLocked() {
        return this.listener != null;
    }

    @Override
    public boolean isReleased() {
        return this.listener == null;
    }

    @Override
    public void lock() {
        if (this.listener != null)
            throw new RuntimeException("This viewer has been locked!");

        this.previousLocation = player.getLocation();

        armorStand = (ArmorStand) player.getWorld().spawnEntity(this.previousLocation, EntityType.ARMOR_STAND);
        armorStand.setInvisible(true);
        armorStand.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999, 1, false, false, false));
        armorStand.setGravity(false);

        this.previousGameMode = player.getGameMode();

        player.setGameMode(GameMode.SPECTATOR);
        player.setSpectatorTarget(armorStand);

        listener = new Listener() {
            @EventHandler
            public void move(PlayerMoveEvent event) {
                event.setCancelled(true);
            }

            @EventHandler
            public void spectate(PlayerStopSpectatingEntityEvent event) {
                event.setCancelled(true);
            }
        };

        Bukkit.getPluginManager().registerEvents(listener, VisualBukkitPlugin.getInstance());
    }

    @Override
    public void ensureAsync(Runnable runnable) {
        if (!VisualBukkitPlugin.getInstance().isEnabled())
            return;
        this.runningTasks.add(new BukkitRunnable() {
            @Override
            public void run() {
                runnable.run();
            }
        }.runTaskAsynchronously(VisualBukkitPlugin.getInstance()));
    }

    @Override
    public void ensureSync(Runnable runnable) {
        if (!VisualBukkitPlugin.getInstance().isEnabled())
            return;
        this.runningTasks.add(new BukkitRunnable() {
            @Override
            public void run() {
                runnable.run();
            }
        }.runTask(VisualBukkitPlugin.getInstance()));
    }

    @Override
    public void release() {
        if (this.listener == null)
            throw new RuntimeException("This viewer has been released!");

        for (BukkitTask task : this.runningTasks) {
            if (!task.isCancelled())
                task.cancel();
        }

        HandlerList.unregisterAll(this.listener);
        this.listener = null;

        this.armorStand.remove();
        this.player.teleport(this.previousLocation);
        this.player.setGameMode(this.previousGameMode);
    }

    @Override
    public void updateFrame(Point3d point, Vector3d direction, boolean reverse) {
        if (this.listener == null)
            throw new RuntimeException("This viewer is not locked!");

        Location location = PointConvertor.toBukkit(this.player.getWorld(), point).subtract(0, this.player.getEyeHeight(), 0);

        Vector vector = new Vector(direction.x(), direction.z(), -direction.y());

        if (reverse)
            vector = vector.multiply(-1);

        location = location.setDirection(vector);

        this.armorStand.setRotation(location.getYaw(), location.getPitch());
        this.player.setRotation(location.getYaw(), location.getPitch());
        this.armorStand.teleport(location);
    }

}
