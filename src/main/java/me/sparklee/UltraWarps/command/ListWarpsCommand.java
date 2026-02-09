package me.sparklee.UltraWarps.command;

import me.sparklee.UltraWarps.warp.Warp;
import me.sparklee.UltraWarps.warp.WarpManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.stream.Collectors;

public class ListWarpsCommand implements CommandExecutor {

    private final WarpManager warpManager;

    public ListWarpsCommand(WarpManager warpManager) {
        this.warpManager = warpManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        String warps = warpManager.getWarps().stream()
                .filter(w -> w.isPublic() || player.hasPermission(w.getPermission()))
                .map(Warp::getName)
                .collect(Collectors.joining(", "));

        player.sendMessage("Available warps: " + warps);
        return true;
    }
}
