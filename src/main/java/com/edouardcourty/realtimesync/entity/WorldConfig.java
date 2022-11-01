package com.edouardcourty.realtimesync.entity;

public class WorldConfig {

    protected String worldName;
    protected int updateRate;
    protected boolean forceDaylightcycleFalse;

    public WorldConfig(String worldName, int updateRate, boolean forceDaylightcycleFalse) {
        this.worldName = worldName;
        this.updateRate = updateRate;
        this.forceDaylightcycleFalse = forceDaylightcycleFalse;
    }

    public String getWorldName() {
        return worldName;
    }

    public int getUpdateRate() {
        return updateRate;
    }

    public boolean isForceDaylightcycleFalse() {
        return forceDaylightcycleFalse;
    }
}
