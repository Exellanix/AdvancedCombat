package io.github.exellanix.advancedcombat;

import io.github.exellanix.advancedcombat.listeners.Damage;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by Brendan on 5/8/2016.
 */
public class AdvancedCombat extends JavaPlugin {

    private static AdvancedCombat singleton;

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
    }

    public void registerEvents() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new Damage(), this);
    }

    public static AdvancedCombat getSingleton() {
        return singleton;
    }

    public ArrayList<Player> inCombat = new ArrayList<Player>();
}
