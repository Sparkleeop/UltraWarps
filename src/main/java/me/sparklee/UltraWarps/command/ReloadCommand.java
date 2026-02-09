package me.sparklee.UltraWarps.command;

import me.sparklee.UltraWarps.UltraWarps;
import me.sparklee.UltraWarps.util.MessageUtil;
import me.sparklee.UltraWarps.warp.WarpManager;
import org.bukkit.command.CommandSender;

public class ReloadCommand {

    private final UltraWarps plugin;
    private final WarpManager warpManager;

    public ReloadCommand(UltraWarps plugin, WarpManager warpManager) {
        this.plugin = plugin;
        this.warpManager = warpManager;
    }

    public void execute(CommandSender sender) {

        long start = System.currentTimeMillis();
        MessageUtil.send(sender, plugin.getConfig().getString("messages.reload.start"));

        plugin.reloadConfig();
        warpManager.loadWarpsAsync();

        long time = System.currentTimeMillis() - start;

        MessageUtil.send(
                sender,
                plugin.getConfig()
                        .getString("messages.reload.success")
                        .replace("%time%", String.valueOf(time))
        );
    }
}
