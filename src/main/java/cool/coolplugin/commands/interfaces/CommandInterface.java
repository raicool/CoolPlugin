package cool.coolplugin.commands.interfaces;

import cool.coolplugin.listeners.DataListener;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface CommandInterface {

    default boolean isPlayer(CommandSender player) {
        return player instanceof Player;
    }

    default boolean isServerProtect(DataListener data) {
        return data.getConfig().getBoolean("serverprotect");
    }

    default boolean inLimbo(Player player, DataListener data) {
        return player.getWorld() == Bukkit.getWorld(data.getConfig().getString("serverlimbo"));
    }

    default boolean hasArgs(String[] args) {
        return args.length <= 0;
    }
}
