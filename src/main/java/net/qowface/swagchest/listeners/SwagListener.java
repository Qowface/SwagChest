package net.qowface.swagchest.listeners;

import java.util.HashSet;
import net.qowface.swagchest.SwagChest;
import net.qowface.swagchest.commands.AdminCommands;
import net.qowface.swagchest.data.ChestObject;
import org.bukkit.Location;
import org.bukkit.event.Listener;

/**
 * Abstract event listener for SwagChest.
 * 
 * @author Qowface
 */
public abstract class SwagListener implements Listener {
    
    protected SwagChest plugin;
    protected AdminCommands cmds;
    protected HashSet<String> adminEnabled;
    protected ChestObject chestObject;
    
    public SwagListener(SwagChest plugin, AdminCommands cmds) {
        this.plugin = plugin;
        this.cmds = cmds;
    }
    
    public boolean isChestRegistered(Location loc) {
        for (ChestObject chest : plugin.getChestData().getChests().values()) {
            if (chest.getLocation().equals(loc)) {
                this.chestObject = chest;
                return true;
            }
        }
        
        return false;
    }
}
