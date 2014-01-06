package net.qowface.swagchest.listeners;

import net.qowface.swagchest.SwagChest;
import net.qowface.swagchest.commands.AdminCommands;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * Handles all Block events.
 * 
 * @author Qowface
 */
public class BlockListener extends SwagListener {
    
    public BlockListener(SwagChest plugin, AdminCommands cmds) {
        super(plugin, cmds);
    }
    
    /**
     * Prevents registered chests from being broken.
     * If this is a chest and is registered, cancel the break.
     */
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();
        
        if (block.getType() == Material.CHEST) {
            Location loc = block.getLocation();
            
            plugin.log.debug("Player tried to break chest at: " + loc.toString());
            
            if (isChestRegistered(loc)) {
                plugin.log.debug("Swag chest found; don't allow break.");

                event.setCancelled(true);
                return;
            }
            
            plugin.log.debug("Just an ordinary chest; allow break.");
        }
    }
}
