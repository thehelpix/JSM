package ru.hdev.hstaffjoin.configManager;

import ru.hdev.hstaffjoin.Main;

public class MessagesManager {
    private final Main instance;

    public MessagesManager(Main plugin) {
        this.instance = plugin;
    }

    public String getMsgGame(String msg) {
        return instance.getMessagesConfig().getConfig().getString("messages."+msg);
    }

    public String getMsgCmd(String msg) {
        return getMsgGame("command."+msg);
    }

    public String getMsgJoin(String msg) {
        return getMsgGame("join."+msg);
    }

    public String getMsgLeave(String msg) {
        return getMsgGame("leave."+msg);
    }
}
