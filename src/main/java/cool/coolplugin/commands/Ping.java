package cool.coolplugin.commands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cool.coolplugin.utilities.PingUtil;

import static cool.coolplugin.CoolPlugin.colorize;

public class Ping implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = ((Player) sender);
        sender.sendMessage(colorize("&aPing: &c" + PingUtil.getPing(p) + "ms"));
        return true;
    }
}
