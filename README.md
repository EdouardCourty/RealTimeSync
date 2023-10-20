# RealTimeSync

### Introduction

This plugin synchronises the world's time with the real world.  
I wrote it for Spigot 1.16.4 first, but it is compatible with Minecraft 1.20.2.

### Config

The configuration file is pretty straight-forward:

```yaml
# World you want to change the time of
world: world

# Update rate (in game ticks, 20 ticks = 1 second)
update_rate: 80

# Forces the world to activate the doDayLightCycle gamerule that stops the time from changing by itself
# The default state of this gamerule in a world is true. Settings this to true will set it to false.
# Not enabling this can result in weird-looking sky at sunrise and sunset, since the sun will move before being updated.
force_daylightcycle_false: true

# The timezone to use for time replication
# The format must be TZ compliant: https://en.wikipedia.org/wiki/List_of_tz_database_time_zones
timezone: Europe/Paris

# Will spam the console. Makes the plugin log every time the in-game time is changed.
debug: false

```

### Contributing

If you want to contribute to this project, feel free to submit a PR on the repository.

&copy; Edouard Courty
