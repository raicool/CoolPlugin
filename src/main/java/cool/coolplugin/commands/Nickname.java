package cool.coolplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static cool.coolplugin.CoolPlugin.colorize;
import static cool.coolplugin.CoolPlugin.data;

public class Nickname implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player))
            return false;

        String path = "players." + sender.getName() + ".nickname";

        if (args.length <= 0) {
            data.getConfig().set(path, sender.getName());
            sender.sendMessage(colorize("&7>> &aReset Nickname!"));
            return true;
        }

        sender.sendMessage(colorize("&7>> &aSet Nickname to: " + args[0] + "!"));

        // Add nickname indicator and save data.yml
        data.getConfig().set(path, "~" + args[0]);
        data.saveConfig();
        return true;
    }
}
