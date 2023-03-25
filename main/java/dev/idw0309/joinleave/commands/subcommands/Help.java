package dev.idw0309.joinleave.commands.subcommands;

import dev.idw0309.joinleave.commands.SubCommandManager;
import dev.idw0309.joinleave.message.Message;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@SubCommandManager.SubCommand(name = "help", description = "See all the commands of this plugin", usage = "help", permission = "joinleave.help")
public class Help implements SubCommandManager.SubCommandExecutor {

    /**
     * This is a help command that will send all the commands to the player that are in this plugin
     * @param sender is the player that runned the command
     */
    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        Message.sendHelpTextToPlayer(player, "/joinleave help\n"
                + "/joinleave reload\n"
                + "/joinleave set <join/leave> <message>\n"
                + "/joinleave show <join/leave>\n", 1, 1);
    }
}
