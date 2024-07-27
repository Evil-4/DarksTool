package evil.darkstool.cmds;

import evil.darkstool.DarksTool;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public class GammaCmd extends Command {

    private DarksTool plugin;
    public GammaCmd(String name, DarksTool plugin) {
        super(name);
        this.plugin = plugin;
    }
    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String arg2, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (!p.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0, false, false, false));
                p.sendMessage("§a✔ §x§7§9§F§F§5§5Gamma została: §x§3§0§D§F§0§0WŁĄCZONA");
            } else {
                p.removePotionEffect(PotionEffectType.NIGHT_VISION);
                p.sendMessage("§a✔ §x§7§9§F§F§5§5Gamma została: §cWYŁĄCZONA");
            }
        }
        return true;
    }
}
