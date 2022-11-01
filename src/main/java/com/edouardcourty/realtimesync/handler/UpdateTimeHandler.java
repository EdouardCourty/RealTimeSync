package com.edouardcourty.realtimesync.handler;

import com.edouardcourty.realtimesync.entity.WorldConfig;
import com.edouardcourty.realtimesync.repository.TimeRepository;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateTimeHandler {

    public World world;
    public JavaPlugin plugin;

    public WorldConfig config;
    public GameRule<Boolean> doDayLightCycleGamerule = GameRule.DO_DAYLIGHT_CYCLE;

    public UpdateTimeHandler(JavaPlugin plugin, WorldConfig config) {
        this.plugin = plugin;
        this.world = this.plugin.getServer().getWorld(config.getWorldName());
        this.config = config;
    }

    public void startLoop()
    {
        if(this.world == null) {
            this.plugin.getLogger().severe("World " + this.config.getWorldName() + " not found");
            return;
        }
        Bukkit.getLogger().info(String.format("Starting loop for world %s. Updating every %s ticks.", world.getName(), this.config.getUpdateRate()));
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this::handle, 0, this.config.getUpdateRate());
    }

    public void handle()
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

        if (this.config.isForceDaylightcycleFalse() && hasDayLightCycleActivated) {
            world.setGameRule(doDayLightCycleGamerule, false);
        }

        world.getGameRuleValue(doDayLightCycleGamerule);
        world.setTime(computedTime);
    }
}
