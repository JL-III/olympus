package com.playtheatria.olympus;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Olympus extends JavaPlugin implements Listener {
    private final List<Location> sourceLocations = new ArrayList<>();

    @Override
    public void onEnable() {
        String world_name = "world";
        if (Bukkit.getWorld(world_name) == null) {
            getServer().getPluginManager().disablePlugin(this);
            Objects.requireNonNull(getServer().getPluginManager().getPlugin(this.getName())).getLogger().severe("World: " + world_name +" did not exist");
        }
        World world = Bukkit.getWorld(world_name);
        double height = 67;

        sourceLocations.add(new Location(world, -2, height, -2));
        sourceLocations.add(new Location(world, -2, height, 2));
        sourceLocations.add(new Location(world, 2, height, -2));
        sourceLocations.add(new Location(world, 2, height, 2));

        // at start up, make sure blocks are set to a new material, they should never be air
        for (Location location : sourceLocations) {
            new SourceRenewal(location).runTaskTimer(this, 0, 10);
        }
        getServer().getPluginManager().registerEvents(this, this);
    }
}
