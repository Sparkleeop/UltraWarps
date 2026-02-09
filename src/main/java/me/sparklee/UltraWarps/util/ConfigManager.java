package me.sparklee.UltraWarps.util;

import me.sparklee.UltraWarps.UltraWarps;

public class ConfigManager {

    private static final int CONFIG_VERSION = 1;

    public static void check(UltraWarps plugin) {
        int version = plugin.getConfig().getInt("config-version", 0);

        if (version != CONFIG_VERSION) {
            plugin.getLogger().warning("Outdated config detected! Regenerating...");
            plugin.saveResource("config.yml", true);
        }
    }
}
