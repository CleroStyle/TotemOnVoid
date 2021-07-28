package ru.clerostyle.totem;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class Main extends JavaPlugin implements Listener {
    public void onEnable() {
        File config = new File(getDataFolder(), "config.yml");
        if (!config.exists()) {
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }

        this.getCommand("totemonvoid").setExecutor(new Commands(this));
        Bukkit.getPluginManager().registerEvents(this, this);

        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.getWorld().getName().equals(getConfig().getString("worldname")) && p.getLocation().getY() <= 20) {
                        Location loc = new Location(Bukkit.getWorld(getConfig().getString("worldname")), getConfig().getDouble("spawn.X"), getConfig().getDouble("spawn.Y"), getConfig().getDouble("spawn.Z"), (float) getConfig().getDouble("spawn.yaw"), (float) getConfig().getDouble("spawn.pitch"));
                        p.teleport(loc);
                        p.playEffect(EntityEffect.TOTEM_RESURRECT);
                    }
                }
            }
        }, 0L, 1L);

    }
}
