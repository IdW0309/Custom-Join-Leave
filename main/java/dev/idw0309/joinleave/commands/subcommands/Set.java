package dev.idw0309.joinleave.commands.subcommands;

import dev.idw0309.joinleave.JoinLeave;
import dev.idw0309.joinleave.commands.SubCommandManager;
import dev.idw0309.joinleave.message.Message;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

@SubCommandManager.SubCommand(name = "set", description = "set the join or leave message", usage = "set", permission = "joinleave.set")
public class Set implements SubCommandManager.SubCommandExecutor {

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        //Checks if the person filled in the full command
        if(args.length <= 1) {
            Message.sendToPlayer(player, "/joinleave set <join/leave> <message>");
            return;
        }

        File f = new File(JoinLeave.path, "lang.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);

        File f2 = new File(JoinLeave.path, "config.yml");
        FileConfiguration cfg2 = YamlConfiguration.loadConfiguration(f2);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            sb.append(args[i]);
            sb.append(" ");
        }
        String message = sb.toString();

        String messageset = null;
        String messageo = null;

        if (args[0].equals("join")) {
            cfg2.set("Join", message);
            messageset = cfg.getString("Languages." + Reload.language  + ".SetJoinMessage");
            messageo = cfg2.getString("Join").replaceAll("&", "§");

        } else if (args[0].equals("leave")) {
            cfg2.set("Leave", message);
            messageset = cfg.getString("Languages." + Reload.language  + ".SetLeaveMessage");
            messageo = cfg2.getString("Leave").replaceAll("&", "§");

        }

        try {
            cfg2.save(f2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(messageset == null) {
            Message.sendToPlayer(sender, "The language " + Reload.language + " isn't known in lang.yml!");
        } else {
            Message.sendToPlayer(sender, messageset + " " + messageo);
        }

    }
}
