package evil.darkstool.cmds;

import evil.darkstool.DarksTool;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BroadcastCmd extends Command {

    private DarksTool plugin;
    public BroadcastCmd(String name, DarksTool plugin) {
        super(name);
        this.plugin = plugin;
    }
    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String arg2, @NotNull String[] args) {
        if (sender.isOp() || sender.hasPermission("darksmc.broadcast")) {
            if (args.length >= 1) {
                StringBuilder sb = new StringBuilder();

                for (int i = 1; i < args.length; ++i) {
                    sb.append(args[i]).append(" ");
                }

                String msg = sb.toString();

                if (args[0].equalsIgnoreCase("bc")) {
                    if  (!msg.isEmpty()) {
                        for (Player ps : Bukkit.getOnlinePlayers()) {
                            ps.sendMessage("§8[§cOgłoszenie§8] §e" + msg.replace("&", "§"));
                        }
                    }
                } else if (args[0].equalsIgnoreCase("title")) {
                    for (Player ps : Bukkit.getOnlinePlayers()) {
                        ps.sendTitle(msg.replace("&", "§"), " ");
                    }
                } else if (args[0].equalsIgnoreCase("actionbar")) {
                    for (Player ps : Bukkit.getOnlinePlayers()) {
                        ps.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(msg.replace("&", "§")));
                    }
                } else {
                    sender.sendMessage("§7Użycie: §c/bc [bc/title/actionbar]");
                }
            } else {
                sender.sendMessage("§7Użycie: §c/bc [bc/title/actionbar]");
            }
        } else {
            sender.sendMessage("§cNie masz uprawnień do tej komendy");
        }

        return false;
    }

    public boolean onsCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
        return false;
    }
}
