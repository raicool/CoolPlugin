package cool.coolplugin.protection;

import cool.coolplugin.commands.Protect;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

import static cool.coolplugin.CoolPlugin.data;

public class Limbo extends Protect {

    static Map<Player, Location> locationMap = new HashMap<>();

    public void Prompt(Player player) {
        player.sendMessage("&7>> &cThis player has a password connected to their account. \n" +
                "in order to play you have to enter the password. \n" +
                "type /password <password> to enter the password.");
    }

    public static void sendToLimbo(Player player) {
        // create map for players prior to teleport
        final String prelimbo = "players." + player.getName().toLowerCase() + ".prelimbo";

        Location location = new Location(Bukkit.getWorld(data.getConfig().getString("serverlimbo")), 0.5, 1, 0.5);

        if (data.getConfig().get(prelimbo) == null)
            locationMap.put(player, player.getLocation());
        else
            locationMap.put(player, data.getConfig().getLocation(prelimbo));

        data.getConfig().set(prelimbo, locationMap.get(player));

        player.teleport(location);
        player.setGameMode(GameMode.SPECTATOR);
        player.setFlySpeed(0f);
        player.sendMessage("\nSent to Limbo.");
    }

    public static void escapeLimbo(Player player) {
        player.setGameMode(GameMode.SURVIVAL);
        player.setFlySpeed(0.1f);
        player.teleport(locationMap.get(player));
    }
}
