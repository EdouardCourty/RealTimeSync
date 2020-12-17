package com.edouardcourty.realtimesync.handler;

import org.bukkit.plugin.java.JavaPlugin;

public class ConfigFileHandler {

    public static JavaPlugin plugin;

    public static void init(JavaPlugin myPlugin)
    {
        plugin = myPlugin;
    }

    public static void handle()
    {
        // Saves the default config.yml if does not exists or incorrect.
        plugin.saveDefaultConfig();
    }
}
