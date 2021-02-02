package cool.coolplugin.listeners;

import cool.coolplugin.commands.Protect;
import cool.coolplugin.protection.Limbo;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import static cool.coolplugin.CoolPlugin.colorize;
import static cool.coolplugin.CoolPlugin.data;

public class ServerListener extends Protect implements Listener {

    public static String checkPrefix(OfflinePlayer player) {
        if (player.isOp())
            return colorize("&c&lOp&r ");
        else return "";
    }
    
    @EventHandler
    public void chatFormat (AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String path = "players." + player.getName().toLowerCase() + ".color";
        String nickPath = "players." + player.getName().toLowerCase() + ".nickname";

        if (inLimbo(player, data)) {
            event.setCancelled(true);
        }
        event.setFormat(colorize(checkPrefix(player) + data.getConfig().getString(path) + data.getConfig().getString(nickPath) + "&7: &r" + event.getMessage()));
    }

    @EventHandler
    public void playerJoinEvent (PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // Check if player has a pass connected to account
        if (data.getConfig().getString("players." + player.getName().toLowerCase() + ".password") != null & data.getConfig().getBoolean("serverprotect", true)) {
            super.Prompt(player);
            Limbo.sendToLimbo(player);
        }

        // Apply changes to data.yml and save
        data.getConfig().set("players." + player.getName().toLowerCase() + ".uuid", player.getUniqueId().toString());
        if (data.getConfig().get("players." + player.getName().toLowerCase() + ".nickname") == null) {
            data.getConfig().set("players." + player.getName().toLowerCase() + ".nickname", player.getName());
        }
        data.saveConfig();
    }
}
