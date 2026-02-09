package me.sparklee.UltraWarps.warp;

import me.sparklee.UltraWarps.UltraWarps;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class WarpStorage {

    private static File file;
    private static FileConfiguration config;

    private static void init(UltraWarps plugin) {
        if (file != null) return;

        file = new File(plugin.getDataFolder(), "warps.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                plugin.getLogger().severe("Could not create warps.yml");
            }
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    public static void load(UltraWarps plugin, Map<String, Warp> warps) {
        init(plugin);
        warps.clear();

        for (String key : config.getKeys(false)) {

            String path = key + ".";

            String world = config.getString(path + "world");
            if (world == null) continue;

            Location loc = new Location(
                    Bukkit.getWorld(world),
                    config.getDouble(path + "x"),
                    config.getDouble(path + "y"),
                    config.getDouble(path + "z"),
                    (float) config.getDouble(path + "yaw"),
                    (float) config.getDouble(path + "pitch")
            );

            String permission = config.getString(path + "permission", "");

            warps.put(
                    key.toLowerCase(),
                    new Warp(key, loc, permission)
            );
        }
    }

    public static void save(UltraWarps plugin, Map<String, Warp> warps) {
        init(plugin);

        config.getKeys(false).forEach(key -> config.set(key, null));

        for (Warp warp : warps.values()) {

            String path = warp.getName() + ".";

            config.set(path + "world", warp.getLocation().getWorld().getName());
            config.set(path + "x", warp.getLocation().getX());
            config.set(path + "y", warp.getLocation().getY());
            config.set(path + "z", warp.getLocation().getZ());
            config.set(path + "yaw", warp.getLocation().getYaw());
            config.set(path + "pitch", warp.getLocation().getPitch());
            config.set(path + "permission", warp.getPermission());
        }

        try {
            config.save(file);
        } catch (IOException e) {
            plugin.getLogger().severe("Failed to save warps.yml");
        }
    }
}
