package cool.coolplugin.commands;

import cool.coolplugin.CoolPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.Plugin;

import static cool.coolplugin.CoolPlugin.colorize;

public class Unload implements CommandExecutor {

    Plugin plugin;
    PluginLoader pluginLoader;
    public Unload(CoolPlugin server) {
        this.pluginLoader = server.getPluginLoader();
        this.plugin = server.getPlugin(server.getClass());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            pluginLoader.disablePlugin(this.plugin);
        } else {
            sender.sendMessage(colorize("&cConsole only command."));
        }
        return true;
    }
}
