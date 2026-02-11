package me.sparklee.UltraWarps.listener;

import me.sparklee.UltraWarps.util.TeleportUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class TeleportListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {

        if (!TeleportUtil.isTeleporting(event.getPlayer())) return;

        if (event.getFrom().getX() == event.getTo().getX() &&
                event.getFrom().getY() == event.getTo().getY() &&
                event.getFrom().getZ() == event.getTo().getZ()) return;

        TeleportUtil.cancelTeleport(event.getPlayer());
    }
}
