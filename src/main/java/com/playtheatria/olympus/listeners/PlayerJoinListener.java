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
        Objects.requireNonNull(event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(healthMessage.getHealth());
        Bukkit.getScheduler().runTask(plugin, () -> player.sendMessage(MiniMessage.miniMessage().deserialize(healthMessage.getMessage())));
    }

    public HealthMessage getHealthForOlympus(Player player) {
        if (player.hasPermission("group.god")) {
            return new HealthMessage(20, "<italics><yellow>You are of divine status, the realm welcomes you.");
        }
        if (player.hasPermission("group.demigod")) {
            return new HealthMessage(16, "<italics><yellow>The strength of the gods flows through your veins, but you have yet to reach their full potential.");
        }
        if (player.hasPermission("group.legend")) {
            return new HealthMessage(12, "<italics><yellow>Your name echoes through the halls of history, but legends can still fall.");
        }
        if (player.hasPermission("group.paragon")) {
            return new HealthMessage(10, "<italics><yellow>You are the pinnacle of mortal achievement, though mortality binds you.");
        }
        if (player.hasPermission("group.champion")) {
            return new HealthMessage(9, "<italics><yellow>Victorious in many battles, but Olympus is not yet your home.");
        }
        if (player.hasPermission("group.hero")) {
            return new HealthMessage(8, "<italics><yellow>Your courage is undeniable, but courage alone will not save you here.");
        }
        if (player.hasPermission("group.archon")) {
            return new HealthMessage(7, "<italics><yellow>A leader among mortals, yet Olympus remains out of your reach.");
        }
        if (player.hasPermission("group.eques")) {
            return new HealthMessage(6, "<italics><yellow>You ride with honor, but honor won't protect you in these sacred lands.");
        }
        if (player.hasPermission("group.patrician")) {
            return new HealthMessage(5, "<italics><yellow>Born into privilege, yet even privilege cannot shield you from divine judgment.");
        }
        if (player.hasPermission("group.gladiator")) {
            return new HealthMessage(4, "<italics><yellow>A fighter in the arena of life, but here, you face forces far greater than you.");
        }
        if (player.hasPermission("group.novice")) {
            return new HealthMessage(3, "<italics><yellow>You are still learning, the realm sees your inexperience.");
        }
        if (player.hasPermission("group.initiate")) {
            return new HealthMessage(2, "<italics><yellow>You have barely begun your journey; this realm will test your worth.");
        }
        if (player.hasPermission("group.default")) {
            return new HealthMessage(1, "<italics><red>You do not belong here, you are far too weak to walk among gods.");
        }
        return new HealthMessage(1, "<italics><red>You do not belong here, you are far too weak to walk among gods.");
    }


    public class HealthMessage {
        private int health;
        private String message;

        public HealthMessage(int health, String message) {
            this.health = health;
            this.message = message;
        }

        public int getHealth() {
            return health;
        }

        public String getMessage() {
            return message;
        }
    }


}
