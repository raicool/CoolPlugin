package cool.coolplugin.listeners;

import cool.coolplugin.player.CoolPluginPlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import static cool.coolplugin.CoolPlugin.*;

public class ServerListener implements Listener
{
    @EventHandler
    public void onMessageSend(AsyncPlayerChatEvent event)
    {
        Player player = event.getPlayer();
        String message;
        String format;

        for (Player recipient : event.getRecipients())
        {
            message = event.getMessage();

            // username: text
            format =
                    CoolPluginPlayer.getPrefix(player) +
                    CoolPluginPlayer.getNameStylized(player) + "&7: &r";

            // Ping players if mentioned
            if (message.contains(recipient.getName()))
            {
                message = message.replace(recipient.getName(), "&a" + recipient.getName() + "&r");
                recipient.playSound(recipient.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 2.0f);
            }

            format += message;
            recipient.sendMessage(colorize(format));
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void playerJoinEvent (PlayerJoinEvent event)
    {
        Player player = event.getPlayer();

        // Apply changes to data.yml and save
        if (!CoolPluginPlayer.hasNickname(player))
        {
            data.getConfig().set("players." + player.getName().toLowerCase() + ".uuid", player.getUniqueId().toString());
            data.getConfig().set("players." + player.getName().toLowerCase() + ".nickname", player.getName());
        }

        data.saveConfig();
    }
}
