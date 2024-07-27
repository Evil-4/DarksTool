package evil.darkstool.cmds;

import evil.darkstool.DarksTool;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TeleportationCmd extends Command {

    private DarksTool plugin;
    public TeleportationCmd(String name, DarksTool plugin) {
        super(name);
        this.plugin = plugin;
    }
    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String arg2, @NotNull String[] args) {
        Player p = (Player) sender;
        if (args.length == 1) {
            List<Player> matchPlayer = Bukkit.matchPlayer(args[0]);
            if (p.hasPermission("darksmc.tp")) {
                if (!matchPlayer.isEmpty()) {
                    Player cel = matchPlayer.get(0);
                    if (!p.getUniqueId().equals(cel.getUniqueId())) {
                        p.teleport(cel.getLocation());
                        p.sendMessage("§a✔ §x§7§9§F§F§5§5Teleportowałeś się do §x§3§0§D§F§0§0" + cel.getDisplayName());
                    } else {
                        p.sendMessage("§x§F§F§3§8§3§8✖ §cNie możesz teleportować samego siebie");
                    }
                } else {
                    p.sendMessage("§x§F§F§3§8§3§8✖ §cGracz nie istnieje lub jest offline");
                }
            } else {
                p.sendMessage("§x§F§F§3§8§3§8✖ §cNie masz uprawnień do tej komendy");
            }
        }
        return false;
    }
}
