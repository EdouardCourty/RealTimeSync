package com.edouardcourty.realtimesync.handler;

import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.ZoneId;
import java.util.Set;

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

        Configuration config = plugin.getConfig();

        String timezone = config.getString("timezone");

        try {
            validateTimezone(timezone);
            plugin.getLogger().info("RealTimeSync configured with timezone " + timezone);
        } catch (Exception exception) {
            timezone = "Europe/Paris";
        }

        plugin.getConfig().set("timezone", timezone);
    }

    public static void validateTimezone(String timezone) throws Exception {
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();

        // Check if the parsed time zone is valid
        if (!availableZoneIds.contains(timezone)) {
            plugin.getLogger().warning("Invalid time zone: " + timezone + ".");
            plugin.getLogger().warning("Using default Europe/Paris timezone, please check the config and make sure the timezone you entered is correct.");
            throw new Exception("Invalid timezone");
        }
    }
}
