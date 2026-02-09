package me.sparklee.UltraWarps.command;

import me.sparklee.UltraWarps.UltraWarps;
import me.sparklee.UltraWarps.util.MessageUtil;
import me.sparklee.UltraWarps.warp.WarpManager;
import org.bukkit.command.CommandSender;

public class InfoCommand {

    private final UltraWarps plugin;
    private final WarpManager warpManager;

    public InfoCommand(UltraWarps plugin, WarpManager warpManager) {
        this.plugin = plugin;
        this.warpManager = warpManager;
    }

    public void execute(CommandSender sender) {

        MessageUtil.send(sender, plugin.getConfig().getString("messages.info.header"));

        MessageUtil.send(sender,
                plugin.getConfig().getString("messages.info.version")
                        .replace("%version%", plugin.getDescription().getVersion())
        );

        MessageUtil.send(sender,
                plugin.getConfig().getString("messages.info.warps")
                        .replace("%warps%", String.valueOf(warpManager.getWarps().size()))
        );

        MessageUtil.send(sender,
                plugin.getConfig().getString("messages.info.storage")
                        .replace("%storage%", plugin.getConfig().getString("storage.type"))
        );
    }
}
