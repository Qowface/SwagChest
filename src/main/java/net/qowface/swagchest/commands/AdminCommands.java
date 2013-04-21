package net.qowface.swagchest.commands;

import java.util.HashSet;
import net.qowface.swagchest.SwagChest;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminCommands implements CommandExecutor {
    
    private SwagChest plugin;
    
    private HashSet<String> swagAdminEnabled = new HashSet<String>();
    
    public AdminCommands(SwagChest plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("swagadmin")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Command not available from console.");
                return true;
            }
            
            Player player = (Player) sender;
            
            if (!player.hasPermission("swagchest.admin")) {
                sender.sendMessage(ChatColor.RED + "You don't have permission to do that.");
                return true;
            }
            
            if (args.length != 0) {
                return false;
            }
            
            String name = player.getPlayerListName();
                       
            if (getSwagAdminEnabled().contains(name)) {
                getSwagAdminEnabled().remove(name);
                sender.sendMessage(ChatColor.GREEN + "SwagChest admin tools disabled.");
            } else {
                getSwagAdminEnabled().add(name);
                sender.sendMessage(ChatColor.GREEN + "SwagChest admin tools enabled.");
            }
            
            return true;
        }
        
        return true;
    }

    public HashSet<String> getSwagAdminEnabled() {
        return swagAdminEnabled;
    }
}
