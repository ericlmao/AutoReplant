package com.bonksmp.autoreplant.task;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

public class DelayedPlantingTask extends BukkitRunnable {

    private final Location location;
    private final Material material;

    public DelayedPlantingTask(Location location, Material material) {
        this.location = location;
        this.material = material;
    }

    @Override
    public void run() {
        Block block = location.getBlock();
        block.setType(material);
    }
}
