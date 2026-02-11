package me.sparklee.UltraWarps;

import me.sparklee.UltraWarps.command.*;
import me.sparklee.UltraWarps.listener.TeleportListener;
import me.sparklee.UltraWarps.util.ConfigManager;
import me.sparklee.UltraWarps.warp.WarpManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Objects;

public final class UltraWarps extends JavaPlugin {

    private static UltraWarps instance;
    private WarpManager warpManager;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        ConfigManager.check(this);

        warpManager = new WarpManager(this);
        warpManager.loadWarpsAsync();

        ReloadCommand reload = new ReloadCommand(this, warpManager);
        HelpCommand help = new HelpCommand(this);
        InfoCommand info = new InfoCommand(this, warpManager);
        SetWarpCommand set = new SetWarpCommand(this, warpManager);
        DeleteWarpCommand delete = new DeleteWarpCommand(this, warpManager);

        Objects.requireNonNull(getCommand("ultrawarps"))
                .setExecutor(new UltraWarpsCommand(reload, help, info, set, delete));

        WarpCommand warpCommand = new WarpCommand(warpManager);
        Objects.requireNonNull(getCommand("warp")).setExecutor(warpCommand);
        Objects.requireNonNull(getCommand("warp")).setTabCompleter(warpCommand);

        Objects.requireNonNull(getCommand("listwarps"))
                .setExecutor(new ListWarpsCommand(warpManager));

        getServer().getPluginManager().registerEvents(
                new TeleportListener(),
                this
        );


        getLogger().info("UltraWarps enabled");
    }


    @Override
    public void onDisable() {
        if (warpManager != null) {
            warpManager.saveWarpsAsync();
        }
    }

    public static UltraWarps getInstance() {
        return instance;
    }
}
