package evil.darkstool.cmds;

import evil.darkstool.DarksTool;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class HelpopCmd extends Command {

    HashMap<Player, Long> spam = new HashMap<Player,Long>();
    private DarksTool plugin;
    public HelpopCmd(String name, DarksTool plugin) {
        super(name);
        this.plugin = plugin;
    }
    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String arg2, @NotNull String[] args) {
        if (sender instanceof Player) {
            if (args.length >= 1) {
                Player p = (Player) sender;

                if (spam.containsKey(p) && !p.hasPermission("darksmc.helpop")) {
                    if (spam.get(p) > System.currentTimeMillis()) {
                        p.sendMessage("§cmusisz odczekać chwilę zanin ponownie użyjesz tej komendy");
                    } else {
                        StringBuilder sb = new StringBuilder();

                        for (int i = 0; i < args.length; ++i) {
                            sb.append(args[i]).append(" ");
                        }

                        String msg = sb.toString();

                        for (Player ps : Bukkit.getOnlinePlayers()) {
                            if (ps.isOp() || ps.hasPermission("darksmc.helpop")) {
                                ps.sendMessage(" §4§lHELPOP §x§F§F§6§5§6§5" + p.getDisplayName() + "§x§F§F§6§5§6§5: §x§F§F§7§5§7§5" + msg);
                            }
                        }

                        spam.put(p, System.currentTimeMillis() + 5 * 1000);
                        if (!p.hasPermission("darksmc.helpop")) {
                            sender.sendMessage(" §4§lHELPOP §x§F§F§6§5§6§5" + p.getDisplayName() + "§x§F§F§6§5§6§5: §x§F§F§7§5§7§5" + msg);
                        }
                    }

                } else {
                    StringBuilder sb = new StringBuilder();

                    for (int i = 0; i < args.length; ++i) {
                        sb.append(args[i]).append(" ");
                    }

                    String msg = sb.toString();

                    for (Player ps : Bukkit.getOnlinePlayers()) {
                        if (ps.isOp() || ps.hasPermission("darksmc.helpop")) {
                            ps.sendMessage(" §4§lHELPOP §x§F§F§6§5§6§5"+ p.getDisplayName() +"§x§F§F§6§5§6§5: §x§F§F§7§5§7§5" + msg);
                        }
                    }

                    spam.put(p, System.currentTimeMillis() + 5 * 1000);
                    if (!p.hasPermission("darksmc.helpop")) {
                        sender.sendMessage(" §4§lHELPOP §x§F§F§6§5§6§5" + p.getDisplayName() + "§x§F§F§6§5§6§5: §x§F§F§7§5§7§5" + msg);
                    }
                }
            } else {
                sender.sendMessage("§7Użycie: §chelpop [wiadomość]");
            }
        } else {
            sender.sendMessage("§cTą komende można użyć tylko z poziomu gracza");
        }
        return false;
    }
}
