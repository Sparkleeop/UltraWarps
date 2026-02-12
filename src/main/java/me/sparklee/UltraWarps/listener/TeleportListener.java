package me.sparklee.UltraWarps.listener;

import me.sparklee.UltraWarps.util.TeleportUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class TeleportListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {

        if (!TeleportUtil.isTeleporting(event.getPlayer())) return;

        if (event.getTo() == null) return;

        // Only cancel if actual block position changed
        if (event.getFrom().getBlockX() == event.getTo().getBlockX()
                && event.getFrom().getBlockY() == event.getTo().getBlockY()
                && event.getFrom().getBlockZ() == event.getTo().getBlockZ()) {
            return; // Only rotation changed, ignore
        }

        TeleportUtil.cancelTeleport(event.getPlayer());
    }
}
