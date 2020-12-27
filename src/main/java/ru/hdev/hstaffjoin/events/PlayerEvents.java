package ru.hdev.hstaffjoin.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffectType;
import ru.hdev.hstaffjoin.Main;

public class PlayerEvents implements Listener {
    private final Main instane;

    public PlayerEvents(Main plugin) {
        this.instane = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        Player player = event.getPlayer();

        if (instane.getJoinPermsManager().hasPerms(player)) {
            if (!instane.getAdminsData().isListAdmin(player)) {
                instane.getAdminsData().addAdmin(player);
            }
            if (!player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                instane.getJoinPermsManager().joinBroadcast(player);
            }
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        event.setQuitMessage(null);
        Player player = event.getPlayer();

        if (instane.getJoinPermsManager().hasPerms(player)) {
            if (instane.getAdminsData().isListAdmin(player)) {
                instane.getAdminsData().removeAdmin(player);
            }
            instane.getJoinPermsManager().leaveBroadcast(player);
        }
    }

    @EventHandler
    public void gmChange(PlayerGameModeChangeEvent event) {
        Player player = event.getPlayer();

        if (instane.getJoinPermsManager().hasPerms(player)) {
            if (instane.getAdminsData().isListAdmin(player)) {
                instane.getAdminsData().removeAdmin(player);
                player.sendMessage(instane.getColor().parse(instane.getMessagesManager().getMsgGame("gmChange").replace("%player%", player.getName())));
            }
        }
    }
}
