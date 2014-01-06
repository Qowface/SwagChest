package net.qowface.swagchest.listeners;

import net.qowface.swagchest.SwagChest;
import net.qowface.swagchest.commands.AdminCommands;
import net.qowface.swagchest.tasks.PopulateChest;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Handles all Player events.
 * 
 * @author Qowface
 */
public class PlayerListener extends SwagListener {
    
    public PlayerListener(SwagChest plugin, AdminCommands cmds) {
        super(plugin, cmds);
    }
    
    /**
     * Displays chest information to administrators.
     * If the Player has permission and has administrator mode active, displays
     * chest coordinates and registration status.
     */
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = event.getClickedBlock();
            
            if (block.getType() != Material.CHEST) {
                return;
            }
            
            Location loc = block.getLocation();
            Player player = event.getPlayer();
            adminEnabled = cmds.getSwagAdminEnabled();
            
            if (player.hasPermission("swagchest.admin") && adminEnabled.contains(player.getPlayerListName())) {
                player.sendMessage(ChatColor.GREEN + "Chest coords: " + loc.getBlockX() + ", "
                                                                       + loc.getBlockY() + ", "
                                                                       + loc.getBlockZ() + " ("
                                                                       + loc.getWorld().getName() + ")");
                
                if (isChestRegistered(loc)) {
                    player.sendMessage(ChatColor.GREEN + "This swag chest is registered as: " + chestObject.getId());
                } else {
                    player.sendMessage(ChatColor.GREEN + "This chest is not a registered swag chest.");
                }
            } else {
                if (isChestRegistered(loc)) {
                    if (!plugin.getChestTasks().containsKey(chestObject.getId())) {
                        PopulateChest chestTask = new PopulateChest(plugin, chestObject.getId(), false);
                    }
                }
            }
        }
    }
}
