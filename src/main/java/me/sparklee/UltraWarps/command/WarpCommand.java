package me.sparklee.UltraWarps.command;

import me.sparklee.UltraWarps.util.TeleportUtil;
import me.sparklee.UltraWarps.warp.Warp;
import me.sparklee.UltraWarps.warp.WarpManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class WarpCommand implements CommandExecutor, TabCompleter {

    private final WarpManager warpManager;

    public WarpCommand(WarpManager warpManager) {
        this.warpManager = warpManager;
    }

    @Override
    public boolean onCommand(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String label,
            @NotNull String[] args
    ) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("Usage: /warp <name>");
            return true;
        }

        Warp warp = warpManager.getWarp(args[0]);
        if (warp == null) {
            sender.sendMessage("Warp not found.");
            return true;
        }

        if (!warp.isPublic() && !player.hasPermission(warp.getPermission())) {
            sender.sendMessage("You don't have permission for this warp.");
            return true;
        }

        TeleportUtil.startTeleport(player, warp, 3);
        return true;
    }

    @Override
    public List<String> onTabComplete(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String alias,
            @NotNull String[] args
    ) {

        if (!(sender instanceof Player player)) return List.of();
        if (args.length != 1) return List.of();

        return warpManager.getWarps().stream()
                .filter(w -> w.isPublic() || player.hasPermission(w.getPermission()))
                .map(Warp::getName)
                .filter(name -> name.toLowerCase().startsWith(args[0].toLowerCase()))
                .collect(Collectors.toList());
    }
}
