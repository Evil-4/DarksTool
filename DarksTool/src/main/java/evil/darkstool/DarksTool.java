package evil.darkstool;

import evil.darkstool.api.TitleAPI;
import evil.darkstool.cmds.*;
import evil.darkstool.data.MsgData;
import evil.darkstool.events.*;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.lang.reflect.Field;

public final class DarksTool extends JavaPlugin {

    public AsyncChatPlayerListener asyncChatPlayerListener;
    private static CommandMap commandMap;
    public static DarksTool main;
    //SpawnData spawnd = SpawnData.getInstance();
    MsgData md = MsgData.getInstance();

    public void onEnable() {
        main = this;
        this.asyncChatPlayerListener = new AsyncChatPlayerListener(this);
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }
        File config = new File(getDataFolder(), "prefix.yml");
        if (!config.exists()) {
            getLogger().info("prefix.yml nie znaleziony, tworzenie!");
            saveResource("prefix.yml", false);
        } else {
            getLogger().info("prefix.yml znaleziony, ładowanie!");
        }
        //spawnd.setup(this);
        md.setup(this);
        getCommandMap().register(main.getName(), new GamemodeCmd("gm", this));
        getCommandMap().register(main.getName(), new TeleportationCmd("tp", this));
        getCommandMap().register(main.getName(), new TeleportationHereCmd("tphere", this));
        getCommandMap().register(main.getName(), new HealCmd("heal", this));
        getCommandMap().register(main.getName(), new BroadcastCmd("bc", this));
        getCommandMap().register(main.getName(), new DayCmd("day", this));
        getCommandMap().register(main.getName(), new FeedCmd("feed", this));
        getCommandMap().register(main.getName(), new FlyCmd("fly", this));
        getCommandMap().register(main.getName(), new GammaCmd("gamma", this));
        getCommandMap().register(main.getName(), new GodCmd("god", this));
        getCommandMap().register(main.getName(), new NightCmd("night", this));
        getCommandMap().register(main.getName(), new PrefixreloadCmd("prefixreload", this));
        getCommandMap().register(main.getName(), new RainCmd("rain", this));
        getCommandMap().register(main.getName(), new RepairCmd("repair", this));
        getCommandMap().register(main.getName(), new StormCmd("storm", this));
        getCommandMap().register(main.getName(), new HelpopCmd("helpop", this));
        getCommandMap().register(main.getName(), new ItemShopBroadcastCmd("itemshopbc", this));
        getCommandMap().register(main.getName(), new PodpisCmd("podpis", this));
        getCommandMap().register(main.getName(), new ExpCmd("exp", this));

        new TitleAPI();

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);
        getServer().getPluginManager().registerEvents(this.asyncChatPlayerListener, this);
    }
    private CommandMap getCommandMap() {
        if (commandMap == null) {
            try {
                Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
                field.setAccessible(true);
                commandMap = (CommandMap) field.get(Bukkit.getServer());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return commandMap;
    }
}