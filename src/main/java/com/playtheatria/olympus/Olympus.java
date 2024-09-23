package com.playtheatria.olympus;

import com.playtheatria.olympus.listeners.BlockBreakListener;
import com.playtheatria.olympus.listeners.PlayerJoinListener;
import com.playtheatria.olympus.utils.SourceRenewalTask;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;


public final class Olympus extends JavaPlugin {
    @Override
    public void onEnable() {
        String world_name = "olympus";
        if (Bukkit.getWorld(world_name) == null) {
            getServer().getPluginManager().disablePlugin(this);
            Objects.requireNonNull(getServer().getPluginManager().getPlugin(this.getName())).getLogger().severe("World: " + world_name +" did not exist");
        }
        World world = Bukkit.getWorld(world_name);
        int height = 151;

        new SourceRenewalTask(getLogger(), new Location(world, -2, height, 2)).runTaskTimer(this, 0, 10);
        new SourceRenewalTask(getLogger(), new Location(world, -2, height, -2)).runTaskTimer(this, 0, 10);
        new SourceRenewalTask(getLogger(), new Location(world, 2, height, -2)).runTaskTimer(this, 0, 10);
        new SourceRenewalTask(getLogger(), new Location(world, 2, height, 2)).runTaskTimer(this, 0, 10);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(height), this);
        Bukkit.getScheduler().runTaskTimer(this,
                () -> Bukkit.getOnlinePlayers().stream()
                        .filter(x -> !x.hasPermission("group.hero"))
                        .forEach(player -> {
                            player.setExhaustion(20);
                            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 1000, 10));
                            player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 1000, 10));
                        }), 0, 20 * 3
        );
    }
}
