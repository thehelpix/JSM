package ru.hdev.hstaffjoin.utils;

import org.bukkit.Bukkit;
import org.bukkit.util.Consumer;
import ru.hdev.hstaffjoin.Main;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class UpdateChecker {

    private final Main plugin;
    private final int resourceId = 84913;

    public UpdateChecker(Main plugin) {
        this.plugin = plugin;
    }

    public void getVersion(Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId).openStream(); Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
                }
            } catch (IOException exception) {
                plugin.getColor().updateLog("&cОшибка проверки обновлений: " + exception.getMessage());
            }
        });
    }

    public void checkUpdate(Main instance) {
        new UpdateChecker(instance).getVersion(version -> {
            if (Double.parseDouble(version) > Double.parseDouble(instance.getDescription().getVersion())) {
                plugin.getColor().updateLog("&eНайдена новая версия плагина &6%new_version%&e. &7(У Вас %this_version%)".replace("%new_version%", version).replace("%this_version%", instance.getDescription().getVersion()));
                plugin.getColor().updateLog("&eСкачать в &6SpigotMC&e:");
                plugin.getColor().updateLog("&6spigotmc.org/resources/aap.84913");
            } else if (Double.parseDouble(version) < Double.parseDouble(instance.getDescription().getVersion())) {
                plugin.getColor().updateLog("&eОго, Вы из будущего. У Вас версия плагина &c%this_version%, &eкогда в релизе версия &4%new_version%&e.".replace("%this_version%", instance.getDescription().getVersion()).replace("%new_version%", version));
            } else if (version.equals(instance.getDescription().getVersion())) {
                plugin.getColor().updateLog("&eУ Вас самая новая версия плагина. &7(%this_version%)".replace("%this_version%", instance.getDescription().getVersion()));
            }
        });
    }
}
