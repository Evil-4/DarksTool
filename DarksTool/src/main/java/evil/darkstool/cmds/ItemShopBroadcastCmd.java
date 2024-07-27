package evil.darkstool.cmds;

import evil.darkstool.DarksTool;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemShopBroadcastCmd extends Command {
    DarksTool plugin;

    public ItemShopBroadcastCmd(String name, DarksTool plugin) {
        super(name);
        this.plugin = plugin;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String arg2, @NotNull String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Nie podano argumentów.");
            return false;
        }

        List<String> lines = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();

        for (String arg : args) {
            if (arg.equals("\\n")) {
                // Jeśli argument to "\n", dodajemy bieżącą linię do listy i zaczynamy nową linię
                lines.add(currentLine.toString());
                currentLine = new StringBuilder();
            } else {
                // W przeciwnym razie dodajemy argument do bieżącej linii
                if (currentLine.length() > 0) {
                    currentLine.append(" ");
                }
                currentLine.append(arg);
            }
        }
        // Dodajemy ostatnią linię do listy
        lines.add(currentLine.toString());

        for (String line : lines) {
            line = fixColors(line);
            Bukkit.broadcastMessage(line);
        }

        return true;
    }
    public String fixColors(String s) {
        Component component = MiniMessage.miniMessage().deserialize(s);
        return LegacyComponentSerializer.legacySection().serialize(component);
    }
}
