package dev.idw0309.joinleave.configs;

import dev.idw0309.joinleave.JoinLeave;
import dev.idw0309.joinleave.message.Message;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Title {

    public static void check() {

        Message.sendToConsole("Checking if title config exist...");

        if(!new File(JoinLeave.path, "title.yml").exists()) {
            Message.sendToConsole("Creating a new title config...");

            File f = new File(JoinLeave.path, "title.yml");
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);

            cfg.options().header("JOIN-LEAVE V2.4 Title Config || Copyright (C) 2023 IdW0309");
            cfg.set("Title.message", "&6&lWELCOME");
            cfg.set("Title.lowermessage", "&6&lLOWER TITLE");
            cfg.set("Title.time.FadeIn", "20");
            cfg.set("Title.time.StayIn", "100");
            cfg.set("Title.time.FadeOut", "20");
            try {
                cfg.save(f);
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        Message.sendToConsole(ChatColor.GREEN + "Title config exist!");

    }
}
