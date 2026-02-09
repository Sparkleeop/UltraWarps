package me.sparklee.UltraWarps.warp;

import me.sparklee.UltraWarps.UltraWarps;
import org.bukkit.Bukkit;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WarpManager {

    private final UltraWarps plugin;
    private final Map<String, Warp> warps = new ConcurrentHashMap<>();

    public WarpManager(UltraWarps plugin) {
        this.plugin = plugin;
    }

    public void loadWarpsAsync() {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            warps.clear();
            WarpStorage.load(plugin, warps);
            plugin.getLogger().info("Loaded " + warps.size() + " warps");
        });
    }

    public void saveWarpsAsync() {
        Bukkit.getScheduler().runTaskAsynchronously(
                plugin,
                () -> WarpStorage.save(plugin, warps)
        );
    }

    public Warp getWarp(String name) {
        return warps.get(name.toLowerCase());
    }

    public void setWarp(Warp warp) {
        warps.put(warp.getName().toLowerCase(), warp);
    }

    public void deleteWarp(String name) {
        warps.remove(name.toLowerCase());
    }

    public Collection<Warp> getWarps() {
        return warps.values();
    }
}
