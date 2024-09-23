package com.playtheatria.olympus.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class SourceRenewalTask extends BukkitRunnable {
    private final Logger logger;
    private final Location location;

    public SourceRenewalTask(Logger logger, Location location) {
        this.logger = logger;
        this.location = location;
    }

    private final List<WeightedMaterial> buildingBlocks = new ArrayList<>() {{
        add(new WeightedMaterial(Material.DIRT, 500));
        add(new WeightedMaterial(Material.STONE, 75));
        add(new WeightedMaterial(Material.OAK_LOG, 100));
        add(new WeightedMaterial(Material.IRON_ORE, 20));
        add(new WeightedMaterial(Material.GOLD_ORE, 10));
    }};

    @Override
    public void run() {
        if (location.getBlock().getType() == Material.AIR) {
            setBlock(location);
        }
    }

    private void setBlock(Location location) {
        Material material = getRandomMaterial(buildingBlocks).material();
        location.getBlock().setType(material);
        logger.info("Block Hardness for " + material.name() + ": " + material.getHardness());
    }

    private void setBlock(Location location, Material material) {
        location.getBlock().setType(material);
    }

    public WeightedMaterial getRandomMaterial(List<WeightedMaterial> materials) {
        int totalWeight = 0;
        for (WeightedMaterial material : materials) {
            totalWeight += material.weight();
        }

        int randomNumber = new Random().nextInt(totalWeight);
        int currentSum = 0;

        for (WeightedMaterial material : materials) {
            currentSum += material.weight();
            if (randomNumber < currentSum) {
                return material;
            }
        }
        return buildingBlocks.getFirst();
    }
}
