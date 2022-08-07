package cool.coolplugin.player;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import static cool.coolplugin.CoolPlugin.colorize;
import static cool.coolplugin.CoolPlugin.data;

public class CoolPluginPlayer
{
    public static String getPrefix(OfflinePlayer player)
    {
        if (player.isOp()) return colorize("&c&lOp&r ");
        else return "";
    }

    public static String getNameStylized(OfflinePlayer player)
    {
        return getNameColor(player) + getNickname(player);
    }



    public static String getNameColor(OfflinePlayer player)
    {
        String color = data.getConfig().getString("players." + player.getName().toLowerCase() + ".color");
        if (color == null) return "";
        else return color;
    }

    // Nicknames
    public static String getNickname(OfflinePlayer player)
    {
        String nickname;

        if (hasNickRestrict(player))
            nickname = player.getName();
        else
            nickname = data.getConfig().getString("players." + player.getName().toLowerCase() + ".nickname");

        if (nickname == null) return "";
        else return nickname;
    }

    public static boolean hasNickname(OfflinePlayer player)
    {
        return !getNickname(player).equals("");
    }

    public static void setNickname(OfflinePlayer player, String nickname)
    {
        // ex: ~username:
        data.getConfig().set("players." + player.getName().toLowerCase() + ".nickname", "~" + nickname);
        data.saveConfig();
    }

    public static void toggleNickRestrict(OfflinePlayer player)
    {
        // xor the boolean and toggle
        data.getConfig().set("players." + player.getName().toLowerCase() + ".restrict", (hasNickRestrict(player) ^ true));
    }

    public static void toggleMute(OfflinePlayer player)
    {
        data.getConfig().set("players." + player.getName().toLowerCase() + ".muted", (isMuted(player) ^ true));
    }

    public static boolean hasNickRestrict(OfflinePlayer player)
    {
        return data.getConfig().getBoolean("players." + player.getName().toLowerCase() + ".restrict");
    }

    public static boolean isMuted(OfflinePlayer player)
    {
        return data.getConfig().getBoolean("players." + player.getName().toLowerCase() + ".muted");
    }

    // others
    public static void grabUUID(OfflinePlayer player)
    {
        data.getConfig().set("players." + player.getName().toLowerCase() + ".uuid", player.getUniqueId().toString());
    }
}
