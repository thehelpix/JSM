package ru.hdev.hstaffjoin;

import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import ru.hdev.hstaffjoin.adminsData.AdminsData;
import ru.hdev.hstaffjoin.commands.CommandsManager;
import ru.hdev.hstaffjoin.configManager.ConfigManger;
import ru.hdev.hstaffjoin.configManager.MessagesConfig;
import ru.hdev.hstaffjoin.configManager.MessagesManager;
import ru.hdev.hstaffjoin.events.EventsManager;
import ru.hdev.hstaffjoin.utils.Color;
import ru.hdev.hstaffjoin.utils.JoinPermsManager;

import java.util.HashMap;
import java.util.Map;

public class Main extends JavaPlugin {
    private static Main plugin;
    private static Color color;
    private static ConfigManger configManger;
    private static JoinPermsManager joinPermsManager;
    private static EventsManager eventsManager;
    private static CommandsManager commandsManager;
    private static AdminsData adminsData;
    private static MessagesConfig messagesConfig;
    private static MessagesManager messagesManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        plugin = this;
        eventsManager = new EventsManager(this);
        color = new Color(this);
        joinPermsManager = new JoinPermsManager(this);
        configManger = new ConfigManger(this);
        commandsManager = new CommandsManager(this);
        adminsData = new AdminsData(this);
        messagesConfig = new MessagesConfig(this);
        messagesManager = new MessagesManager(this);
        Metrics metrics = new Metrics(this, 9789);

        metrics.addCustomChart(new Metrics.DrilldownPie("java_version", () -> {
            Map<String, Map<String, Integer>> map = new HashMap<>();
            String javaVersion = System.getProperty("java.version");
            Map<String, Integer> entry = new HashMap<>();
            entry.put(javaVersion, 1);
            if (javaVersion.startsWith("1.7")) {
                map.put("Java 1.7", entry);
            } else if (javaVersion.startsWith("1.8")) {
                map.put("Java 1.8", entry);
            } else if (javaVersion.startsWith("1.9")) {
                map.put("Java 1.9", entry);
            } else {
                map.put("Other", entry);
            }
            return map;
        }));

        metrics.addCustomChart(new Metrics.SingleLineChart("players", () -> {
            return Bukkit.getOnlinePlayers().size();
        }));

        messagesConfig.saveConfig();
        adminsData.checkAdmins();
        eventsManager.registerEvents();
        commandsManager.registerCommands();
        color.log("Плагин включён.");
    }

    @Override
    public void onDisable() {
        color.log("&aПлагин выключен.");
    }

    public MessagesManager getMessagesManager() {
        return messagesManager;
    }

    public MessagesConfig getMessagesConfig() {
        return messagesConfig;
    }

    public AdminsData getAdminsData() {
        return adminsData;
    }

    public EventsManager getEventsManager() {
        return eventsManager;
    }

    public CommandsManager getCommandsManager() {
        return commandsManager;
    }

    public ConfigManger getConfigManger() {
        return configManger;
    }

    public JoinPermsManager getJoinPermsManager() {
        return joinPermsManager;
    }

    public static Main getInstance() {
        return plugin;
    }

    public Color getColor() {
        return color;
    }
}
