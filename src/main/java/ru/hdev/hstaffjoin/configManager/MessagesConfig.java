package ru.hdev.hstaffjoin.configManager;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import ru.hdev.hstaffjoin.Main;

import java.io.File;
import java.io.IOException;

public class MessagesConfig {
    private final Main instance;
    public static FileConfiguration msgConfig;

    public MessagesConfig(Main plugin) {
        this.instance = plugin;
    }

    public FileConfiguration getConfig() {
        return msgConfig;
    }

    public void saveConfig() {
        File cenalConfigFile = new File(instance.getDataFolder(), "messages.yml");
        if (!cenalConfigFile.exists()) {
            instance.saveResource("messages.yml", false);
        }

        msgConfig = new YamlConfiguration();
        try {
            msgConfig.load(cenalConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
