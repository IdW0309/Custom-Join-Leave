package dev.idw0309.joinleave.commands.subcommands;

import dev.idw0309.joinleave.JoinLeave;
import dev.idw0309.joinleave.commands.SubCommandManager;
import dev.idw0309.joinleave.message.Message;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;


@SubCommandManager.SubCommand(name = "reload", description = "Reloads config", usage = "reload", permission = "joinleave.reload")
public class Reload implements SubCommandManager.SubCommandExecutor {

    public static String prefix;
    public static String language;

    @Override
    public void execute(CommandSender sender, String[] args) {

        File f = new File(JoinLeave.path, "config.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);

        prefix = cfg.getString("CommandPrefix");
        language = cfg.getString("Language");

        if (prefix != null) {
            prefix = "Join-Leave";
        }

        Message.sendToPlayer(sender, "Reloaded the config");

    }
}