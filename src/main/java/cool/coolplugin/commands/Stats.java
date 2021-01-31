package cool.coolplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Calendar;
import java.util.TimeZone;

import static cool.coolplugin.CoolPlugin.colorize;
import static cool.coolplugin.CoolPlugin.data;
import static cool.coolplugin.listeners.ServerListener.checkPrefix;

public class Stats implements CommandExecutor {

    public String Time(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("EST"));
        cal.setTimeInMillis(time);
        return ((cal.get(Calendar.MONTH) + 1) + "-" +
                cal.get(Calendar.DAY_OF_MONTH) + "-" +
                cal.get(Calendar.YEAR) + " at " +
                cal.get(Calendar.HOUR_OF_DAY) + ":" +
                cal.get(Calendar.MINUTE) + " EST (UTC-05:00)");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        OfflinePlayer player;
        String path;

        // Check if there is an argument
        if (args.length <= 0) {
            // Get player from Command Sender
            player = Bukkit.getPlayer(sender.getName());
        } else {
            // Try to get player from Bukkit
            try {
                player = Bukkit.getOfflinePlayer(data.findUUID(args[0]));
            } catch (NullPointerException | IllegalArgumentException e) {
                sender.sendMessage(colorize("&cERROR: This person may not have logged on or may have never existed."));
                return true;
            }
        }

        assert player != null;
        path = "players." + player.getUniqueId() + ".color";

        // Check if Prefix or Name color is null
        String prefix;
        String nameColor;
        if (data.getConfig().get(path) == null)
        {
            nameColor = "&a";
        } else {
            nameColor = data.getConfig().get(path).toString();
        } if (checkPrefix(player) == null) {
            prefix = "";
        } else {
            prefix = checkPrefix(player);
        }

        // Main stats message
        sender.sendMessage(colorize(
                "&bStatistics of player &l"+ player.getName() +
                "\n&r&aUUID&0.......................&a: " + player.getUniqueId().toString() +
                "\n&aDisplay Name&0..&a: " + prefix + nameColor + player.getName() +
                "\n&aBanned?&0.............&a: " + player.isBanned() +
                "\n&aOp?&0.........................&a: " + player.isOp() +
                "\n&aLast Online&0......&a: " + Time(player.getLastSeen())));

        return true;
    }
}
