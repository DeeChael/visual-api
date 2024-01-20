package net.deechael.visual.bukkit;

import net.deechael.visual.animation.AnimationManagerImpl;
import net.deechael.visual.api.Viewer;
import net.deechael.visual.api.VisualPlatform;
import net.deechael.visual.api.animation.AnimationManager;
import net.deechael.visual.api.curve.CurveManager;
import net.deechael.visual.api.geometry.Geometry;
import net.deechael.visual.curve.CurveManagerImpl;
import net.deechael.visual.geometry.GeometryImpl;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class VisualBukkitPlugin extends JavaPlugin implements VisualPlatform, Listener {

    private final static AnimationManager ANIMATION_MANAGER = new AnimationManagerImpl();
    private final static Geometry GEOMETRY = new GeometryImpl();
    private final static CurveManager CURVE_MANAGER = new CurveManagerImpl();

    private final Map<UUID, BukkitViewer> viewers = new HashMap<>();

    @EventHandler
    public void playerQuit(PlayerQuitEvent event) {
        if (viewers.containsKey(event.getPlayer().getUniqueId())) {
            BukkitViewer viewer = viewers.remove(event.getPlayer().getUniqueId());
            if (!viewer.isReleased())
                viewer.release();
        }
    }

    public static VisualBukkitPlugin getInstance() {
        return JavaPlugin.getPlugin(VisualBukkitPlugin.class);
    }

    public Viewer getViewer(Player player) {
        if (this.viewers.containsKey(player.getUniqueId()))
            return this.viewers.get(player.getUniqueId());
        BukkitViewer newViewer = new BukkitViewer(player);
        this.viewers.put(player.getUniqueId(), newViewer);
        return newViewer;
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        for (BukkitViewer viewer : this.viewers.values()) {
            if (viewer.isReleased())
                continue;
            viewer.release();
        }
    }

    @Override
    public AnimationManager getAnimationManager() {
        return ANIMATION_MANAGER;
    }

    @Override
    public Geometry getGeometry() {
        return GEOMETRY;
    }

    @Override
    public CurveManager getCurveManager() {
        return CURVE_MANAGER;
    }

}
