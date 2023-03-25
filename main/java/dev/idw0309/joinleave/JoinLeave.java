package dev.idw0309.joinleave;

import java.io.File;

import dev.idw0309.joinleave.commands.SubCommandManager;
import dev.idw0309.joinleave.commands.subcommands.*;
import dev.idw0309.joinleave.configs.*;
import dev.idw0309.joinleave.events.PlayerJoinListener;
import dev.idw0309.joinleave.events.PlayerQuitListener;
import dev.idw0309.joinleave.message.Message;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import dev.idw0309.joinleave.commands.CommandManager;

public class JoinLeave extends JavaPlugin {

    public static JoinLeave plugin;

    public static boolean essentialsInstalled;

    public static String path;

    @Override
    public void onEnable() {


        path = getDataFolder().getAbsolutePath();
        ReloadInfo();
        Message.sendToConsole("Loading plugin assets...");
        plugin = this;
        createConfig();
        Commands();
        Events();

        int pluginId = 18040; // <-- Replace with the id of your plugin!
        Metrics metrics = new Metrics(this, pluginId);
        getLogger().info("Enabled metrics. You may opt-out by changing plugins/bStats/config.yml");

    }

    /**
     * Checks if essentials is installed
     */
    @Override
    public void onLoad() {
        if(getServer().getPluginManager().getPlugin("Essentials") != null) {
            Message.sendToConsole("Essentials installed. Essentials functions enabled.");
            essentialsInstalled = true;
            return;
        }

        Message.sendToConsole("Essentials is not installed. Essentials functions Disabled.");
        essentialsInstalled = false;
    }

    /**
     * Registers all the Events
     */
    private void Events() {
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
    }

    private void ReloadInfo() {
        File f = new File(JoinLeave.path, "config.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);

        if (cfg.getString("CommandPrefix") != null) {
            Reload.prefix = "Join-Leave";
        } else {
            Reload.prefix = cfg.getString("CommandPrefix");
        }

        Reload.language = cfg.getString("Language");

    }

    /**
     * Registers all the commands
     */
    private void Commands() {
        getCommand("joinleave").setExecutor(new CommandManager());
        SubCommandManager.addSubCommand(new Reload());
        SubCommandManager.addSubCommand(new Help());
        SubCommandManager.addSubCommand(new Show());
        SubCommandManager.addSubCommand(new Set());
    }

    /**
     * Creates configs if they do not exist
     */
    private void createConfig() {
        Message.sendToConsole("Checking if datafolder exist...");
        if(!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }
        Message.sendToConsole(ChatColor.GREEN + "Datafolder exist!");
        Config.check();
        Lang.check();
        Title.check();
        Sound.check();
        Fireworks.check();
        Message.sendToConsole(ChatColor.GREEN + "Plugin assets loaded!");
    }
}
