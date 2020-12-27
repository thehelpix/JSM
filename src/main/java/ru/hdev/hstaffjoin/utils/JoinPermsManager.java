package ru.hdev.hstaffjoin.utils;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import ru.hdev.hstaffjoin.Main;

import java.util.List;

public class JoinPermsManager {
    private final Main instance;

    public JoinPermsManager(Main plugin) {
        this.instance = plugin;
    }

    public Boolean hasPerms(Player player) {
        List<String> perms = instance.getConfigManger().getPermsList();
        boolean bool = false;
        for (String perm : perms) {
            if (player.hasPermission(perm)) {
                bool = true;
            }
        }
        if (player.isOp()) {
            bool = true;
        }
        return bool;
    }

    public void joinBroadcast(Player player) {
        if (instance.getConfigManger().isBroadcastJoin()) {
            if (player.getGameMode().equals(GameMode.SPECTATOR) || !player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                Bukkit.broadcastMessage(instance.getColor().parse(instance.getMessagesManager().getMsgJoin("broadcast").replace("%player%", player.getName())));
            }
        }

        if (instance.getConfigManger().isJoinAdminsBroadcast()) {
            for (Player admin : Bukkit.getOnlinePlayers()) {
                if (hasPerms(admin) || admin.isOp()) {
                    admin.sendMessage(instance.getColor().parse(instance.getMessagesManager().getMsgJoin("admins").replace("%player%", player.getName())));
                }
            }
        }
    }

    public void leaveBroadcast(Player player) {
        if (instance.getConfigManger().isBroadcastLeave()) {
            if (player.getGameMode().equals(GameMode.SPECTATOR) || !player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                Bukkit.broadcastMessage(instance.getColor().parse(instance.getMessagesManager().getMsgLeave("broadcast").replace("%player%", player.getName())));
            }
        }

        if (instance.getConfigManger().isLeaveAdminsBroadcast()) {
            for (Player admin : Bukkit.getOnlinePlayers()) {
                if (hasPerms(admin) || admin.isOp()) {
                    admin.sendMessage(instance.getColor().parse(instance.getMessagesManager().getMsgLeave("admins").replace("%player%", player.getName())));
                }
            }
        }
    }
}
