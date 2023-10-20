package com.edouardcourty.realtimesync.converter;

public class TimeConverter {

    public static final int MINECRAFT_MAX_TIME_TICK = 24000;

    public static long convertTimeToMinecraftTicks(int hours, int minutes)
    {
        int currentHoursConverted = (hours * 1000) - 6000;
        int currentMinutesConverted = (minutes * 10);

        int calculated = currentHoursConverted + currentMinutesConverted;

        if (calculated < 0) {
            calculated = MINECRAFT_MAX_TIME_TICK + calculated;
        }

        return calculated;
    }
}
