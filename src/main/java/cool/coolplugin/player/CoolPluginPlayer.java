package cool.coolplugin.player;

import org.bukkit.OfflinePlayer;

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

    public static boolean hasNickname(OfflinePlayer player)
    {
        return !getNickname(player).equals("");
    }

    public static String getNameColor(OfflinePlayer player)
    {
        String color = data.getConfig().getString("players." + player.getName().toLowerCase() + ".color");
        if (color == null) return "";
        else return color;
    }

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

    public static void toggleNickRestrict(OfflinePlayer player)
    {
        // xor the boolean and toggle
        data.getConfig().set("players." + player.getName().toLowerCase() + ".restrict", (hasNickRestrict(player) ^ true));
    }

    public static boolean hasNickRestrict(OfflinePlayer player)
    {
        return data.getConfig().getBoolean("players." + player.getName().toLowerCase() + ".restrict");
    }

}
