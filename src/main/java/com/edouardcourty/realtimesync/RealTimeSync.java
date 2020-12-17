package com.edouardcourty.realtimesync;

import com.edouardcourty.realtimesync.handler.ConfigFileHandler;
import com.edouardcourty.realtimesync.handler.UpdateTimeHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class RealTimeSync extends JavaPlugin {
    @Override
    public void onEnable()
    {
        getLogger().info("Plugin enabled !");

        ConfigFileHandler.init(this);
        ConfigFileHandler.handle();

        UpdateTimeHandler.init(this);
        UpdateTimeHandler.startLoop();
    }

    @Override
    public void onDisable()
    {
        getLogger().info("Plugin disabled ! Thanks for using it :)");
    }
}
