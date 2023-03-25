package dev.idw0309.joinleave.message;

import dev.idw0309.joinleave.commands.subcommands.Reload;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Message {
	
	final static String prefix2;
	
	static {
		//prefix2 = ChatColor.RED + ChatColor.BOLD.toString() + Reload.prefix + " " + ChatColor.WHITE;
		prefix2 = ChatColor.RED + ChatColor.BOLD.toString() + "Join-Leave " + ChatColor.WHITE;
	}
	
	public static void sendToConsole(String message) {

		Bukkit.getConsoleSender().sendMessage(prefix2 + message);

	}
	
	public static void sendToPlayer(CommandSender sender, String message) {
		sender.sendMessage(prefix2 + message);
	}

	public static void sendErrorToPlayer(Player pl, String message) {
		pl.sendMessage(prefix2 + message);
	}
	
	public static void sendHelpTextToPlayer(CommandSender sender, String message, int i, int j) {
		sender.sendMessage("§e------------- §c§lJOIN-LEAVE §e-------------");
		sender.sendMessage(ChatColor.WHITE + message);
		sender.sendMessage("§7Page " + i + "/" + j);
		sender.sendMessage("§e------------- §c§lJOIN-LEAVE §e-------------");
	}
}
