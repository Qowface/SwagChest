package net.qowface.swagchest.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.qowface.swagchest.SwagChest;

public class Loggy {
    
    private SwagChest plugin;
    private Logger log;
    private boolean debug;
    
    public Loggy(SwagChest plugin) {
        this.plugin = plugin;
        log = plugin.getLogger();
        debug = plugin.getConfig().getBoolean("Config.Debug");
    }
    
    public void info(String msg) {
        log.log(Level.INFO, "{0}", msg);
    }
    
    public void warn(String msg) {
        log.log(Level.WARNING, "{0}", msg);
    }
    
    public void debug(String msg) {
        if (debug) {
            log.log(Level.INFO, "[Debug] {0}", msg);
        }
    }
    
}
