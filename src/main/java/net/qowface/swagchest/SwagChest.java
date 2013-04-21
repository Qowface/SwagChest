package net.qowface.swagchest;

import java.util.HashMap;
import net.qowface.swagchest.commands.AdminCommands;
import net.qowface.swagchest.data.ChestData;
import net.qowface.swagchest.data.ItemProfileData;
import net.qowface.swagchest.listeners.BlockListener;
import net.qowface.swagchest.listeners.PlayerListener;
import net.qowface.swagchest.tasks.ClearAllChests;
import net.qowface.swagchest.tasks.PopulateChest;
import net.qowface.swagchest.util.Loggy;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SwagChest extends JavaPlugin {
    
    public Loggy log;
    
    private ChestData chestData;
    private ItemProfileData itemData;
    
    private BlockListener blockListener;
    private PlayerListener playerListener;
    
    private AdminCommands adminCommands;
    
    private HashMap<String, PopulateChest> chestTasks = new HashMap<String, PopulateChest>();
    
    @Override
    public void onEnable() {
        //Setup config
        if (getConfig().options().header() == null) {
            getConfig().options().copyHeader();
            getConfig().options().copyDefaults(true);
            saveConfig();
        }
        
        //Initialize logger
        log = new Loggy(this);
        
        //Setup data files
        chestData = new ChestData(this);
        itemData = new ItemProfileData(this);
        
        //Register commands
        adminCommands = new AdminCommands(this);
        getCommand("swagadmin").setExecutor(adminCommands);
        
        //Register event listeners
        PluginManager pm = this.getServer().getPluginManager();
        blockListener = new BlockListener(this, adminCommands);
        pm.registerEvents(blockListener, this);
        playerListener = new PlayerListener(this, adminCommands);
        pm.registerEvents(playerListener, this);
        
        //Setup task to clear tasks if enabled
        if (getConfig().getBoolean("Config.Chests.Wipe Chests On Server Start")) {
            ClearAllChests clearTask = new ClearAllChests(this);
        }
        
        //Setup initial chest population tasks
        for (String key : getChestData().getChests().keySet()) {
            PopulateChest chestTask = new PopulateChest(this, key, true);
        }
        
        log.info("Enabled");
    }
    
    @Override
    public void onDisable() {
        log.info("Disabled");
    }

    public ChestData getChestData() {
        return chestData;
    }

    public ItemProfileData getItemData() {
        return itemData;
    }

    public HashMap<String, PopulateChest> getChestTasks() {
        return chestTasks;
    }
}
