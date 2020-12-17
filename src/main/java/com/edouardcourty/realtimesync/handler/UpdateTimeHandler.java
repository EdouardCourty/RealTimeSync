package com.edouardcourty.realtimesync.handler;

import com.edouardcourty.realtimesync.repository.TimeRepository;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class UpdateTimeHandler {

    public static World world;
    public static JavaPlugin plugin;
    public static int updateRate;
    public static GameRule<Boolean> doDayLightCycleGamerule = GameRule.DO_DAYLIGHT_CYCLE;

    static public void init(JavaPlugin myPlugin)
    {
        plugin = myPlugin;
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
        SimpleDateFormat sdfHours = new SimpleDateFormat("HH");
        SimpleDateFormat sdfMinutes = new SimpleDateFormat("mm");

        final long computedTime = TimeRepository.convertTimeToMinecraftTicks(
                Integer.parseInt(sdfHours.format(new Date())),
                Integer.parseInt(sdfMinutes.format(new Date()))
        );

        if (plugin.getConfig().getBoolean("debug")) {
            Bukkit.getLogger().info(String.format("Minecraft time set to: %s", computedTime));
        }

        boolean hasDayLightCycleActivated = world.getGameRuleValue(doDayLightCycleGamerule);

        if (plugin.getConfig().getBoolean("force_daylightcycle_false") && hasDayLightCycleActivated) {
            world.setGameRule(doDayLightCycleGamerule, false);
        }

        world.getGameRuleValue(doDayLightCycleGamerule);
        world.setTime(computedTime);
    }
}
