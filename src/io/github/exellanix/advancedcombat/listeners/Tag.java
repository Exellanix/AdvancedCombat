package io.github.exellanix.advancedcombat.listeners;

import io.github.exellanix.advancedcombat.AdvancedCombat;
import io.github.exellanix.advancedcombat.event.CombatTagEvent;
import me.exellanix.kitpvp.KitPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Brendan on 5/15/2016.
 */
public class Tag implements Listener {

    private int id = -1;

    @Deprecated
    @EventHandler
    public void onTag(CombatTagEvent event) {
        Player tagger = event.getAttacker();
        Player victim = event.getVictim();
        if(!KitPvP.getSingleton().getRegionManager().getRegion("").isInside(tagger)) {
            if (!KitPvP.getSingleton().getRegionManager().getRegion("spawn").isInside(victim)) {
                if (AdvancedCombat.getSingleton().inCombat.contains(tagger)) {
                    AdvancedCombat.getSingleton().inCombat.add(tagger);
                } else {
                    AdvancedCombat.getSingleton().inCombat.add(tagger);
                    tagger.sendMessage(AdvancedCombat.getSingleton().getPrefix + ChatColor.GRAY + "You have tagged " + victim.getName() + "!");
                    id = Bukkit.getServer().getScheduler().runTaskLater(AdvancedCombat.getSingleton(), new Runnable() {
                        @Override
                        public void run() {
                            check(tagger);
                            if (gotCombat()) {
                                cancelTask();
                            } else {
                                tagger.sendMessage(AdvancedCombat.getSingleton().getPrefix + ChatColor.GRAY + "You are now out of combat!");
                                cancelTask();
                            }
                        }
                    }, 30L).getTaskId();
                }

                if (AdvancedCombat.getSingleton().inCombat.contains(victim)) {
                    AdvancedCombat.getSingleton().inCombat.add(victim);
                } else {
                    AdvancedCombat.getSingleton().inCombat.add(victim);
                    victim.sendMessage(AdvancedCombat.getSingleton().getPrefix + ChatColor.GRAY + "You have been tagged by " + tagger.getName() + "!");
                    id = Bukkit.getServer().getScheduler().runTaskLater(AdvancedCombat.getSingleton(), new Runnable() {
                        @Override
                        public void run() {
                            check(tagger);
                            if (gotCombat()) {
                                cancelTask();
                            } else {
                                tagger.sendMessage(AdvancedCombat.getSingleton().getPrefix + ChatColor.GRAY + "You are now out of combat!");
                                cancelTask();
                            }
                        }
                    }, 30L).getTaskId();
                }
            }

        }
    }
    public void check(Player player) {
        if(AdvancedCombat.getSingleton().inCombat.contains(player)) {
            AdvancedCombat.getSingleton().inCombat.add(player);
            gotCombat();
        }
    }

    public boolean gotCombat() {
        return true;
    }

    public void cancelTask() {
        if(id > 0) {
            Bukkit.getServer().getScheduler().cancelTask(id);
        }
    }
}
