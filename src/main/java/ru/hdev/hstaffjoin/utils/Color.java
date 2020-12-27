package ru.hdev.hstaffjoin.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import ru.hdev.hstaffjoin.Main;

public class Color {
    private final Main instance;

    public Color(Main plugin) {
        this.instance = plugin;
    }

    public String parse(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public void log(String str) {
        Bukkit.getConsoleSender().sendMessage(parse("&e[&6"+instance.getDescription().getName()+"&e] &6"+str));
    }

    public String cmd(String str) {
        return parse("&e| &6"+instance.getDescription().getName()+" &e>> &6"+str);
    }

    public void updateLog(String text) {
        Bukkit.getConsoleSender().sendMessage(parse("&6[&e"+instance.getDescription().getName()+" Updater&6] &e"+text));
    }
}
