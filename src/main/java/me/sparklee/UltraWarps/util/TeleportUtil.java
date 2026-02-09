package me.sparklee.UltraWarps.util;

import me.sparklee.UltraWarps.UltraWarps;
import me.sparklee.UltraWarps.warp.Warp;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TeleportUtil {

    private static final Map<UUID, Location> pending = new ConcurrentHashMap<>();

    private static UltraWarps plugin() {
        return UltraWarps.getInstance();
    }

    public static void startTeleport(Player player, Warp warp, int seconds) {

        pending.put(player.getUniqueId(), player.getLocation().clone());

        for (int i = seconds; i > 0; i--) {
            int time = i;

            Bukkit.getScheduler().runTaskLater(
                    plugin(),
                    () -> {
                        if (!pending.containsKey(player.getUniqueId())) return;

                        player.showTitle(Title.title(
                                MessageUtil.color(
                                        plugin().getConfig()
                                                .getString("messages.teleport.timer-title")
                                                .replace("%seconds%", String.valueOf(time))
                                ),
                                MessageUtil.color(
                                        plugin().getConfig()
                                                .getString("messages.teleport.timer-subtitle")
                                )
                        ));
                    },
                    (seconds - i) * 20L
            );
        }

        Bukkit.getScheduler().runTaskLater(
                plugin(),
                () -> finishTeleport(player, warp),
                seconds * 20L
        );
    }

    private static void finishTeleport(Player player, Warp warp) {

        Location start = pending.get(player.getUniqueId());
        if (start == null) return;

        Location now = player.getLocation();
        if (start.distanceSquared(now) > 0.01) {
            pending.remove(player.getUniqueId());
            MessageUtil.send(
                    player,
                    plugin().getConfig().getString("messages.teleport.cancelled")
            );
            return;
        }

        pending.remove(player.getUniqueId());

        player.teleportAsync(warp.getLocation()).thenRun(() -> {
            MessageUtil.sendHeaderBody(
                    player,
                    plugin().getConfig().getString("messages.teleport.success.header"),
                    plugin().getConfig().getString("messages.teleport.success.body")
                            .replace("%warp%", warp.getName())
            );
        });
    }
}
