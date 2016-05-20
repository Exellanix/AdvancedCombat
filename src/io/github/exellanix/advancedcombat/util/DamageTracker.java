package io.github.exellanix.advancedcombat.util;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * Created by Brendan on 5/19/2016.
 */
public class DamageTracker {

    private HashMap<Player, Double> damage;

    private int cooldown;

    public DamageTracker() {
        damage = new HashMap<>();
        cooldown = 0;
    }

    public void decrementCooldown() {
        if(cooldown == 0 && !damage.isEmpty()) {
            damage.clear();
        }else if(cooldown > 0) {
            cooldown--;
        }
    }

    public int getCooldown() {
        return cooldown;
    }

    public Player getKiller() {
        TreeSet<DamageValues> damageSet = new TreeSet<>();
        for(Player p : damage.keySet()) {
            damageSet.add(new DamageValues(p, damage.get(p)));
        }
        if(damageSet.size() > 0) {
            return damageSet.last().p;
        }else{
            return null;
        }
    }

    public void takeDamage(Player p, double damageAmount) {
        if(damage.containsKey(p)) {
            damage.put(p, damage.get(p) + damageAmount);
        }else{
            damage.put(p, damageAmount);
        }
        cooldown = 10;
    }

    public void resetCooldown() {
        cooldown = 0;
    }

    private class DamageValues implements Comparable<DamageValues> {

        Player p;
        double d;

        public DamageValues(Player p, double d) {
            this.p = p;
            this.d = d;
        }

        @Override
        public int compareTo(DamageValues o) {
            if(this.d < o.d) {
                return -1;
            }else if(this.d > o.d) {
                return 1;
            }else{
                return 0;
            }
        }
    }
}
