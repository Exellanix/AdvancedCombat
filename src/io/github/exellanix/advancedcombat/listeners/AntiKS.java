package io.github.exellanix.advancedcombat.listeners;

import io.github.exellanix.advancedcombat.AdvancedCombat;
import io.github.exellanix.advancedcombat.util.DamageTracker;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

/**
 * Created by Brendan on 5/15/2016.
 */

public class AntiKS implements Listener {

    private HashMap<Player, DamageTracker> damage = new HashMap<>();

    public AntiKS() {
        AdvancedCombat.getSingleton().getServer().getScheduler().runTaskTimer(AdvancedCombat.getSingleton(), new BukkitRunnable() {
            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()) {
                    damage.get(p).decrementCooldown();
                }
            }
        }, 0, 20);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onDamage(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            if(event.getEntity() != event.getDamager()) {
                if(!event.isCancelled()) {
                    damage.get(event.getEntity()).takeDamage((Player) event.getDamager(), event.getFinalDamage());
                }
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        damage.put(event.getPlayer(), new DamageTracker());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        damage.remove(event.getPlayer());
    }

    @EventHandler
    public void onKick(PlayerKickEvent event) {
        damage.remove(event.getPlayer());
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if(damage.get(event.getEntity()).getCooldown() > 0) {
            Player killer = damage.get(event.getEntity()).getKiller();
            if(killer != null) {
                //TODO give killer money

                //TODO make death messages in another death event

            }
        }
        damage.get(event.getEntity()).resetCooldown();
    }
}
