package cool.coolplugin.commands;

import cool.coolplugin.CoolPlugin;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static cool.coolplugin.CoolPlugin.colorize;

public class ServerStats implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Runtime runtime = Runtime.getRuntime();

        sender.sendMessage(colorize("&bServer Stats " +
                "\n&aHeap Memory: &c" + (toMegaBytesDecimal(runtime.totalMemory() - runtime.freeMemory()) + " &b/&c " + toMegaBytesDecimal(runtime.maxMemory())) + " MB" +
                "\n&aProcessors: &c" + runtime.availableProcessors() +
                "\n&aTPS: &c" + ((float)getServer.getTPS()[0])));

        return true;
    }

    private final Server getServer;

    private long toMegaBytesDecimal(long memory) {
        return memory / 1000000;
    }

    public ServerStats(CoolPlugin Server) {
        this.getServer = Server.getServer();
    }
}
