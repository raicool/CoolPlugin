package cool.coolplugin.commands;

import cool.coolplugin.player.CoolPluginPlayer;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static cool.coolplugin.CoolPlugin.colorize;
import static cool.coolplugin.CoolPlugin.data;

public class Mute implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (args.length == 0)
        {
            sender.sendMessage(colorize("&7>> &cPlease specify a username!"));
            return true;
        }

        if (sender.isOp())
        {
            OfflinePlayer reciever = Bukkit.getOfflinePlayer(data.findUUID(args[0]));
            CoolPluginPlayer.toggleMute(reciever);

            if (CoolPluginPlayer.isMuted(reciever))
                sender.sendMessage(colorize("&7>> &a" + reciever.getName() + " has been muted."));
            else
                sender.sendMessage(colorize("&7>> &a" + reciever.getName() + " is no longer muted."));

            data.saveConfig();
            return true;
        }
        else
        {
            sender.sendMessage(colorize("&7>> &cInsufficient permissions."));
            return true;
        }
    }
}
