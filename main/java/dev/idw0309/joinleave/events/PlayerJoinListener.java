package dev.idw0309.joinleave.events;

import com.earth2me.essentials.Essentials;
import dev.idw0309.joinleave.JoinLeave;
import dev.idw0309.joinleave.message.Message;
import dev.idw0309.joinleave.fireworks.Color;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.FireworkMeta;

import java.io.File;


public class PlayerJoinListener implements Listener {

    private String message = null;

    @EventHandler
    public void event(PlayerJoinEvent e) {

        final Player pl = e.getPlayer();

        e.setJoinMessage(null);

        message = cfg.getString("Join").replaceAll("&", "§");

        if (cfg.getString("Disable-Join-LeaveMessage").equals("true")) return;

        if (cfg.getString("Hide-Players-With-Perms").equals("true")) {
            if (pl.hasPermission("joinleave.silentjoin")) return;
        }

        firstJoined(pl);

        fireworksOnJoin(pl);

        soundOnJoin(pl);

        useEssentials(pl);

        Bukkit.broadcastMessage(message);

        titleOnJoin(pl);

    }

    File f = new File(JoinLeave.path, "config.yml");
    FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);

    private void titleOnJoin(Player pl) {

        if (!(cfg.getString("Title-Onjoin").equals("true"))) return;

        File f2 = new File(JoinLeave.path, "title.yml");
        FileConfiguration cfg2 = YamlConfiguration.loadConfiguration(f2);

        String subtitle = cfg2.getString("Title.lowermessage").replaceAll("&", "§");

        //pl.sendTitle( cfg.getString("Title-Onjoin-Message-Main").replaceAll("&", "§") , cfg.getString("Title-Onjoin-Message-Bottem").replaceAll("&", "§"), 50, 20, 1);
        pl.sendTitle(cfg2.getString("Title.message").replaceAll("&", "§"), subtitle, Integer.parseInt(cfg2.getString("Title.time.FadeIn")), Integer.parseInt(cfg2.getString("Title.time.StayIn")), Integer.parseInt(cfg2.getString("Title.time.FadeOut")));
    }

    private void useEssentials(Player pl) {

        if (!(cfg.getString("Use-Essentials").equals("true"))) return;

        if (JoinLeave.essentialsInstalled) {
            Essentials ess = (Essentials) Bukkit.getServer().getPluginManager().getPlugin("Essentials");

            assert ess != null;
            message = message.replaceAll("%player%", ess.getUserMap().getUser(pl.getName()).getNickname());
            return;
        }

        Message.sendToConsole("Essentials is not installed on your server. Please install before using this function.");
        message = message.replaceAll("%player%", pl.getName());

    }

    private void soundOnJoin(Player pl) {
        if (!(cfg.getString("Sound-onJoin").equals("true"))) return;

        File f3 = new File(JoinLeave.path, "sound.yml");
        FileConfiguration cfg3 = YamlConfiguration.loadConfiguration(f3);

        int volume = cfg3.getInt("Sound.Volume");
        int pitch = cfg3.getInt("Sound.Pitch");

        pl.playSound(pl.getLocation(), getSound(), volume, pitch);


    }


    private void fireworksOnJoin(Player pl) {

        if (!(cfg.getString("Fireworks-onJoin").equals("true"))) return;

        Location loc = pl.getLocation();
        Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();

        File f4 = new File(JoinLeave.path, "fireworks.yml");
        FileConfiguration cfg4 = YamlConfiguration.loadConfiguration(f4);


        fwm.setPower(cfg4.getInt("Fireworks.Power"));
        fwm.addEffect(FireworkEffect.builder().withColor(Color.parseColor(cfg4.getString("Fireworks.Color"))).flicker(cfg4.getBoolean("Fireworks.Flicker")).build());

        fw.setFireworkMeta(fwm);
        fw.detonate();

        for(int i = 0;i<1; i++) {
            Firework fw2 = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
            fw2.setFireworkMeta(fwm);
        }
    }

    private void firstJoined(Player pl) {
        boolean hasjoined = pl.hasPlayedBefore();

        if (!(cfg.getString("First-Join-Message").equals("true"))) return;

        if (hasjoined) {
            message = cfg.getString("Join").replaceAll("&", "§");
            return;
        }

        message = cfg.getString("First-Join").replaceAll("&", "§");
    }


    public static Sound getSound(){
        File f3 = new File(JoinLeave.path, "sound.yml");
        FileConfiguration cfg3 = YamlConfiguration.loadConfiguration(f3);

        return Sound.valueOf(cfg3.getString("Sound.Sound"));
    }

}
