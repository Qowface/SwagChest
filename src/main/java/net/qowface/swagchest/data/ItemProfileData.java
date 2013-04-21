package net.qowface.swagchest.data;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import net.qowface.swagchest.SwagChest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ItemProfileData {
    
    private SwagChest plugin;
    
    private FileConfiguration config;
    private File file;
    
    private HashMap<String, ItemProfileObject> profiles = new HashMap<String, ItemProfileObject>();
    
    public ItemProfileData(SwagChest plugin) {
        this.plugin = plugin;
        
        file = new File(plugin.getDataFolder(), "items.yml");
        if (!file.exists()) {
            getConfig().setDefaults(YamlConfiguration.loadConfiguration(plugin.getClass().getResourceAsStream("/items.yml")));
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
            plugin.log.warn("Failed to save items.yml!");
        }
    }
    
    public final void loadData() {
        plugin.log.debug("Loading item profiles...");
        
        getProfiles().clear();
        
        for (String key : config.getConfigurationSection("Items").getKeys(false)) {
            plugin.log.debug("Loading item profile: " + key);
            
            List items = config.getList("Items." + key);
            
            ItemProfileObject object = new ItemProfileObject(key, items);
            getProfiles().put(key, object);
            
            plugin.log.debug("Item profile loaded: " + key);
            plugin.log.debug(object.toString());
        }
        
        plugin.log.debug("Item profiles loaded!");
    }

    public HashMap<String, ItemProfileObject> getProfiles() {
        return profiles;
    }
}
