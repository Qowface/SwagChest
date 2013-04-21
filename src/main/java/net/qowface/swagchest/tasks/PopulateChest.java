package net.qowface.swagchest.tasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import net.qowface.swagchest.SwagChest;
import net.qowface.swagchest.data.ChestObject;
import net.qowface.swagchest.data.ItemProfileObject;
import net.qowface.swagchest.data.SwagStack;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class PopulateChest implements Runnable {

    private SwagChest plugin;
    private String chestID;
    private boolean startup;
    private long delay;
    private int id;
    
    private ChestObject chestObject;
    private ItemProfileObject profileObject;
    
    public PopulateChest(SwagChest plugin, String chestID, boolean startup) {
        this.plugin = plugin;
        this.chestID = chestID;
        this.startup = startup;
        
        this.chestObject = plugin.getChestData().getChests().get(chestID);
        this.profileObject = plugin.getItemData().getProfiles().get(chestObject.getProfile());
        
        if (startup) {
            this.delay = plugin.getConfig().getLong("Config.Chests.Stock X Minutes After Server Start") * 60;
        } else {
            this.delay = plugin.getConfig().getLong("Config.Chests.Restock X Minutes After Opened") * 60;
        }
        
        this.id = plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, this, delay * 20);
        plugin.log.debug("PopulateChest task " + id + " scheduled for chest " + chestID);
        
        plugin.getChestTasks().put(chestID, this);
    }
    
    public void run() {
        plugin.log.debug("PopulateChest task " + id + " running for chest " + chestID);
        
        Location loc = chestObject.getLocation();
        Block block = loc.getBlock();
        
        if (block.getType() == Material.CHEST) {
            InventoryHolder chest = (InventoryHolder) block.getState();
            chest.getInventory().clear();
            
            List<SwagStack> swagList = getShuffledSwag(profileObject.getSwagStacks(), chestObject.getCount());
            
            for (SwagStack swag : swagList) {
                ItemStack item = swag.toItemStack();
                chest.getInventory().addItem(item);
                plugin.log.debug("Added " + item.toString() + " to chest " + chestID);
            }
        } else {
            plugin.log.warn("No chest found for " + chestID + " at " + loc.toString());
        }
        
        plugin.log.debug("PopulateChest task " + id + " completed for chest " + chestID);
        plugin.getChestTasks().remove(chestID);
    }
    
    public List<SwagStack> getShuffledSwag(HashSet<SwagStack> swag, int count) {
        List<SwagStack> theSwag = new ArrayList<SwagStack>(swag);
        Collections.shuffle(theSwag);
        return theSwag.subList(0, count);
    }
    
}
