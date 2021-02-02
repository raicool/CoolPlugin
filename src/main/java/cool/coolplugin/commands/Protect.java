package cool.coolplugin.commands;

import cool.coolplugin.commands.utilities.CommandUtilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Base64;

import static cool.coolplugin.CoolPlugin.colorize;
import static cool.coolplugin.CoolPlugin.data;

public class Protect extends CommandUtilities implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        isPlayer(sender);
        isServerProtect(data);
        hasArgs(args);

        final String path = "players." + sender.getName() + ".password";

        // Player has no password
        if (data.getConfig().get(path) == null) {
            setPass(sender, args[0], path);

            data.saveConfig();
            return true;
        }
        // Password does not match
        if (!decrypt(args[0].getBytes()).equals(data.getConfig().getString(path))) {
            sender.sendMessage(colorize("&4Password did not match old password."));

            data.saveConfig();
            return true;
        }

        // Password matches
        setPass(sender, args[1], path);
        data.saveConfig();
        return true;
    }

    private void setPass(CommandSender sender, String pass, String path) {
        data.getConfig().set(path, decrypt(pass.getBytes()));
        sender.sendMessage(colorize("&7>> &aPassword set to: " + pass));
    }

    public static String decrypt(byte[] input) {
        return Arrays.toString(Base64.getEncoder().encode(input));
    }

    public void Prompt(Player player) {
        player.sendMessage(colorize("""
                &7>> &cThis player has a password connected to their account.\s
                in order to play you have to enter the password.\s
                type /password <password> to enter the password."""));
    }
}
