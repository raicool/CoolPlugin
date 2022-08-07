package cool.coolplugin.listeners;

import cool.coolplugin.CoolPlugin;
import cool.coolplugin.player.CoolPluginPlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.logging.Level;

import static cool.coolplugin.CoolPlugin.*;

public class ServerListener implements Listener
{
    private static CoolPlugin plugin;

    public ServerListener(CoolPlugin plugin)
    {
        ServerListener.plugin = plugin;
    }

    @EventHandler
    public void onMessageSend(AsyncPlayerChatEvent event)
    {
        Player player = event.getPlayer();
        event.setCancelled(true);

        // Check if they are muted
        if (CoolPluginPlayer.isMuted(player))
        {
            player.sendMessage(colorize("&7>> &cUnable to send message."));
            return;
        }

        String message = null;
        String format = null;

        for (Player recipient : event.getRecipients())
        {
            message = event.getMessage();

            // username: text
            format = CoolPluginPlayer.getPrefix(player) +
                     CoolPluginPlayer.getNameStylized(player) + "&7: &r";

            // Ping players if mentioned
            if (message.contains(recipient.getName()))
            {
                message = message.replace(recipient.getName(), "&a&o" + recipient.getName() + "&r");
                recipient.playSound(recipient.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 2.0f);
            }

            format = format.concat(message);
            recipient.sendMessage(colorize(format));
        }
        // Send to console since event gets canceled
        plugin.getLogger().log(Level.INFO, player.getName() + "(" + CoolPluginPlayer.getNickname(player) + ") : " + message);
    }

    @EventHandler
    public void playerJoinEvent (PlayerJoinEvent event)
    {
        Player player = event.getPlayer();

        // Check if user is not in data.yml
        // Apply changes to data.yml and save
        if (!CoolPluginPlayer.hasNickname(player))
        {
            CoolPluginPlayer.grabUUID(player);
            CoolPluginPlayer.setNickname(player, player.getName());
        }
    }
}
