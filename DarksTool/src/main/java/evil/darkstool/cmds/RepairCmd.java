package evil.darkstool.cmds;

import evil.darkstool.DarksTool;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class RepairCmd extends Command {

    private DarksTool plugin;
    public RepairCmd(String name, DarksTool plugin) {
        super(name);
        this.plugin = plugin;
    }
    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String arg2, @NotNull String[] args) {
        Player p = (Player) sender;

        if (args.length == 1) {
            if (p.hasPermission("darksmc.repair.all")) {
                if (args[0].equalsIgnoreCase("all")) {
                    ItemStack[] contents;

                    for (int lenght = (contents = p.getInventory().getContents()).length, i = 0; i < lenght; ++i) {
                        ItemStack item = contents[i];

                        if (item != null) {
                            item.setDurability((short) 0);
                        }
                    }
                    ItemStack[] armor;

                    for (int lenght2 = (armor = p.getEquipment().getArmorContents()).length, j = 0; j < lenght2; ++j) {
                        ItemStack item2 = armor[j];

                        if (item2 != null) {
                            item2.setDurability((short) 0);
                        }
                    }

                    p.sendMessage("§a✔ §x§7§9§F§F§5§5Naprawiono wszystkie przedmioty w ekwipunku");
                }
            } else {
                p.sendMessage("§cNie masz uprawnień do tej komendy");
            }
        } else if (args.length == 0) {
            if (p.hasPermission("darksmc.repair")) {
                p.getInventory().getItemInHand().setDurability((short) 0);
                p.sendMessage("§a✔ §x§7§9§F§F§5§5Naprawiono przedmiot w ręce");
            } else {
              p.sendMessage("§x§F§F§3§8§3§8✖ §cNie masz uprawnień do tej komendy!");
            }
        } else {
            p.sendMessage("§7Użycie: §e/repair [all]");
        }
        return false;
    }
}
