package io.github.exellanix.advancedcombat;

import io.github.exellanix.advancedcombat.commands.AdvancedCombat_Command;
import io.github.exellanix.advancedcombat.listeners.AntiKS;
import io.github.exellanix.advancedcombat.listeners.CombatTagEventCaller;
import io.github.exellanix.advancedcombat.listeners.Tag;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * Created by Brendan on 5/8/2016.
 */
public class AdvancedCombat extends JavaPlugin {

    private static AdvancedCombat singleton;

    public String getPrefix = ChatColor.GOLD + "" + ChatColor.BOLD + "CombatTag " + ChatColor.DARK_GRAY + "⇨ ";

    public void onEnable() {
        PluginDescriptionFile pdfFile = getDescription();
        Logger logger = Logger.getLogger("Minecraft");

        registerEvents();
        registerCommands();
        singleton = this;

        logger.info(pdfFile.getName() + " has been enabled! (V." + pdfFile.getVersion());
    }

    public void onDisable() {
        PluginDescriptionFile pdfFile = getDescription();
        Logger logger = Logger.getLogger("Minecraft");


        logger.info(pdfFile.getName() + " has been disabled! (V." + pdfFile.getVersion());

    }

    public void registerCommands() {
        getCommand("advancedcombat").setExecutor(new AdvancedCombat_Command());
    }

    public void registerEvents() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new CombatTagEventCaller(), this);
        pm.registerEvents(new AntiKS(), this);
        pm.registerEvents(new Tag(), this);
    }

    public static AdvancedCombat getSingleton() {
        return singleton;
    }

    public ArrayList<Player> inCombat = new ArrayList<Player>();
}
