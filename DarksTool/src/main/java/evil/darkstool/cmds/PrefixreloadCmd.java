package evil.darkstool.cmds;

import evil.darkstool.DarksTool;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class PrefixreloadCmd extends Command {
    private DarksTool plugin;
    public PrefixreloadCmd(String name, DarksTool plugin) {
        super(name);
        this.plugin = plugin;
    }
    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Tą komendę można użyć tylko z poziomu gracza");
            return true;
        }
        Player p = (Player) sender;
        if (p.hasPermission("darksmc.prefixreload")) {
            File configFile = new File(plugin.getDataFolder(), "prefix.yml");
            if (configFile.exists()) {
                FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
                plugin.reloadConfig();
                plugin.asyncChatPlayerListener.reloadConfig();
                sender.sendMessage("Konfiguracja prefix.yml została przeładowana!");
                return true;
            } else {
                sender.sendMessage("Plik prefix.yml nie istnieje!");
                return true;
            }
        } else {
            p.sendMessage("§#ff746aNie posiadasz permisji");
            return true;
        }
    }
}
