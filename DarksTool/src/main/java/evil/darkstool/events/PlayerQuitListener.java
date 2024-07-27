package evil.darkstool.events;

import evil.darkstool.DarksTool;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
public class PlayerQuitListener implements Listener {
    DarksTool plugin;

    public PlayerQuitListener(DarksTool m) {
        plugin = m;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (p.isInvulnerable()) {
            p.setInvulnerable(false);
        }
    }
}
