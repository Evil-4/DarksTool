package evil.darkstool.cmds;

import evil.darkstool.DarksTool;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FlyCmd extends Command {

    private DarksTool plugin;
    public FlyCmd(String name, DarksTool plugin) {
        super(name);
        this.plugin = plugin;
    }
    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String arg2, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                if (p.hasPermission("darksmc.fly")) {
                    if (!p.getAllowFlight()) {
                        p.setAllowFlight(true);
                        p.sendMessage("§a✔ §x§7§9§F§F§5§5Twoje Latanie zostało: §x§3§0§D§F§0§0WŁĄCZONE");
                    } else {
                        p.setAllowFlight(false);
                        p.sendMessage("§a✔ §x§7§9§F§F§5§5Twoje Latanie zostało: §cWYŁĄCZONE");
                    }
                } else {
                    sender.sendMessage("§x§F§F§3§8§3§8✖ §cNie masz uprawnień do tej komendy");
                }
            } else if (args.length == 1) {
                Player cel = Bukkit.getPlayerExact(args[0]);
                if (p.hasPermission("darksmc.fly.other")) {
                    if (p.getUniqueId() == cel.getUniqueId()) {
                        if (!cel.getAllowFlight()) {
                            cel.setAllowFlight(true);
                            p.sendMessage("§a✔ §x§7§9§F§F§5§5Latanie dla gracza §x§3§0§D§F§0§0" + cel.getDisplayName() + " §x§7§9§F§F§5§5zostało: §x§3§0§D§F§0§0WŁĄCZONE");
                            cel.sendMessage("§a✔ §x§7§9§F§F§5§5Twoje Latanie zostało: §x§3§0§D§F§0§0WŁĄCZONE");
                        } else {
                            cel.setAllowFlight(false);
                            p.sendMessage("§a✔ §x§7§9§F§F§5§5Latanie dla gracza §x§3§0§D§F§0§0" + cel.getDisplayName() + " §x§7§9§F§F§5§5zostało: §cWYŁĄCZONE");
                            cel.sendMessage("§a✔ §x§7§9§F§F§5§5Twoje Latanie zostało: §cWYŁĄCZONE");
                        }
                    } else {
                        p.sendMessage("§x§F§F§3§8§3§8✖ §cNie możesz zmienić fly samemu sobie");
                    }
                } else {
                    p.sendMessage("§x§F§F§3§8§3§8✖ §cNie masz uprawnień do tej komendy");
                }
            }
        }
        return false;
    }
}