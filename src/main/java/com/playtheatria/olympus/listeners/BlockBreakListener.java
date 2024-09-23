package com.playtheatria.olympus.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.HashSet;
import java.util.Set;

public class BlockBreakListener implements Listener {
    private final Set<Location> protectedSpawn = new HashSet<>();

    public BlockBreakListener(int height) {
        for (double x = -4; x <= 4; x++) {
            for (double y = 0; y < height - 1; y++) {
                for (double z = -4; z <= 4; z++) {
                    protectedSpawn.add(new Location(Bukkit.getWorld("olympus"), x, y, z));
                }
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (protectedSpawn.contains(event.getBlock().getLocation())) {
            event.setCancelled(true);
        }
    }
}
