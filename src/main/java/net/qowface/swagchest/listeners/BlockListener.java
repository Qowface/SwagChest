package net.qowface.swagchest.listeners;

import net.qowface.swagchest.SwagChest;
import net.qowface.swagchest.commands.AdminCommands;
import net.qowface.swagchest.data.ChestObject;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockListener extends SwagListener implements Listener {
    
    public BlockListener(SwagChest plugin, AdminCommands cmds) {
        super(plugin, cmds);
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.isCancelled()) {
            return;
        }
        
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
