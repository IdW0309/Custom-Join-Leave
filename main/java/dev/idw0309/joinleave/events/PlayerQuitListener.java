package dev.idw0309.joinleave.events;

import dev.idw0309.joinleave.JoinLeave;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void event(PlayerQuitEvent e) {
        File f = new File(JoinLeave.path, "config.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);

        final Player pl = e.getPlayer();

        e.setQuitMessage(null);

        if (cfg.getString("Disable-Join-LeaveMessage").equals("true")) return;

        if (cfg.getString("Hide-Players-With-Perms").equals("true")) {
            if (pl.hasPermission("joinleave.silentleave")) return;
        }

        String message = cfg.getString("Leave").replaceAll("%player%", pl.getName()).replaceAll("&", "§");
        cfg.getString("Leave").replaceAll("&", "§");


        Bukkit.broadcastMessage(message);
    }
}
