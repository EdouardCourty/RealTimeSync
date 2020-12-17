# RealTimeSync

### Introduction

This plugin synchronises the world's time with the real world.  
I wrote it for Spigot 1.16.4 and will surely update it to 1.17 when available.

### Config

The configuration file is pretty straight-forward:

```yaml
world: world # The name of the world you want to sync
update_rate: 80 # In ticks (20 per second), the amount of time the server will wait to update the time
force_daylightcycle_false: true # Forces the game to disable thhe vanilla's doDayLightCycle gamerule
debug: false # Enabled logging of not (spams the console, don't use in production)
```

