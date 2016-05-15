package io.github.exellanix.advancedcombat.listeners;

import io.github.exellanix.advancedcombat.AdvancedCombat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerKickEvent;

/**
 * Created by Brendan on 5/15/2016.
 */
public class Cancel implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = (Player) event.getEntity();
        AdvancedCombat.getSingleton().inCombat.remove(player);
    }
    @EventHandler
    public void onKick(PlayerKickEvent event) {
        Player player = event.getPlayer();
        AdvancedCombat.getSingleton().inCombat.remove(player);
    }
}
