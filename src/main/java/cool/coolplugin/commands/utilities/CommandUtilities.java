package cool.coolplugin.commands.utilities;

import cool.coolplugin.listeners.DataListener;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandUtilities {

    protected boolean isPlayer(CommandSender player) {
        return player instanceof Player;
    }

    protected boolean isServerProtect(DataListener data) {
        return data.getConfig().getBoolean("serverprotect");
    }


    protected boolean inLimbo(Player player, DataListener data) {
        return player.getWorld() == Bukkit.getWorld(data.getConfig().getString("serverlimbo"));
    }


    protected boolean hasArgs(String[] args) {
        return args.length <= 0;
    }
}
