package me.sparklee.UltraWarps.command;

import me.sparklee.UltraWarps.UltraWarps;
import me.sparklee.UltraWarps.util.MessageUtil;
import me.sparklee.UltraWarps.warp.Warp;
import me.sparklee.UltraWarps.warp.WarpManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarpCommand {

    private final UltraWarps plugin;
    private final WarpManager warpManager;

    public SetWarpCommand(UltraWarps plugin, WarpManager warpManager) {
        this.plugin = plugin;
        this.warpManager = warpManager;
    }

    public void execute(CommandSender sender, String[] args) {

        if (!(sender instanceof Player player)) {
            MessageUtil.send(sender, "&cOnly players can use this command.");
            return;
        }

        if (args.length < 2) {
            MessageUtil.sendHeaderBody(
                    sender,
                    plugin.getConfig().getString("messages.usage.invalid.header"),
                    "&7Usage: &a/uw set <name>"
            );
            return;
        }

        String name = args[1].toLowerCase();

        if (warpManager.getWarp(name) != null) {
            MessageUtil.send(sender, "&cThat warp already exists.");
            return;
        }

        Warp warp = new Warp(name, player.getLocation(), "");
        warpManager.setWarp(warp);
        warpManager.saveWarpsAsync();

        MessageUtil.sendHeaderBody(
                sender,
                plugin.getConfig().getString("messages.warp-created.header"),
                plugin.getConfig()
                        .getString("messages.warp-created.body")
                        .replace("%warp%", name)
        );
    }
}
