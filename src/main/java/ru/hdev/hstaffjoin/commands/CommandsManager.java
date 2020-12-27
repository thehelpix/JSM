package ru.hdev.hstaffjoin.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.hdev.hstaffjoin.Main;

import java.util.List;
import java.util.Objects;

public class CommandsManager {
    private final Main instance;

    public CommandsManager(Main plguin) {
        this.instance = plguin;
    }

    public void sendOnlineAdminList(CommandSender sender) {
        List<Player> admins = instance.getAdminsData().getAdmins();

        sender.sendMessage(instance.getColor().cmd(instance.getMessagesManager().getMsgCmd("1st").replace("%amount%", String.valueOf(admins.size()))));
        if (admins.size() == 0) {
            sender.sendMessage(instance.getColor().parse(instance.getMessagesManager().getMsgCmd("noAdmins").replace("%amount%", String.valueOf(admins.size()))));
            sender.sendMessage(instance.getColor().parse(instance.getMessagesManager().getMsgCmd("amount").replace("%amount%", String.valueOf(admins.size()))));
        }
        if (admins.size() != 0) {
            for (Player admin : admins) {
                sender.sendMessage(instance.getColor().parse(instance.getMessagesManager().getMsgCmd("admin").replace("%player%", admin.getName()).replace("%amount%", String.valueOf(admins.size()))));
            }
            sender.sendMessage(instance.getColor().parse(instance.getMessagesManager().getMsgCmd("2st").replace("%amount%", String.valueOf(admins.size()))));
            sender.sendMessage(instance.getColor().parse(instance.getMessagesManager().getMsgCmd("amount").replace("%amount%", String.valueOf(admins.size()))));
        }
    }

    public void registerCommands() {
        Objects.requireNonNull(instance.getCommand("staff")).setExecutor(new StaffCmd(instance));
    }
}
