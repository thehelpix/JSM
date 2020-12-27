package ru.hdev.hstaffjoin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import ru.hdev.hstaffjoin.Main;

public class StaffCmd implements CommandExecutor {
    private final Main instance;

    public StaffCmd(Main plugin) {
        this.instance = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        instance.getCommandsManager().sendOnlineAdminList(sender);
        return true;
    }
}
