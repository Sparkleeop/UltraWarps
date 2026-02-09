package me.sparklee.UltraWarps.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UltraWarpsCommand implements CommandExecutor {

    private final ReloadCommand reload;
    private final HelpCommand help;
    private final InfoCommand info;
    private final SetWarpCommand set;
    private final DeleteWarpCommand delete;

    public UltraWarpsCommand(
            ReloadCommand reload,
            HelpCommand help,
            InfoCommand info,
            SetWarpCommand set,
            DeleteWarpCommand delete
    ) {
        this.reload = reload;
        this.help = help;
        this.info = info;
        this.set = set;
        this.delete = delete;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            help.execute(sender);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "reload" -> reload.execute(sender);
            case "help" -> help.execute(sender);
            case "info" -> info.execute(sender);
            case "set" -> set.execute(sender, args);
            case "delete", "del", "remove" -> delete.execute(sender, args);
            default -> help.execute(sender);
        }

        return true;
    }
}
