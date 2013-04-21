package net.qowface.swagchest.data;

import java.util.Map;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class SwagStack {
    
    private int id;
    private short minDmg;
    private short maxDmg;
    private int minAmnt;
    private int maxAmnt;
    private Map<Enchantment, Integer> enchants;
    
    private ItemStack itemStack;
    
    public SwagStack(int id) {
        this.id = id;
    }
    
    public ItemStack toItemStack() {
        ItemStack theStack = new ItemStack(id, getRandomAmnt(), getRandomDmg());
        if (enchants != null) {
            theStack.addEnchantments(enchants);
        }
        itemStack = theStack;
        return theStack;
    }
    
    public short getRandomDmg() {
        if (minDmg == maxDmg) {
            return minDmg;
        } else {
            return (short)(minDmg + (int)(Math.random() * ((maxDmg - minDmg) + 1)));
        }
    }
    
    public int getRandomAmnt() {
        if (minAmnt == maxAmnt) {
            return minAmnt;
        } else {
            return (minAmnt + (int)(Math.random() * ((maxAmnt - minAmnt) + 1)));
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public short getMinDmg() {
        return minDmg;
    }

    public void setMinDmg(short minDmg) {
        this.minDmg = minDmg;
    }

    public short getMaxDmg() {
        return maxDmg;
    }

    public void setMaxDmg(short maxDmg) {
        this.maxDmg = maxDmg;
    }

    public int getMinAmnt() {
        return minAmnt;
    }

    public void setMinAmnt(int minAmnt) {
        this.minAmnt = minAmnt;
    }

    public int getMaxAmnt() {
        return maxAmnt;
    }

    public void setMaxAmnt(int maxAmnt) {
        this.maxAmnt = maxAmnt;
    }

    public Map<Enchantment, Integer> getEnchants() {
        return enchants;
    }

    public void setEnchants(Map<Enchantment, Integer> enchants) {
        this.enchants = enchants;
    }
    
    @Override
    public String toString() {
        return "SwagStack=[id=" + id + ",minDmg=" + minDmg + ",maxDmg=" + maxDmg + ",minAmnt=" + minAmnt + ",maxAmnt=" + maxAmnt + ",enchants=" + enchants + ",toItemStack=" + itemStack + "]";
    }
}
