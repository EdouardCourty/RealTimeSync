package com.edouardcourty.realtimesync;

import com.edouardcourty.realtimesync.entity.WorldConfig;
import com.edouardcourty.realtimesync.handler.ConfigFileHandler;
import com.edouardcourty.realtimesync.handler.UpdateTimeHandler;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class RealTimeSync extends JavaPlugin {
    @Override
    public void onEnable()
    {
        getLogger().info("Plugin enabled !");

        ConfigFileHandler.init(this);
        ConfigFileHandler.handle();

        // Start handlers later, so all worlds had a chance to load first
        final RealTimeSync instance = this;
        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                ConfigurationSection worldSection = instance.getConfig().getConfigurationSection("worlds");
                assert worldSection != null;
                for(String worldName : worldSection.getKeys(false)) {
                    WorldConfig config = new WorldConfig(worldName, worldSection.getInt(worldName + ".update_rate"), worldSection.getBoolean(worldName + ".force_daylightcycle_false"));
                    (new UpdateTimeHandler(instance, config)).startLoop();
                }
            }
        };
        runnable.runTaskLater(this, 1L);
    }

    @Override
    public void onDisable()
    {
        getLogger().info("Plugin disabled ! Thanks for using it :)");
    }
}
