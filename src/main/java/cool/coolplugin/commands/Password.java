package cool.coolplugin.commands;

import cool.coolplugin.commands.utilities.CommandUtilities;
import cool.coolplugin.protection.Limbo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static cool.coolplugin.CoolPlugin.data;

public class Password extends CommandUtilities implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        final String path = "players." + sender.getName().toLowerCase() + ".password";
        final Player player = (Player) sender;

        // Is password correct
        if (compare(args[0], data.getConfig().getString(path))) {
            Limbo.escapeLimbo(player);
            return true;
        }
        else player.kickPlayer("Invalid password");

        return true;
    }

    public Boolean compare(String password, String hashPassword) {
        return (Protect.decrypt(password.getBytes()).equals(hashPassword));
    }
}
