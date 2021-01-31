package cool.coolplugin;

import cool.coolplugin.commands.*;
import cool.coolplugin.listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

public class CoolPlugin extends JavaPlugin {

    public static DataListener data;

    public static String colorize(String textToTranslate) {
        return net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', textToTranslate);
    }

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new ServerListener(), this);

        //load config.yml
        this.data = new DataListener(this);

        //load commands
        this.getCommand("Color").setExecutor(new Color());
        this.getCommand("Stats").setExecutor(new Stats());
        this.getCommand("Ping").setExecutor(new Ping());
        this.getCommand("Server").setExecutor(new ServerStats(this));
        this.getCommand("Unload").setExecutor(new Unload(this));
    }

    @Override
    public void onDisable() {
        this.saveConfig();
        setEnabled(false);
    }
}
