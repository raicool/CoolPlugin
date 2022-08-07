package cool.coolplugin.commands.utilities;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandUtilities
{
    protected boolean isPlayer(CommandSender player)
    {
        return player instanceof Player;
    }
}
