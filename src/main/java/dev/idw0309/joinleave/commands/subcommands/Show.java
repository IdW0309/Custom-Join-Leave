package dev.idw0309.joinleave.commands.subcommands;

import dev.idw0309.joinleave.JoinLeave;
import dev.idw0309.joinleave.commands.SubCommandManager;
import dev.idw0309.joinleave.message.Message;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

@SubCommandManager.SubCommand(name = "show", description = "shows the join or leave message", usage = "show", permission = "joinleave.show")
public class Show implements SubCommandManager.SubCommandExecutor {

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if(args.length == 0) {
            Message.sendToPlayer(player, "/joinleave show <join/leave>");
            return;
        }

        if(args.length >= 2) {
            Message.sendToPlayer(player, "/joinleave show <join/leave>");
            return;
        }

        File f = new File(JoinLeave.path, "lang.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);

        File f2 = new File(JoinLeave.path, "config.yml");
        FileConfiguration cfg2 = YamlConfiguration.loadConfiguration(f2);

        String messageset = null;
        String message = null;

        if (args[0].equalsIgnoreCase("join")) {
            messageset = cfg.getString("Languages." + Reload.language  + ".ShowJoinMessage");
            message = cfg2.getString("Join").replaceAll("&", "§");
        } else if (args[0].equalsIgnoreCase("leave")) {
            messageset = cfg.getString("Languages." + Reload.language  + ".ShowLeaveMessage");
            message = cfg2.getString("Leave").replaceAll("&", "§");
        }

        if(messageset == null) {
            Message.sendToPlayer(sender, "The language " + Reload.language + " isn't known in lang.yml!");
            return;
        }

        Message.sendToPlayer(sender, messageset + " " + message);

    }
}
