package com.playtheatria.olympus.listeners;

import com.playtheatria.olympus.Olympus;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

public class PlayerJoinListener implements Listener {
    private final Olympus plugin;

    public PlayerJoinListener(Olympus plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        HealthMessage healthMessage = getHealthForOlympus(player);
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            Objects.requireNonNull(event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(healthMessage.health());
            player.sendMessage(MiniMessage.miniMessage().deserialize(healthMessage.message()));}, 20 * 3
        );
    }

    public HealthMessage getHealthForOlympus(Player player) {
        if (player.hasPermission("group.god")) {
            return new HealthMessage(20, "<italic><yellow>You are of divine status, the realm welcomes you.");
        }
        if (player.hasPermission("group.demigod")) {
            return new HealthMessage(18, "<italic><yellow>The strength of the gods flows through your veins, but you have yet to reach their full potential.");
        }
        if (player.hasPermission("group.legend")) {
            return new HealthMessage(18, "<italic><yellow>Your name echoes through the halls of history, but legends can still fall.");
        }
        if (player.hasPermission("group.paragon")) {
            return new HealthMessage(14, "<italic><yellow>You are the pinnacle of mortal achievement, though mortality binds you.");
        }
        if (player.hasPermission("group.champion")) {
            return new HealthMessage(12, "<italic><yellow>Victorious in many battles, but Olympus is not yet your home.");
        }
        if (player.hasPermission("group.hero")) {
            return new HealthMessage(10, "<italic><yellow>Your courage is undeniable, but courage alone will not save you here.");
        }
        return new HealthMessage(10, "<italic><red>You do not belong here, you are far too weak to walk among gods.");
    }

    public record HealthMessage(int health, String message) {}
}
