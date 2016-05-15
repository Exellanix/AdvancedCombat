package io.github.exellanix.advancedcombat.listeners;

import io.github.exellanix.advancedcombat.AdvancedCombat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by Brendan on 5/15/2016.
 */
public class AntiKS implements Listener {

    public static final HashMap<UUID, UUID> entityTracker = new HashMap();
    public static final HashMap<UUID, Integer> counter = new HashMap();

    public static void removePlayer(Player p)
    {
        counter.remove(p.getUniqueId());
        entityTracker.remove(p.getUniqueId());
    }

    public static int getAmount(Player p)
    {
        return ((Integer)counter.get(p.getUniqueId())).intValue();
    }

    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e)
    {
        Entity ent = e.getEntity();
        Entity damager = e.getDamager();
        if ((damager instanceof Player))
        {
            Player p = (Player)damager;
            if (this.entityTracker.containsKey(p.getUniqueId()))
            {
                if (((UUID)this.entityTracker.get(p.getUniqueId())).equals(ent.getUniqueId())) {
                    this.counter.put(p.getUniqueId(), Integer.valueOf(10));
                } else {
                    e.setCancelled(true);
                }
            }
            else if ((!this.entityTracker.containsKey(p.getUniqueId())) && (!this.entityTracker.containsValue(ent.getUniqueId())))
            {
                this.entityTracker.put(p.getUniqueId(), ent.getUniqueId());
                this.counter.put(p.getUniqueId(), Integer.valueOf(10));
            }
            else if ((!this.entityTracker.containsKey(p.getUniqueId())) && (this.entityTracker.containsValue(ent.getUniqueId())))
            {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onEntityDeathEvent(EntityDeathEvent e)
    {
        Entity ent = e.getEntity();
        if ((e.getEntity().getKiller() instanceof Player))
        {
            Player killer = e.getEntity().getKiller();
            if (this.entityTracker.containsValue(ent.getUniqueId()))
            {
                this.entityTracker.remove(killer.getUniqueId());
                this.counter.remove(killer.getUniqueId());
            }
        }
    }
}
