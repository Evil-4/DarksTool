package evil.darkstool.cmds;

import evil.darkstool.DarksTool;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RainCmd extends Command {

    private DarksTool plugin;
    public RainCmd(String name, DarksTool plugin) {
        super(name);
        this.plugin = plugin;
    }
    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String arg2, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("darksmc.rain")) {
                p.getWorld().setWeatherDuration(0);
                p.getWorld().setStorm(true);
                p.getWorld().setThundering(false);
                p.sendMessage("§a✔ §x§7§9§F§F§5§5Zmieniłeś pogodę na §x§3§0§D§F§0§0Deszczową");
            } else {
                p.sendMessage("§x§F§F§3§8§3§8✖ §cNie masz uprawnień do tej komendy!");
            }
        }
        return false;
    }
}
