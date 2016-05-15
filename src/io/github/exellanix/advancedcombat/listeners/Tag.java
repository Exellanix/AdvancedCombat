package io.github.exellanix.advancedcombat.listeners;

import io.github.exellanix.advancedcombat.AdvancedCombat;
import io.github.exellanix.advancedcombat.event.CombatTagEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

/**
 * Created by Brendan on 5/15/2016.
 */
public class Tag implements Listener {

    @EventHandler
    public void onTag(CombatTagEvent event) {
        Player tagger = event.getAttacker();
        Player victim = event.getVictim();
        if (AdvancedCombat.getSingleton().inCombat.contains(tagger)) {
            AdvancedCombat.getSingleton().inCombat.add(tagger);
        } else {
            AdvancedCombat.getSingleton().inCombat.add(tagger);
            tagger.sendMessage(AdvancedCombat.getSingleton().getPrefix + ChatColor.GRAY + "You have tagged " + victim.getName() + "!");
                int id = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(AdvancedCombat.getSingleton(), new BukkitRunnable() {
                @Override
                public void run() {
                    check(tagger);
                    if(gotCombat()) {
                        cancel();
                    }else{
                        tagger.sendMessage(AdvancedCombat.getSingleton().getPrefix + ChatColor.GRAY + "You are now out of combat!");
                        cancel();
                    }
                }
            }, 30L);
        }

        if (AdvancedCombat.getSingleton().inCombat.contains(victim)) {
            AdvancedCombat.getSingleton().inCombat.add(victim);
        } else {
            AdvancedCombat.getSingleton().inCombat.add(victim);
            victim.sendMessage(AdvancedCombat.getSingleton().getPrefix + ChatColor.GRAY + "You have been tagged by " + tagger.getName() + "!");
            int id = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(AdvancedCombat.getSingleton(), new BukkitRunnable() {
                @Override
                public void run() {
                    check(victim);
                    if(gotCombat()) {
                        cancel();
                    }else{
                        victim.sendMessage(AdvancedCombat.getSingleton().getPrefix + ChatColor.GRAY + "You are now out of combat!");
                        cancel();
                    }
                }
            }, 30L);
        }
    }

    public void check(Player player) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(AdvancedCombat.getSingleton(), new BukkitRunnable() {
            public void run() {
                if(AdvancedCombat.getSingleton().inCombat.contains(player)) {
                    AdvancedCombat.getSingleton().inCombat.add(player);
                    gotCombat();
                }
            }
        }, 20L);
    }

    boolean gotCombat() {
        return true;
    }
}