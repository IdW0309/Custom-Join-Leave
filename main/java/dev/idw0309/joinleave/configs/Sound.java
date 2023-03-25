package dev.idw0309.joinleave.configs;

import dev.idw0309.joinleave.JoinLeave;
import dev.idw0309.joinleave.message.Message;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Sound {

    public static void check() {

        Message.sendToConsole("Checking if sound config exist...");

        if(!new File(JoinLeave.path, "sound.yml").exists()) {
            Message.sendToConsole("Creating a new sound config...");

            File f = new File(JoinLeave.path, "sound.yml");
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);

            cfg.options().header("JOIN-LEAVE V2.4 Sound Config || Copyright (C) 2023 IdW0309");
            cfg.set("Sound.Sound", "BLOCK_NOTE_BLOCK_BELL");
            cfg.set("Sound.Volume", 10);
            cfg.set("Sound.Pitch", 29);

            try {
                cfg.save(f);
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        Message.sendToConsole(ChatColor.GREEN + "Sound config exist!");

    }
}
