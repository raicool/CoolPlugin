package cool.coolplugin;

import cool.coolplugin.commands.*;
import cool.coolplugin.listeners.DataListener;
import cool.coolplugin.listeners.ServerListener;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class CoolPlugin extends JavaPlugin
{
    public static DataListener data;

    public static String colorize(String translate)
    {
        return ChatColor.translateAlternateColorCodes('&', translate);
    }

    @Override
    public void onEnable()
    {
        this.getServer().getPluginManager().registerEvents(new ServerListener(this), this);

        //load config.yml
        data = new DataListener(this);

        //load commands
        this.getCommand("Color").setExecutor(new Color());
        this.getCommand("Stats").setExecutor(new Stats());
        this.getCommand("Nickname").setExecutor(new Nickname());
        this.getCommand("Mute").setExecutor(new Mute());
        this.getCommand("Restrict").setExecutor(new Restrict());
        this.getCommand("Server").setExecutor(new Server(this));
        this.getCommand("Unload").setExecutor(new Unload(this));
    }

    @Override
    public void onDisable()
    {
        this.getLogger().log(Level.INFO, "Plugin Disabled.");
        this.saveConfig();
        setEnabled(false);
    }
}
