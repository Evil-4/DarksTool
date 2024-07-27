package evil.darkstool.cmds;

import evil.darkstool.DarksTool;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ExpCmd extends Command {

    DarksTool plugin;

    public ExpCmd(String name, DarksTool plugin) {
        super(name);
        this.plugin = plugin;
    }


    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String arg2, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("darksmc.")) {
                int exp = (int) player.getExp();
                player.sendMessage("Posiadasz " + exp + "expa");
            } else {
                player.sendMessage("§x§F§F§3§8§3§8✖ §cNie masz uprawnień do tej komendy");
            }
        }
        return false;
    }
}
