package dev.idw0309.joinleave.configs;

import dev.idw0309.joinleave.JoinLeave;
import dev.idw0309.joinleave.message.Message;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Fireworks {

    public static void check() {

        Message.sendToConsole("Checking if fireworks config exist...");

        if(!new File(JoinLeave.path, "fireworks.yml").exists()) {
            Message.sendToConsole("Creating a new fireworks config...");

            File f = new File(JoinLeave.path, "fireworks.yml");
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);

            cfg.options().header("JOIN-LEAVE V2.4 Sound Config || Copyright (C) 2023 IdW0309");
            cfg.set("Fireworks.Color", "LIME");
            cfg.set("Fireworks.Flicker", true);
            cfg.set("Fireworks.Power", 2);

            try {
                cfg.save(f);
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        Message.sendToConsole(ChatColor.GREEN + "Fireworks config exist!");

    }
}
