package com.i0dev.plugins.objects;

import lombok.*;
import org.bukkit.Location;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Cuboid {
    double xMin, yMin, zMin;
    double xMax, yMax, zMax;
    String world;


    public boolean contains(Location location) {
        if (location.getWorld() == null) return false;
        if (!location.getWorld().getName().equalsIgnoreCase(getWorld())) return false;
        if (location.getBlockX() < getXMin()) return false;
        if (location.getBlockX() > getXMax()) return false;
        if (location.getBlockY() < getYMin()) return false;
        if (location.getBlockY() > getYMax()) return false;
        if (location.getBlockZ() < getZMin()) return false;
        if (location.getBlockZ() > getZMax()) return false;
        return true;
    }

}