package com.edouardcourty.realtimesync.handler;

import com.edouardcourty.realtimesync.converter.TimeConverter;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class UpdateTimeHandler {

    public static World world;
    public static JavaPlugin plugin;
    public static int updateRate;
    public static GameRule<Boolean> doDayLightCycleGamerule = GameRule.DO_DAYLIGHT_CYCLE;

    public static ZoneId timezone;

    static public void init(JavaPlugin myPlugin)
    {
        plugin = myPlugin;

        String timezoneString = myPlugin.getConfig().getString("timezone");
        timezone = ZoneId.of(timezoneString);

        world = plugin.getServer().getWorld(Objects.requireNonNull(plugin.getConfig().getString("world")));
        updateRate = plugin.getConfig().getInt("update_rate");
    }

    static public void startLoop()
    {
        Bukkit.getLogger().info(String.format("Starting loop for world %s. Updating every %s ticks.", world.getName(), updateRate));
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, UpdateTimeHandler::handle, 0, updateRate);
    }

    static public void handle()
    {
        DateTimeFormatter formatHours = DateTimeFormatter.ofPattern("HH");
        DateTimeFormatter formatMinutes = DateTimeFormatter.ofPattern("mm");

        ZonedDateTime date = ZonedDateTime.now(timezone);

        final long computedTime = TimeConverter.convertTimeToMinecraftTicks(
                Integer.parseInt(date.format(formatHours)),
                Integer.parseInt(date.format(formatMinutes))
        );

        if (plugin.getConfig().getBoolean("debug")) {
            plugin.getLogger().info(String.format("Minecraft time set to: %s", computedTime));
        }

        boolean hasDayLightCycleActivated = world.getGameRuleValue(doDayLightCycleGamerule);

        if (plugin.getConfig().getBoolean("force_daylightcycle_false") && hasDayLightCycleActivated) {
            world.setGameRule(doDayLightCycleGamerule, false);
        }

        world.getGameRuleValue(doDayLightCycleGamerule);
        world.setTime(computedTime);
    }
}
