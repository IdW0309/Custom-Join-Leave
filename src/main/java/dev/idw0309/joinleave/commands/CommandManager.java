package dev.idw0309.joinleave.commands;

import dev.idw0309.joinleave.JoinLeave;
import dev.idw0309.joinleave.commands.subcommands.Reload;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import dev.idw0309.joinleave.commands.SubCommandManager.SubCommandState;
import dev.idw0309.joinleave.message.Message;

import java.io.File;

public class CommandManager implements CommandExecutor {
    
    /**
     * Main command of the plugin
     * @return true if the plugin detects something what isnt right, it will return true and stop the process.
     */
	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			Message.sendToConsole("Your not a player....");
			return true;
		}

		File f = new File(JoinLeave.path, "lang.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);

		File f2 = new File(JoinLeave.path, "config.yml");
		FileConfiguration cfg2 = YamlConfiguration.loadConfiguration(f2);

		Player player = (Player) sender;
		if (args.length == 0) {
			Message.sendToPlayer(player, cfg.getString("Languages." + Reload.language  + ".NoSubCommand"));
		}

		if (args.length > 0) {
			  SubCommandState executionState = SubCommandManager.execute(sender, args);
			  switch (executionState) {
			    case NOT_FOUND:
			    	Message.sendToPlayer(player, cfg.getString("Languages." + Reload.language  + ".CommandDoesntExist"));
			    	break;
			    case NO_PERMISSION: 
			    	Message.sendToPlayer(player, cfg.getString("Languages." + Reload.language  + ".NoPermissions"));
			      	break;
			    case INVALID_ARG_LENGTH: 
			    	Message.sendToPlayer(player, "Invalid arg length!");
			    	break;
			    default:
			  }
			}
	     
	     return true;
    }



}
