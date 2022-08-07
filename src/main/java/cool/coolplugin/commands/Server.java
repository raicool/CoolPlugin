package cool.coolplugin.commands;

import cool.coolplugin.CoolPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static cool.coolplugin.CoolPlugin.colorize;

public class Server implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Runtime runtime = Runtime.getRuntime();
        OperatingSystemMXBean os = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        // get tps and color
        double tps = getServer.getTPS()[0];
        String tps_round = String.valueOf(round(tps, 3));
        if      (tps >= 15)            tps_round = "&b".concat(tps_round);
        else if (tps < 15 && tps > 10) tps_round = "&e".concat(tps_round);
        else if (tps < 10)             tps_round = "&c&l".concat(tps_round);

        sender.sendMessage(colorize(
                "\n&bServer Stats "           +
                "\n&aHeap Memory: &c"         + (MegaByte(runtime.totalMemory() - runtime.freeMemory()) + "&b / &c" + MegaByte(runtime.maxMemory())) + " MB" +
                "\n&aOs: &c"                  + os.getName() + "&b / &c&o" + os.getArch() + "&b / &c&o" + os.getVersion() +
                "\n&aThreads: &c"             + runtime.availableProcessors() +
                "\n&aCpu Usage (Process): &c" + round(os.getProcessCpuLoad() * 100, 2) + " &b%" +
                "\n&aCpu Usage (System): &c"  + round(os.getCpuLoad(), 2) * 100 + " &b%" +
                "\n&aTPS: &c"                 + tps_round
        ));

        return true;
    }

    private final org.bukkit.Server getServer;

    private long MegaByte(long memory)
    {
        return memory / 1000000;
    }

    public Server(CoolPlugin server)
    {
        this.getServer = server.getServer();
    }

    private double round(double val, int places)
    {
        if (places < 0) return val;

        BigDecimal bigdecimal = new BigDecimal(Double.toString(val));
        bigdecimal = bigdecimal.setScale(places, RoundingMode.HALF_UP);
        return bigdecimal.doubleValue();
    }
}
