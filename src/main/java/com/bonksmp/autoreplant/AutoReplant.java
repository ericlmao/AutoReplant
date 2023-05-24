package com.bonksmp.autoreplant;

import com.bonksmp.autoreplant.listener.AutoReplantListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AutoReplant extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        Bukkit.getPluginManager().registerEvents(new AutoReplantListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
