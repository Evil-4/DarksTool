package evil.darkstool.cmds;

import evil.darkstool.DarksTool;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HealCmd extends Command {

    DarksTool plugin;

    public HealCmd(String name, DarksTool plugin) {
        super(name);
        this.plugin = plugin;
    }
    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String arg2, @NotNull String[] args) {
        if (sender.isOp() || sender.hasPermission("darksmc.heal")) {
            if (args.length == 0) {
                if (sender instanceof Player) {
                    Player p = (Player) sender;

                    p.setHealth(20);
                    p.setFoodLevel(20);
                    p.setFireTicks(0);
                    p.sendMessage("§a✔ §x§7§9§F§F§5§5Zostałeś uleczony");
                }
            } else if (args.length == 1) {
                Player cel = Bukkit.getPlayerExact(args[0]);

                if (cel != null) {
                    cel.setHealth(20);
                    cel.setFoodLevel(20);
                    cel.setFireTicks(0);
                    cel.sendMessage("§a✔ §x§7§9§F§F§5§5Zostałeś uleczony przez §x§3§0§D§F§0§0 " + sender.getName());

                    sender.sendMessage("§a✔ §x§7§9§F§F§5§5Uleczyłeś gracza §x§3§0§D§F§0§0 " + cel.getDisplayName());
                }
            } else {
                sender.sendMessage("§7 Użycie: §c/heal [nick]");
            }
        } else {
            sender.sendMessage("§x§F§F§3§8§3§8✖ §cNie masz uprawnień do tej komendy");
        }
        return false;
    }
}
