package evil.darkstool.data;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class MsgData {

    static MsgData instance;
    Plugin p;
    FileConfiguration data;
    public static File rfile;

    static {
        instance = new MsgData();
    }

    public static MsgData getInstance() {
        return instance;
    }

    public void setup(Plugin p) {
        if (!p.getDataFolder().exists()) {
            p.getDataFolder().mkdir();
        }
        File path = new File(p.getDataFolder() + File.separator + "/data");
        rfile = new File(path, String.valueOf(File.separator + "msg.yml"));
        if (!rfile.exists()) {
            try {
                path.mkdir();
                rfile.createNewFile();
            } catch (IOException e) {
                Bukkit.getServer().getLogger().severe(ChatColor.RED + "Nie udało się stworzyć pliku msg.yml");
            }
        }
        data = YamlConfiguration.loadConfiguration(rfile);
    }

    public FileConfiguration getData() {

        return data;
    }

    public void saveData() {
        try {
            data.save(rfile);
        } catch (IOException e) {
            Bukkit.getServer().getLogger().severe(ChatColor.DARK_BLUE + "Nie udało się zapisać pliku msg.yml");
        }
    }

    public void reloadData() {
        data = YamlConfiguration.loadConfiguration(rfile);
    }
}
