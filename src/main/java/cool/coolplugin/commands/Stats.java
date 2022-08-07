package cool.coolplugin.commands;

import cool.coolplugin.commands.utilities.CommandUtilities;
import cool.coolplugin.player.CoolPluginPlayer;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Calendar;
import java.util.TimeZone;

import static cool.coolplugin.CoolPlugin.colorize;
import static cool.coolplugin.CoolPlugin.data;

public class Stats extends CommandUtilities implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        OfflinePlayer player;

        if (args.length == 0)
        {
            player = Bukkit.getPlayer(sender.getName());
        }
        else
        {
            try
            {
                player = Bukkit.getOfflinePlayer(data.findUUID(args[0]));
            }
            catch (NullPointerException | IllegalArgumentException e)
            {
                sender.sendMessage(colorize("&cERROR: This person may not have logged on or may have never existed."));
                return true;
            }
        }
        assert player != null;

        // Check if prefix or name color is not null

        // Main stats message
        sender.sendMessage(colorize(
                "\n&bStatistics of player &l"               + player.getName() +
                "\n&r&aUUID&0.......................&a: &c" + player.getUniqueId().toString() +
                "\n&aDisplay Name&0..&a: &c"                + CoolPluginPlayer.getPrefix(player) + CoolPluginPlayer.getNameColor(player) + player.getName() +
                "\n&aBanned?&0.............&a: &c"          + player.isBanned() +
                "\n&aOp?&0.........................&a: &c"  + player.isOp() +
                "\n&aLast Online&0......&a: &c"             + Time(player.getLastSeen()) +
                "\n&aJoin Date&0..........&a: &c"           + Time(player.getFirstPlayed())));

        return true;
    }

    public String Time(long time)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("EST"));
        cal.setTimeInMillis(time);
        return (cal.get(Calendar.MONTH) + 1) + "/" +
                cal.get(Calendar.DAY_OF_MONTH) + "/" +
                cal.get(Calendar.YEAR) + " at " +
                cal.get(Calendar.HOUR_OF_DAY) + ":" +
                cal.get(Calendar.MINUTE) + " EST (UTC-05:00)";
    }
}
