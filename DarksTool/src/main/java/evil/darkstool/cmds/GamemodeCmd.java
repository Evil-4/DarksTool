package evil.darkstool.cmds;

import evil.darkstool.DarksTool;
import evil.darkstool.api.GamemodeAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GamemodeCmd extends Command {

    DarksTool plugin;
    GamemodeAPI gma = new GamemodeAPI();

    public GamemodeCmd(String name, DarksTool plugin) {
        super(name);
        this.plugin = plugin;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (sender.isOp() || sender.hasPermission("darksmc.creative") || sender.hasPermission("darksmc.survival") || sender.hasPermission("darksmc.spectator")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (args.length == 1) {
                    gma.checkGamemode(args, p);
                    return true;
                } else if (args.length == 2) {
                    Player cel = Bukkit.getPlayerExact(args[1]);

                    if (cel != null) {
                        gma.checkGamemode(args, p, cel);
                        return true;
                    } else {
                        sender.sendMessage("§x§F§F§3§8§3§8✖ §cTen gracz nie istenieje lub jest offline");
                        return true;
                    }
                } else {
                    sender.sendMessage("§7Użycie: §c/gm [0/1/2/3] [nick]");
                    return true;
                }
            } else {
                sender.sendMessage("§7Tą komendę można użyć tylko z poziomu gracza");
                return true;
            }
        } else {
            sender.sendMessage("§x§F§F§3§8§3§8✖ §cNie masz uprawnień do tej komendy");
            return true;
        }
    }
}
