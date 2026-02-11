package me.sparklee.UltraWarps.util;

import me.sparklee.UltraWarps.UltraWarps;
import me.sparklee.UltraWarps.warp.Warp;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TeleportUtil {

    private static final Map<UUID, BukkitTask> pendingTasks = new HashMap<>();
    private static final Map<UUID, Integer> countdowns = new HashMap<>();

    private static UltraWarps plugin() {
        return UltraWarps.getInstance();
    }

    public static void startTeleport(Player player, Warp warp, int seconds) {

        if (pendingTasks.containsKey(player.getUniqueId())) {
            pendingTasks.get(player.getUniqueId()).cancel();
        }

        countdowns.put(player.getUniqueId(), seconds);

        BukkitTask task = Bukkit.getScheduler().runTaskTimer(plugin(), () -> {

            int timeLeft = countdowns.get(player.getUniqueId());

            if (timeLeft <= 0) {
                finishTeleport(player, warp);
                return;
            }

            player.showTitle(Title.title(
                    MessageUtil.color(
                            plugin().getConfig()
                                    .getString("messages.teleport.timer-title")
                                    .replace("%seconds%", String.valueOf(timeLeft))
                    ),
                    MessageUtil.color(
                            plugin().getConfig()
                                    .getString("messages.teleport.timer-subtitle")
                    )
            ));

            countdowns.put(player.getUniqueId(), timeLeft - 1);

        }, 0L, 20L);

        pendingTasks.put(player.getUniqueId(), task);
    }

    public static void cancelTeleport(Player player) {

        UUID uuid = player.getUniqueId();

        if (!pendingTasks.containsKey(uuid)) return;

        pendingTasks.get(uuid).cancel();
        pendingTasks.remove(uuid);
        countdowns.remove(uuid);

        player.showTitle(Title.title(
                MessageUtil.color(
                        plugin().getConfig().getString("messages.teleport.cancelled-title")
                ),
                MessageUtil.color(
                        plugin().getConfig().getString("messages.teleport.cancelled-subtitle")
                )
        ));

        MessageUtil.send(
                player,
                plugin().getConfig().getString("messages.teleport.cancelled")
        );
    }

    private static void finishTeleport(Player player, Warp warp) {

        UUID uuid = player.getUniqueId();

        if (!pendingTasks.containsKey(uuid)) return;

        pendingTasks.get(uuid).cancel();
        pendingTasks.remove(uuid);
        countdowns.remove(uuid);

        player.teleportAsync(warp.getLocation()).thenRun(() -> {

            player.showTitle(Title.title(
                    MessageUtil.color(
                            plugin().getConfig()
                                    .getString("messages.teleport.success.title")
                                    .replace("%warp%", warp.getName())
                    ),
                    MessageUtil.color(
                            plugin().getConfig()
                                    .getString("messages.teleport.success.subtitle")
                                    .replace("%warp%", warp.getName())
                    )
            ));

            MessageUtil.sendHeaderBody(
                    player,
                    plugin().getConfig()
                            .getString("messages.teleport.success.header"),
                    plugin().getConfig()
                            .getString("messages.teleport.success.body")
                            .replace("%warp%", warp.getName())
            );
        });
    }

    public static boolean isTeleporting(Player player) {
        return pendingTasks.containsKey(player.getUniqueId());
    }
}
