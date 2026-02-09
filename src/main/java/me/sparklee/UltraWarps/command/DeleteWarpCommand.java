package me.sparklee.UltraWarps.command;

import me.sparklee.UltraWarps.UltraWarps;
import me.sparklee.UltraWarps.util.MessageUtil;
import me.sparklee.UltraWarps.warp.WarpManager;
import org.bukkit.command.CommandSender;

public class DeleteWarpCommand {

    private final UltraWarps plugin;
    private final WarpManager warpManager;

    public DeleteWarpCommand(UltraWarps plugin, WarpManager warpManager) {
        this.plugin = plugin;
        this.warpManager = warpManager;
    }

    public void execute(CommandSender sender, String[] args) {

        if (args.length < 2) {
            MessageUtil.sendHeaderBody(
                    sender,
                    plugin.getConfig().getString("messages.usage.invalid.header"),
                    "&7Usage: &a/uw delete <name>"
            );
            return;
        }

        String name = args[1].toLowerCase();

        if (warpManager.getWarp(name) == null) {
            MessageUtil.send(sender, "&cThat warp does not exist.");
            return;
        }

        warpManager.deleteWarp(name);
        warpManager.saveWarpsAsync();

        MessageUtil.sendHeaderBody(
                sender,
                plugin.getConfig().getString("messages.warp-deleted.header"),
                plugin.getConfig()
                        .getString("messages.warp-deleted.body")
                        .replace("%warp%", name)
        );
    }
}
