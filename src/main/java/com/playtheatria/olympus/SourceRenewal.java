package com.playtheatria.olympus;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class SourceRenewal extends BukkitRunnable {

    private final Location location;

    private final List<Material> punchList = List.of(
            Material.DIRT,
            Material.GRASS_BLOCK,
            Material.OAK_SAPLING,
            Material.SAND,
            Material.GRAVEL,
            Material.OAK_LOG
    );

    private final List<Material> woodList = List.of(
            Material.STONE,
            Material.COBBLESTONE,
            Material.COAL_ORE
    );

    private final List<Material> ironList = List.of(
            Material.IRON_ORE,
            Material.GOLD_ORE
    );

    private final List<Material> woolList = List.of(
            Material.RED_WOOL,
            Material.ORANGE_WOOL,
            Material.YELLOW_WOOL,
            Material.GREEN_WOOL,
            Material.LIME_WOOL,
            Material.CYAN_WOOL,
            Material.LIGHT_BLUE_WOOL,
            Material.MAGENTA_WOOL,
            Material.PURPLE_WOOL
    );

    private final List<Material> miscList = List.of(
            Material.SPONGE
    );

    public SourceRenewal(Location location) {
        this.location = location;
    }

    @Override
    public void run() {
        if (location.getBlock().getType() == Material.AIR) {
            setBlock(location);
        }
    }

    private void setBlock(Location location) {
        location.getBlock().setType(getMaterial());
    }

    private Material getMaterial() {

        List<Material> tierOne = new ArrayList<>() {{
            addAll(miscList);

        }};
        List<Material> tierTwo = new ArrayList<>() {{
            addAll(ironList);
        }};

        List<Material> tierThree = new ArrayList<>() {{
            addAll(woolList);
        }};

        List<Material> tierFour = new ArrayList<>() {{
            addAll(woodList);
        }};

        List<Material> tierFive = new ArrayList<>() {{
            addAll(punchList);
        }};

        int tierNumber = (int) (Math.random() * 100);

        if (tierNumber <= 50) {
            int randomNumber = (int) (Math.random() * tierFive.size());
            return tierFive.get(randomNumber);
        }
        if (tierNumber >= 70 && tierNumber < 80) {
            int randomNumber = (int) (Math.random() * tierFour.size());
            return tierFour.get(randomNumber);
        }
        if (tierNumber >= 80 && tierNumber < 85) {
            int randomNumber = (int) (Math.random() * tierThree.size());
            return tierThree.get(randomNumber);
        }
        if (tierNumber >= 85 && tierNumber < 98) {
            int randomNumber = (int) (Math.random() * tierTwo.size());
            return tierTwo.get(randomNumber);
        }
        if (tierNumber >= 98) {
            int randomNumber = (int) (Math.random() * tierOne.size());
            return tierOne.get(randomNumber);
        }
        else {
            return tierFive.getFirst();
        }
    }
}
