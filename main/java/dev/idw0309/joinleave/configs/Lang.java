package dev.idw0309.joinleave.configs;

import dev.idw0309.joinleave.JoinLeave;
import dev.idw0309.joinleave.message.Message;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Lang {

    public static void check() {
        Message.sendToConsole("Checking if language config exist...");

        if(!new File(JoinLeave.path, "lang.yml").exists()) {
            Message.sendToConsole("Creating a new language config...");

            File f = new File(JoinLeave.path, "lang.yml");
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);

            cfg.options().header("JOIN-LEAVE V2.4 Language Config || Copyright (C) 2023 IdW0309");
            cfg.set("Languages.EN.NoPermissions", "You re not allowed to use this command!");
            cfg.set("Languages.EN.NoSubCommand", "Please fill in a sub command!");
            cfg.set("Languages.EN.CommandDoesntExist", "This command doesnt exist!");
            cfg.set("Languages.EN.SetJoinMessage", "Join message has been set to");
            cfg.set("Languages.EN.ShowJoinMessage", "This is at this moment the join message");
            cfg.set("Languages.EN.SetLeaveMessage", "Leave message has been set to");
            cfg.set("Languages.EN.ShowLeaveMessage", "This is at this moment the leave message");

            cfg.set("Languages.NL.NoPermissions", "Je hebt niet de juiste permissies om dit command uit te voeren!");
            cfg.set("Languages.NL.NoSubCommand", "Vul een subcommando in!");
            cfg.set("Languages.NL.CommandDoesntExist", "Dit subcommando bestaat niet!");
            cfg.set("Languages.NL.SetJoinMessage", "Het join bericht is gezet naar");
            cfg.set("Languages.NL.ShowJoinMessage", "Op dit moment is de join message");
            cfg.set("Languages.NL.SetLeaveMessage", "Het leave bericht is gezet naar");
            cfg.set("Languages.NL.ShowLeaveMessage", "Op dit moment is de leave message");
            try {
                cfg.save(f);
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        Message.sendToConsole(ChatColor.GREEN + "Language config exist!");
    }
}
