package net.qowface.swagchest.data;

import java.util.HashSet;
import java.util.List;
import net.qowface.swagchest.util.ItemUtils;

public class ItemProfileObject {
    
    private String id;
    private List<String> items;
    
    private HashSet<SwagStack> swagStacks;
    
    public ItemProfileObject(String id, List<String> items) {
        this.id = id;
        this.items = items;
        
        swagStacks = loadSwagStacks(items);
    }
    
    private HashSet<SwagStack> loadSwagStacks(List<String> items) {
        HashSet<SwagStack> theStacks = new HashSet<SwagStack>();
        
        for (String item : items) {
            SwagStack theSwag = ItemUtils.stringToSwagStack(item);
            
            theStacks.add(theSwag);
        }
        
        return theStacks;
    }
    
    public HashSet<SwagStack> getSwagStacks() {
        return swagStacks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
    
    @Override
    public String toString() {
        return "ItemObject=[id=" + getId() + ",items=" + getItems().toString() + "]";
    }
}
