package dev.idw0309.joinleave.configs;

import dev.idw0309.joinleave.JoinLeave;
import dev.idw0309.joinleave.message.Message;
import org.bukkit.ChatColor;

import java.io.File;

public class Config {

    public static void check() {


        Message.sendToConsole("Checking if config exist...");

        if(!new File(JoinLeave.path, "config.yml").exists()) {
            Message.sendToConsole("Creating a new config...");
            JoinLeave.plugin.getConfig().options().copyDefaults(true);
            JoinLeave.plugin.getConfig().options().copyHeader(true);
            JoinLeave.plugin.getConfig().options().header("JOIN-LEAVE V2.4 Config || Copyright (C) 2023 IdW0309");
            JoinLeave.plugin.getConfig().addDefault("Join", "[&a+&r] &6The player &6&l%player% &6has joined the server!");
            JoinLeave.plugin.getConfig().addDefault("Leave", "[&c-&r] &6The player &6&l%player% &6has left the server!");

            JoinLeave.plugin.getConfig().addDefault("First-Join-Message", Boolean.TRUE);
            JoinLeave.plugin.getConfig().addDefault("First-Join", "[&a+&r] &6The player &6&l%player% &6has joined for the first time!");

            JoinLeave.plugin.getConfig().addDefault("Language", "EN");
            JoinLeave.plugin.getConfig().addDefault("CommandPrefix", "Join-Leave");
            JoinLeave.plugin.getConfig().addDefault("Title-Onjoin", Boolean.TRUE);
            JoinLeave.plugin.getConfig().addDefault("Hide-Players-With-Perms", Boolean.FALSE);
            JoinLeave.plugin.getConfig().addDefault("Disable-Join-LeaveMessage", Boolean.FALSE);
            JoinLeave.plugin.getConfig().addDefault("Fireworks-onJoin", Boolean.TRUE);
            JoinLeave.plugin.getConfig().addDefault("Sound-onJoin", Boolean.TRUE);
            JoinLeave.plugin.getConfig().addDefault("Use-Essentials",  Boolean.FALSE);
            //getConfig().addDefault("Title-Onjoin-Message-Main", "WELCOME");
            //getConfig().addDefault("Title-Onjoin-Message-Bottem", "IN OUR SERVER");
            JoinLeave.plugin.saveConfig();
        }
        Message.sendToConsole(ChatColor.GREEN + "Config exist!");
    }
}
