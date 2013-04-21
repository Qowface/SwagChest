package net.qowface.swagchest.util;

import java.util.HashMap;
import java.util.Map;
import net.qowface.swagchest.data.SwagStack;
import org.bukkit.enchantments.Enchantment;

public class ItemUtils {
    
    public static SwagStack stringToSwagStack(String swagString) {
        String[] swagPieces = swagString.split(",");
        
        SwagStack swagStack = new SwagStack(Integer.valueOf(swagPieces[0]));
        
        if (!swagPieces[1].equals("0")) {
            if (swagPieces[1].contains("-")) {
                String[] dmg = swagPieces[1].split("-");
                swagStack.setMinDmg(Short.valueOf(dmg[0]));
                swagStack.setMaxDmg(Short.valueOf(dmg[1]));
            } else {
                swagStack.setMinDmg(Short.valueOf(swagPieces[1]));
                swagStack.setMaxDmg(Short.valueOf(swagPieces[1]));
            }
        }
        
        if (swagPieces[2].contains("-")) {
            String[] amnt = swagPieces[2].split("-");
            swagStack.setMinAmnt(Integer.valueOf(amnt[0]));
            swagStack.setMaxAmnt(Integer.valueOf(amnt[1]));
        } else {
            swagStack.setMinAmnt(Integer.valueOf(swagPieces[2]));
            swagStack.setMaxAmnt(Integer.valueOf(swagPieces[2]));
        }
        
        if (!swagPieces[3].equals("0")) {
            Map<Enchantment, Integer> enchantMap = new HashMap<Enchantment, Integer>();
            
            String[] enchants = swagPieces[3].split("&");
            
            for (String enchant : enchants) {
                String[] fullEnchant = enchant.split("#");
                enchantMap.put(Enchantment.getById(Integer.valueOf(fullEnchant[0])), Integer.valueOf(fullEnchant[1]));
            }
            
            swagStack.setEnchants(enchantMap);
        }
        
        return swagStack;
    }
}
