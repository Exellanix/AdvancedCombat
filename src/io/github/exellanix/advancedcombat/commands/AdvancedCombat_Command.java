package io.github.exellanix.advancedcombat.commands;

import io.github.exellanix.advancedcombat.AdvancedCombat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Brendan on 5/15/2016.
 */
public class AdvancedCombat_Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("advancedcombat")) {
            if(sender.hasPermission("advancedcombat.command")) {
                if(args.length == 0) {
                    sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "<------ AdvancedCombat Help ------>");
                    sender.sendMessage(ChatColor.RED + "/advancedcombat reload " + ChatColor.WHITE + "Allows you to reload the plugin!");
                    return true;
                }else if(args.length == 1) {
                    if(args[0].equalsIgnoreCase("reload")) {
                        if(sender.hasPermission("advancedcombat.reload")) {
                            AdvancedCombat.getSingleton().getLogger().info("Reloading the plugin...");
                            AdvancedCombat.getSingleton().getLogger().info("Removing all players in combat...");
                            AdvancedCombat.getSingleton().inCombat.removeAll(Bukkit.getOnlinePlayers());
                            AdvancedCombat.getSingleton().getLogger().info("Removing all player kill counters...");
                            sender.sendMessage("Done...");
                            return true;
                        }else{
                            sender.sendMessage(ChatColor.RED + "You do not have permission!");
                            return true;
                        }
                    }else if(args[0].equalsIgnoreCase("help")) {
                        sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "<------ AdvancedCombat Help ------>");
                        sender.sendMessage(ChatColor.RED + "/advancedcombat reload " + ChatColor.WHITE + "Allows you to reload the plugin!");
                        return true;
                    }else{
                        sender.sendMessage(ChatColor.RED + "That is not a valid argument!");
                        return true;
                    }
                }else{
                    sender.sendMessage(ChatColor.RED + "Too many arguments!");
                    return true;
                }
            }else{
                sender.sendMessage(ChatColor.RED + "You do not have permission!");
                return true;
            }
        }
        return false;
    }
}
