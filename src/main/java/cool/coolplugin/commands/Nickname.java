package cool.coolplugin.commands;

import cool.coolplugin.commands.utilities.CommandUtilities;
import cool.coolplugin.player.CoolPluginPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static cool.coolplugin.CoolPlugin.colorize;
import static cool.coolplugin.CoolPlugin.data;

public class Nickname extends CommandUtilities implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        isPlayer(sender);

        final String path = "players." + sender.getName().toLowerCase() + ".nickname";
        final String restrict_path = "players." + sender.getName().toLowerCase() + ".restrict";

        if (data.getConfig().getBoolean(restrict_path))
        {
            sender.sendMessage(colorize("&7>> &cYou cannot set your nickname right now."));
        }
        else
        {
            if (args.length <= 0)
            {
                data.getConfig().set(path, sender.getName());
                sender.sendMessage(colorize("&7>> &aReset Nickname!"));
                return true;
            }

            sender.sendMessage(colorize("&7>> &aSet Nickname to: " + args[0] + "!"));

            // Add nickname indicator and save data.yml
            data.getConfig().set(path, "~" + args[0]);
            data.saveConfig();
        }
        return true;
    }
}