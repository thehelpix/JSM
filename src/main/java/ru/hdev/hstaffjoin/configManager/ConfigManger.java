package ru.hdev.hstaffjoin.configManager;

import ru.hdev.hstaffjoin.Main;

import java.util.List;

public class ConfigManger {
    private final Main instance;

    public ConfigManger(Main plugin) {
        this.instance = plugin;
    }

    public List<String> getPermsList() {
        return instance.getConfig().getStringList("perms");
    }

    public Boolean isBroadcastJoin() {
        return instance.getConfig().getBoolean("settings.join.broadcast");
    }

    public Boolean isBroadcastLeave() {
        return instance.getConfig().getBoolean("settings.leave.broadcast");
    }

    public Boolean isJoinAdminsBroadcast() {
        return instance.getConfig().getBoolean("settings.join.admin_broadcast");
    }

    public Boolean isLeaveAdminsBroadcast() {
        return instance.getConfig().getBoolean("settings.leave.admin_broadcast");
    }
}
