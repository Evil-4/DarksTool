package evil.darkstool.events;

import evil.darkstool.DarksTool;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.File;
import java.util.*;



public class AsyncChatPlayerListener implements Listener {

    private final DarksTool plugin;
    private final NavigableMap<Integer, Map<String, String>> chatFormats = new TreeMap<>(Collections.reverseOrder());
    private Map<String, String> permissionToUrlMap = new HashMap<>();
    public AsyncChatPlayerListener(DarksTool plugin) {
        this.plugin = plugin;
        loadConfig();
        permissionToUrlMap.put("darksmc.link1", "https://link1.com");
        permissionToUrlMap.put("darksmc.link2", "https://link2.com");
    }

    private void loadConfig() {
        FileConfiguration config = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "prefix.yml"));
        List<String> formats = config.getStringList("Prefixy");

        for (String format : formats) {
            String[] parts = format.split(";");
            int priority = Integer.parseInt(parts[0]);
            String permission = parts[1];
            String prefix = parts[2];

            chatFormats.computeIfAbsent(priority, k -> new HashMap<>()).put(permission, prefix);
        }
    }

    public void reloadConfig() {
        chatFormats.clear();
        loadConfig();
    }

    @EventHandler
    public void onClickEvent(AsyncPlayerChatEvent event) {
        Player p = event.getPlayer();
        String playerName = p.getName();
        String msg = event.getMessage();

        for (Map<String, String> format : chatFormats.values()) {
            for (Map.Entry<String, String> entry : format.entrySet()) {
                if (p.hasPermission(entry.getKey())) {
                    String prefix = entry.getValue();
                    prefix = prefix.replace("{name}", playerName);
                    prefix = fixColors(prefix);
                    if (p.hasPermission("darksmc.chat-colorize")) {
                        msg = ChatColor.translateAlternateColorCodes('&', msg);
                    }
                    prefix = prefix.replace("{message}", msg);
                    prefix = prefix.replace("%", "%%");

                    if (p.hasPermission("darksmc.chat-link") && (msg.contains("http://www.") || msg.contains("https://www.")))  {
                        for (Player ps : Bukkit.getOnlinePlayers()) {
                            ps.sendMessage(prefix);
                        }
                        event.setCancelled(true);
                    }
                    event.setFormat(prefix);
                    return;
                }
            }
        }
    }


    public String fixColors(String s) {
        Component component = MiniMessage.miniMessage().deserialize(s);
        return LegacyComponentSerializer.legacySection().serialize(component);
    }
}

