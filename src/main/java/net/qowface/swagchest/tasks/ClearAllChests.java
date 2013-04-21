package net.qowface.swagchest.tasks;

import java.util.HashMap;
import net.qowface.swagchest.SwagChest;
import net.qowface.swagchest.data.ChestObject;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.InventoryHolder;

public class ClearAllChests implements Runnable {

    private SwagChest plugin;
    private int id;
    
    private HashMap<String, ChestObject> chests = new HashMap<String, ChestObject>();
    
    public ClearAllChests(SwagChest plugin) {
        this.plugin = plugin;
        
        this.chests = plugin.getChestData().getChests();
        
        this.id = plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, this);
    }
    
    public void run() {
        plugin.log.debug("ClearAllChests task " + id + " running...");
        
        for (ChestObject chestObject : chests.values()) {
            Block block = chestObject.getLocation().getBlock();
            
            if (block.getType() == Material.CHEST) {
                InventoryHolder chest = (InventoryHolder) block.getState();
                
                chest.getInventory().clear();
                
                plugin.log.debug("Cleared chest " + chestObject.getId());
            } else {
                plugin.log.warn("No chest found for " + chestObject.getId() + " at " + chestObject.getLocation().toString());
            }
        }
        
        plugin.log.debug("ClearAllChests task " + id + " completed!");
    }
    
}
