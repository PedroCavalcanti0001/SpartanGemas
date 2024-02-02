package me.zkingofkill.spartan.gemas;

import me.clip.placeholderapi.PlaceholderAPI;
import me.zkingofkill.spartan.gemas.commands.Gemas;
import me.zkingofkill.spartan.gemas.hooks.PlaceHolderAPI;
import me.zkingofkill.spartan.gemas.mysql.Cache;
import me.zkingofkill.spartan.gemas.mysql.Mysql;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;

public final class SpartanGemas extends JavaPlugin {
    private static SpartanGemas instance;
    private Cache cache;
    private Mysql mysql;
    @Override
    public void onEnable() {
        instance = this;
        if(!new File(getDataFolder(), "config.yml").exists()){
            saveDefaultConfig();
        }
        mysql = new Mysql();
        mysql.setup();
        cache = new Cache();
        cache.setup();
        getCommand("gemas").setExecutor(new Gemas());

        if (this.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new PlaceHolderAPI(this, "SpartanGemas").hook();
        } else {
            System.out.println("PlaceholderAPI não encontrado.");
        }
    }
    @Override
    public void onDisable() {
        getCache().saveGemas();
    }

    public static SpartanGemas getInstance() { return instance; }

    public Cache getCache() { return cache; }

    public Mysql getMysql() { return mysql; }
}
