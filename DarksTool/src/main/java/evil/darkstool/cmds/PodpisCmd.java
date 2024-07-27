package evil.darkstool.cmds;

import evil.darkstool.DarksTool;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class PodpisCmd extends Command {
    DarksTool plugin;

    public PodpisCmd(String name, DarksTool plugin) {
        super(name);
        this.plugin = plugin;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String arg2, @NotNull String[] args) {
        if (sender instanceof Player player) {
            ItemStack item = player.getInventory().getItemInMainHand();

            if (!item.getType().equals(Material.AIR)) {
                ItemMeta meta = item.getItemMeta();

                if (meta != null) {
                    meta.setDisplayName(fixColors("<rainbow>Podpis od: <b>" + player.getDisplayName() + "</b></rainbow>"));
                    item.setItemMeta(meta);
                    player.getInventory().setItemInMainHand(item);
                }
            } else {
                sender.sendMessage("§x§F§F§3§8§3§8✖ §cMusisz trzymać jakiś przedmiot w ręce!");
            }
        }
        return true;
    }
    public String fixColors(String s) {
        Component component = MiniMessage.miniMessage().deserialize(s);
        return LegacyComponentSerializer.legacySection().serialize(component);
    }
}
