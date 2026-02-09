package me.sparklee.UltraWarps.command;

import me.sparklee.UltraWarps.UltraWarps;
import me.sparklee.UltraWarps.util.MessageUtil;
import org.bukkit.command.CommandSender;

public class HelpCommand {

    private final UltraWarps plugin;

    public HelpCommand(UltraWarps plugin) {
        this.plugin = plugin;
    }

    public void execute(CommandSender sender) {

        MessageUtil.send(sender, plugin.getConfig().getString("messages.help.header"));

        send(sender, "/warp <name>", "Teleport to a server warp");
        send(sender, "/listwarps", "List all available warps");
        send(sender, "/uw set <name>", "Create a server warp");
        send(sender, "/uw delete <name>", "Delete a server warp");
        send(sender, "/uw reload", "Reload configuration");
        send(sender, "/uw info", "View plugin information");
    }

    private void send(CommandSender sender, String cmd, String desc) {
        MessageUtil.send(
                sender,
                plugin.getConfig()
                        .getString("messages.help.format")
                        .replace("%command%", cmd)
                        .replace("%description%", desc)
        );
    }
}
