package ru.hdev.hstaffjoin.events;

import org.bukkit.Bukkit;
import ru.hdev.hstaffjoin.Main;

public class EventsManager {
    private final Main instance;

    public EventsManager(Main plugin) {
        this.instance = plugin;
    }

    public void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new PlayerEvents(instance), instance);
    }
}
