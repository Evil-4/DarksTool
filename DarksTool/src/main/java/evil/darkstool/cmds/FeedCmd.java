package evil.darkstool.cmds;

import evil.darkstool.DarksTool;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FeedCmd extends Command {

    private DarksTool plugin;
    public FeedCmd(String name, DarksTool plugin) {
        super(name);
        this.plugin = plugin;
    }
    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String arg2, @NotNull String[] args) {
        if (args.length == 0) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("darksmc.feed")) {

                    p.setFoodLevel(20);
                    p.sendMessage("§a✔ §x§7§9§F§F§5§5Zostałeś nakarmiony");
                } else {
                    p.sendMessage("§x§F§F§3§8§3§8✖ §cNie masz uprawnień do tej komendy");
                }
            }
        } else if (args.length == 1) {
            Player p = (Player) sender;
            if (p.hasPermission("darksmc.feed.other")) {
                Player cel = Bukkit.getPlayerExact(args[0]);

                if (cel != null) {
                    cel.setFoodLevel(20);
                    cel.sendMessage("§a✔ §x§7§9§F§F§5§5Zostałeś nakarmiony przez §x§3§0§D§F§0§0 " + sender.getName());

                    p.sendMessage("§a✔ §x§7§9§F§F§5§5Nakarmiłeś gracza §x§3§0§D§F§0§0 " + cel.getDisplayName());
                }
            } else {
                p.sendMessage("§x§F§F§3§8§3§8✖ §cNie masz uprawnień do tej komendy");
            }
        } else {
            sender.sendMessage("§7 Użycie: §c/feed [nick]");
        }

        return false;
    }
}
