package net.qowface.swagchest.data;

import java.io.File;
import java.util.HashMap;
import net.qowface.swagchest.SwagChest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ChestData {
    
    private SwagChest plugin;
    
    private FileConfiguration config;
    private File file;
    
    private HashMap<String, ChestObject> chests = new HashMap<String, ChestObject>();
    
    public ChestData(SwagChest plugin) {
        this.plugin = plugin;
        
        file = new File(plugin.getDataFolder(), "chests.yml");
        if (!file.exists()) {
            getConfig().setDefaults(YamlConfiguration.loadConfiguration(plugin.getClass().getResourceAsStream("/chests.yml")));
            getConfig().options().copyDefaults(true);
            saveConfig();
        }
        saveConfig();
        
        loadData();
    }
    
    public final FileConfiguration getConfig() {
        if (config == null) {
            config = YamlConfiguration.loadConfiguration(file);
            config.setDefaults(YamlConfiguration.loadConfiguration(file));
        }
        return config;
    }
    
    public final void saveConfig() {
        try {
            getConfig().save(file);
        } catch (Exception e) {
            plugin.log.warn("Failed to save chests.yml!");
        }
    }
    
    public final void loadData() {
        plugin.log.debug("Loading chests...");
        
        getChests().clear();
        
        for (String key : config.getConfigurationSection("Chests").getKeys(false)) {
            plugin.log.debug("Loading chest: " + key);
            
            String entry = "Chests." + key + ".";
            
            String world = config.getString(entry + "World");
            int x = config.getInt(entry + "X");
            int y = config.getInt(entry + "Y");
            int z = config.getInt(entry + "Z");
            String profile = config.getString(entry + "Profile");
            int count = config.getInt(entry + "Count");
            
            ChestObject object = new ChestObject(key, world, x, y, z, profile, count);
            getChests().put(key, object);
            
            plugin.log.debug("Chest loaded: " + key);
            plugin.log.debug(object.toString());
        }
        
        plugin.log.debug("Chests loaded!");
    }

    public HashMap<String, ChestObject> getChests() {
        return chests;
    }
}
