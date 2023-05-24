package com.bonksmp.autoreplant.listener;

import com.bonksmp.autoreplant.AutoReplant;
import com.bonksmp.autoreplant.task.DelayedPlantingTask;
import com.google.common.base.Preconditions;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class AutoReplantListener implements Listener {

    private final Material[] crops;
    private final Material[] hoes;

    private final AutoReplant plugin;
    public AutoReplantListener(AutoReplant plugin) {
        this.plugin = plugin;
        this.crops = new Material[] {
            Material.WHEAT,
            Material.CARROTS,
            Material.POTATOES,
            Material.BEETROOTS,
            Material.NETHER_WART
        };

        this.hoes = new Material[] {
            Material.WOODEN_HOE,
            Material.STONE_HOE,
            Material.IRON_HOE,
            Material.GOLDEN_HOE,
            Material.DIAMOND_HOE,
            Material.NETHERITE_HOE
        };

    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        ItemStack item = player.getInventory().getItemInMainHand();
        Material type = block.getType();
        if (!isTool(item.getType()) || !isCrop(type)) return;

        Ageable ageable = (Ageable) block.getBlockData();

        if (ageable.getAge() != ageable.getMaximumAge()) {
            event.setCancelled(true);
            return;
        }

        Location location = block.getLocation();
        new DelayedPlantingTask(location, type).runTaskLater(plugin, 2);
    }

    private boolean isCrop(Material material) {
        Preconditions.checkNotNull(material, "Material cannot be null.");

        for (Material crop : crops) {
            if (crop.equals(material))
                return true;
        }
        return false;
    }

    private boolean isTool(Material material) {
        Preconditions.checkNotNull(material, "Material cannot be null.");

        for (Material hoe : hoes) {
            if (hoe.equals(material))
                return true;
        }
        return false;
    }
}
