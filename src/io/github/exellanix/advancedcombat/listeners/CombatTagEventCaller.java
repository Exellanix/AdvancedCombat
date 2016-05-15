package io.github.exellanix.advancedcombat.listeners;

import io.github.exellanix.advancedcombat.event.CombatTagEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by Brendan on 5/15/2016.
 */
public class CombatTagEventCaller implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Player) {
            if(event.getEntity() instanceof Player) {
                Bukkit.getServer().getPluginManager().callEvent(new CombatTagEvent((Player) event.getEntity(), (Player) event.getDamager(), 30));
            }
        }
    }
}
