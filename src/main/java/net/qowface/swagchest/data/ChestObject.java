package net.qowface.swagchest.data;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class ChestObject {
    
    private String id;
    private String world;
    private int x, y, z;
    private String profile;
    private int count;
    
    private Location location;
    
    public ChestObject(String id, String world, int x, int y, int z, String profile, int count) {
        this.id = id;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.profile = profile;
        this.count = count;
        
        this.location = new Location(Bukkit.getServer().getWorld(world), x, y, z);
    }
    
    @Override
    public String toString() {
        return "ChestObject=[id=" + getId() + ",world=" + getWorld() + ",x=" + getX() + ",y=" + getY() + ",z=" + getZ() + ",profile=" + getProfile() + ",count=" + getCount() + "]";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
