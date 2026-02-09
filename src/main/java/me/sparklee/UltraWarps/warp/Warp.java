package me.sparklee.UltraWarps.warp;

import org.bukkit.Location;

public class Warp {

    private final String name;
    private final Location location;
    private final String permission;

    public Warp(String name, Location location, String permission) {
        this.name = name;
        this.location = location;
        this.permission = permission;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public String getPermission() {
        return permission;
    }

    public boolean isPublic() {
        return permission == null || permission.isEmpty();
    }
}
