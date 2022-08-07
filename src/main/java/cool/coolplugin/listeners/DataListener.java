package cool.coolplugin.listeners;

import cool.coolplugin.CoolPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;
import java.util.logging.Level;

public class DataListener implements Listener
{
    private final CoolPlugin plugin;
    private FileConfiguration dataConfig = null;
    private File configFile = null;

    public DataListener(CoolPlugin plugin)
    {
        this.plugin = plugin;
    }

    public void reloadConfig()
    {
        if (this.configFile == null)
        {
            this.configFile = new File(this.plugin.getDataFolder(), "data.yml");
        }

        this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);

        InputStream defaultStream = this.plugin.getResource("data.yml");
        if (defaultStream != null)
        {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.dataConfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getConfig()
    {
        if (this.dataConfig == null)
        {
            reloadConfig();
        }

        return this.dataConfig;
    }

    public void saveConfig()
    {
        if (this.dataConfig == null || this.configFile == null)
            return;

        try
        {
            this.getConfig().save(configFile);
        }
        catch (IOException e)
        {
            plugin.getLogger().log(Level.SEVERE, "Could not save config to ", e);
        }
    }

    public void saveDefaultConfig()
    {
        if (this.configFile == null)
            this.configFile = new File(this.plugin.getDataFolder(), "data.yml");

        if (!this.configFile.exists())
            this.plugin.saveResource("data.yml", false);
    }

    public UUID findUUID(String player)
    {
        if (this.getConfig().contains("players." + player.toLowerCase() + ".uuid"))
            return UUID.fromString((String)this.getConfig().get("players." + player + ".uuid"));
        return null;
    }
}
