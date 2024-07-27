package evil.darkstool.api;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class GamemodeAPI {

    public void checkGamemode(String[] args, Player p) {
        switch (args[0]) {
            case "0":
                if (p.hasPermission("darksmc.survival")) {
                    setGamemode(p, GameMode.SURVIVAL);
                    p.setAllowFlight(false);
                } else {
                    p.sendMessage("§x§F§F§3§8§3§8✖ §cNie masz uprawnień do tej komendy");
                }
                break;
            case "1":
                if (p.hasPermission("darksmc.creative")) {
                setGamemode(p, GameMode.CREATIVE);
                    p.setAllowFlight(true);
                } else {
                    p.sendMessage("§x§F§F§3§8§3§8✖ §cNie masz uprawnień do tej komendy");
                }
                break;
            case "2":
                if (p.hasPermission("darksmc.adventure")) {
                    setGamemode(p, GameMode.ADVENTURE);
                    p.setAllowFlight(false);
                } else {
                    p.sendMessage("§x§F§F§3§8§3§8✖ §cNie masz uprawnień do tej komendy");
                }
                break;
            case "3":
                if (p.hasPermission("darksmc.spectator")) {
                    setGamemode(p, GameMode.SPECTATOR);
                    p.setAllowFlight(true);
                } else {
                    p.sendMessage("§x§F§F§3§8§3§8✖ §cNie masz uprawnień do tej komendy");
                }
                break;
            default:
                getDefault(p);
                break;
        }
    }

    public void checkGamemode(String[] args, Player p, Player cel) {
        switch (args[0]) {
            case "0":
                if (p.hasPermission("darksmc.survival.other")) {
                    setGamemode(p, cel, GameMode.SURVIVAL);
                    p.setAllowFlight(false);
                } else {
                    p.sendMessage("§x§F§F§3§8§3§8✖ §cNie masz uprawnień do tej komendy");
                }
                break;
            case "1":
                if (p.hasPermission("darksmc.creative.other")) {
                    setGamemode(p, cel, GameMode.CREATIVE);
                    p.setAllowFlight(true);
                } else {
                    p.sendMessage("§x§F§F§3§8§3§8✖ §cNie masz uprawnień do tej komendy");
                }
                break;
            case "2":
                if (p.hasPermission("darksmc.adventure.other")) {
                    setGamemode(p, cel, GameMode.ADVENTURE);
                    p.setAllowFlight(false);
                } else {
                    p.sendMessage("§x§F§F§3§8§3§8✖ §cNie masz uprawnień do tej komendy");
                }
                break;
            case "3":
                if (p.hasPermission("darksmc.spectator.other")) {
                    setGamemode(p, cel, GameMode.SPECTATOR);
                    p.setAllowFlight(true);
                } else {
                    p.sendMessage("§x§F§F§3§8§3§8✖ §cNie masz uprawnień do tej komendy");
                }
                break;
            default:
                getDefault(p);
                break;
        }
    }

    public void setGamemode(Player p, GameMode gm) {
        p.setGameMode(gm);
        p.sendMessage("§a✔ §x§7§9§F§F§5§5Twój tryb gry został zmieniony na §x§3§0§D§F§0§0" + gm.toString());
    }

    public void setGamemode(Player p, Player cel, GameMode gm) {
        cel.setGameMode(gm);
        cel.sendMessage("§a✔ §x§7§9§F§F§5§5Twój tryb gry został zmieniony na §x§3§0§D§F§0§0" + gm.toString());

        p.sendMessage("§a✔ §x§7§9§F§F§5§5Zmieniłeś tryb gry gracza §x§3§0§D§F§0§0" + cel.getDisplayName() + " §x§7§9§F§F§5§5na §x§3§0§D§F§0§0" + gm.toString());

    }

    public void getDefault(Player p) {
        p.sendMessage("§7Użycie: §c/gm [0/1/2/3] [nick]");
        p.sendMessage("§7Użycie: §c/gm [s/c/a/spec] [nick]");
    }
}
