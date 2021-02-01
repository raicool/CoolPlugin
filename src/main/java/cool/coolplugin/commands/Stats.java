package cool.coolplugin.commands;

import cool.coolplugin.commands.interfaces.CommandInterface;
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

public class Stats implements CommandExecutor, CommandInterface {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        OfflinePlayer player;
        String path;

        if (args.length <= 0)
            player = Bukkit.getPlayer(sender.getName());
        else
            try
            {
                player = Bukkit.getOfflinePlayer(data.findUUID(args[0]));
            }
            catch (NullPointerException | IllegalArgumentException e)
            {
                sender.sendMessage(colorize("&cERROR: This person may not have logged on or may have never existed."));
                return true;
            }

        assert player != null;
        path = "players." + player.getName() + ".color";

        // Check if prefix or name color is not null

        // Name color
        if (!(data.getConfig().get(path) == null))
            defaultNameColor = data.getConfig().getString(path);

        // Prefix
        if (!(checkPrefix(player) == null))
            defaultPrefix = checkPrefix(player);

        // Main stats message
        sender.sendMessage(colorize(
                "&bStatistics of player &l"+ player.getName() +
                "\n&r&aUUID&0.......................&a: " + player.getUniqueId().toString() +
                "\n&aDisplay Name&0..&a: " + defaultPrefix + defaultNameColor + player.getName() +
                "\n&aBanned?&0.............&a: " + player.isBanned() +
                "\n&aOp?&0.........................&a: " + player.isOp() +
                "\n&aLast Online&0......&a: " + Time(player.getLastSeen()) +
                "\n&aIn Limbo?.........:" + inLimbo(player.getPlayer(),data)));

        return true;
    }

    private static String defaultPrefix = "";
    private static String defaultNameColor = "&a";

    public String Time(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("EST"));
        cal.setTimeInMillis(time);
        return ((cal.get(Calendar.MONTH) + 1) + "/" +
                cal.get(Calendar.DAY_OF_MONTH) + "/" +
                cal.get(Calendar.YEAR) + " at " +
                cal.get(Calendar.HOUR_OF_DAY) + ":" +
                cal.get(Calendar.MINUTE) + " EST (UTC-05:00)");
    }
}
