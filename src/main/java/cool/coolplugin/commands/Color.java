package cool.coolplugin.commands;

import cool.coolplugin.commands.utilities.CommandUtilities;
import cool.coolplugin.player.CoolPluginPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static cool.coolplugin.CoolPlugin.colorize;
import static cool.coolplugin.CoolPlugin.data;

public class Color extends CommandUtilities implements CommandExecutor {

    private static String color = null;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        isPlayer(sender);

        final String path = "players." + sender.getName().toLowerCase() + ".color";

        if (args.length <= 0)
        {
            sender.sendMessage(colorize(
                               """
                               &aOptions:\s
                               &cred, &6orange, &eyellow, &agreen, &2darkgreen, &9blue, &5purple, &bcyan, &3aqua, &4darkred, &dpink, &fwhite

                               &aHex values are also supported (ex: &b#abcdef&a)"""
            ));
            return true;
        }

        if (args[0].startsWith("#"))
        {
            if (args[0].length() == 7)
            {
                color = args[0].replace("#", "x").replaceAll("(.)", "&$1");
                sender.sendMessage(colorize("&7>> &aYour " + color + CoolPluginPlayer.getNickname(Bukkit.getOfflinePlayer(sender.getName())) + "&r&a now."));
            }
            else
            {
                sender.sendMessage(colorize("&7>> &cInvalid hex code!"));
            }
        }
        else
        {
            switch (args[0])
            {
                case "reset" ->
                {
                    color = "&a";
                    sender.sendMessage("&7>> &aReset!");
                    break;
                }
                case "red" ->
                {
                    color = "&c";
                    sender.sendMessage(colorize("&7>> &aYou're &cRed&a now."));
                    break;
                }
                case "orange" ->
                {
                    color = "&6";
                    sender.sendMessage(colorize("&7>> &aYou're &6Orange&a now."));
                    break;
                }
                case "yellow" ->
                {
                    color = "&e";
                    sender.sendMessage(colorize("&7>> &aYou're &eYellow&a now."));
                    break;
                }
                case "green" ->
                {
                    color = "&a";
                    sender.sendMessage(colorize("&7>> &aYou're &aGreen&a now."));
                    break;
                }
                case "darkgreen" ->
                {
                    color = "&2";
                    sender.sendMessage(colorize("&7>> &aYou're &2Dark Green&a now."));
                    break;
                }
                case "blue" ->
                {
                    color = "&9";
                    sender.sendMessage(colorize("&7>> &aYou're &9Blue&a now."));
                    break;
                }
                case "purple" ->
                {
                    color = "&5";
                    sender.sendMessage(colorize("&7>> &aYou're &5Purple&a now."));
                    break;
                }
                case "cyan" ->
                {
                    color = "&b";
                    sender.sendMessage(colorize("&7>> &aYou're &bCyan&a now."));
                    break;
                }
                case "aqua" ->
                {
                    color = "&3";
                    sender.sendMessage(colorize("&7>> &aYou're &3Aqua&a now."));
                    break;
                }
                case "darkred" ->
                {
                    color = "&4";
                    sender.sendMessage(colorize("&7>> &aYou're &4Dark Red&a now."));
                    break;
                }
                case "pink" ->
                {
                    color = "&d";
                    sender.sendMessage(colorize("&7>> &aYou're &dPink&a now."));
                    break;
                }
                case "white" ->
                {
                    color = "&f";
                    sender.sendMessage(colorize("&7>> &aYou're &rWhite&a now."));
                    break;
                }
                default ->
                {
                    sender.sendMessage(colorize("&7>> &aIncorrect color."));
                    break;
                }
            }
        }

        data.getConfig().set(path, color);
        data.saveConfig();
        return true;
    }
}
