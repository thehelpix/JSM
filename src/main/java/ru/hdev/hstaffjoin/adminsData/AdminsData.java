package ru.hdev.hstaffjoin.adminsData;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import ru.hdev.hstaffjoin.Main;

import java.util.ArrayList;
import java.util.List;

public class AdminsData {
    private final Main instance;
    protected List<Player> admins;

    public AdminsData(Main plugin) {
        this.instance = plugin;
        this.admins = new ArrayList<>();
    }

    public Boolean isListAdmin(Player player) {
        return this.admins.contains(player);
    }

    public List<Player> getAdmins() {
        return this.admins;
    }

    public void addAdmin(Player player) {
        if (!isListAdmin(player)) {
            this.admins.add(player);
        }
    }

    public void removeAdmin(Player player) {
        if (isListAdmin(player)) {
            admins.remove(player);
        }
    }

    public void checkAdmins() {
        Bukkit.getScheduler().runTaskTimer(instance, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (instance.getAdminsData().isListAdmin(player)) {
                    if (!instance.getJoinPermsManager().hasPerms(player)) {
                        removeAdmin(player);
                    }
                }
                if (instance.getJoinPermsManager().hasPerms(player)) {
                    if (!isListAdmin(player)) {
                        addAdmin(player);
                    }
                    if (player.getGameMode().equals(GameMode.SPECTATOR) || player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                        if (isListAdmin(player)) {
                            removeAdmin(player);
                        }
                    }
                }
            }
        }, 1L, 1L);
    }

}
